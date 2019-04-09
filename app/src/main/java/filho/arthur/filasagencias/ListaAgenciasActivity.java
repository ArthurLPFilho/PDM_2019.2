package filho.arthur.filasagencias;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.List;
import filho.arthur.filasagencias.dao.AgenciaDAO;
import filho.arthur.filasagencias.modelo.Agencia;

public class ListaAgenciasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_agencias);

        Button avaliaAgencia = (Button) findViewById(R.id.avaliarAgencia);
        avaliaAgencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiAvaliarAgencia = new Intent(ListaAgenciasActivity.this, DadosAgenciaActivity.class);
                startActivity(intentVaiAvaliarAgencia);
            }
        });
    }

    private void carregaLista() {
        AgenciaDAO dao = new AgenciaDAO(this, 1);
        List<Agencia> agencias = dao.buscaAgencias();
        dao.close();

        //String agencias[]={"Agamenon Magalhães", "Av Caxangá", "Ilha do Leite", "Cidade Universitária", "CHESF"};
        ListView listaAgencias = (ListView) findViewById(R.id.lista_agencias);
        ArrayAdapter<Agencia> adapter = new ArrayAdapter<Agencia>(this, android.R.layout.simple_list_item_1,agencias);
        listaAgencias.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_lista_agencias, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_mapa:
                Intent vaiParaMapa = new Intent(this, MapaActivity.class);
                startActivity(vaiParaMapa);
                break;
        }
        return false;
    }
}
