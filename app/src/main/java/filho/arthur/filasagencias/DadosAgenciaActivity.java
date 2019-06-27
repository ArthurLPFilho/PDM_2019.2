package filho.arthur.filasagencias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import filho.arthur.filasagencias.dao.AgenciaDAO;
import filho.arthur.filasagencias.modelo.Agencia;

public class DadosAgenciaActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_agencia);
        helper = new FormularioHelper(this);
        Intent intent = getIntent();
        Agencia agencia = (Agencia) intent.getSerializableExtra( "agencia" );
        if (agencia != null){
            helper.preencheFormulario(agencia);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_dados_agencias, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:
                Agencia agencia = helper.pegaAgencia();
                AgenciaDAO dao = new AgenciaDAO(this, 1);
                if(agencia.getId() != null){
                    dao.altera(agencia);
                }else {
                    dao.insere( agencia );
                }
                dao.close();
                Toast.makeText(DadosAgenciaActivity.this, "Avaliação da agência " + agencia.getNome() + " Salva!", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}