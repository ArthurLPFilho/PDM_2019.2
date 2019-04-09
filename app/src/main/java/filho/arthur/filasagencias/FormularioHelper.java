package filho.arthur.filasagencias;

import android.widget.EditText;
import android.widget.RatingBar;

import filho.arthur.filasagencias.modelo.Agencia;

public class FormularioHelper {
    private final EditText campoNumero;
    private final EditText campoNome;
    private final EditText campoEndereco;
    private final EditText campoHorario;
    private final RatingBar campoNota;

    public FormularioHelper(DadosAgenciaActivity activity){
        campoNumero = (EditText) activity.findViewById(R.id.formulario_num);
        campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        campoEndereco = (EditText) activity.findViewById(R.id.formulario_endereco);
        campoHorario = (EditText) activity.findViewById(R.id.formulario_horario);
        campoNota = (RatingBar) activity.findViewById(R.id.formulario_nota);

    }

    public Agencia pegaAgencia() {
        Agencia agencia = new Agencia();
        agencia.setNumero(campoNumero.getText().toString());
        agencia.setNome(campoNome.getText().toString());
        agencia.setEndereco(campoEndereco.getText().toString());
        agencia.setHorario(campoHorario.getText().toString());
        agencia.setNota(Double.valueOf(campoNota.getProgress()));
        return agencia;
    }
}
