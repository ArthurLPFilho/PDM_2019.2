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
    private Agencia agencia;

    //inclusao da somaDasNotasAnteriores
    private Double notaAcumulada;

    public Double getNotaAcumulada() {
        return notaAcumulada;
    }

    public void setNotaAcumulada(Double notaAcumulada) {
        this.notaAcumulada = agencia.getNota();
    }
    //Fim da inclusao

    public FormularioHelper(DadosAgenciaActivity activity){
        campoNumero = (EditText) activity.findViewById(R.id.formulario_num);
        campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        campoEndereco = (EditText) activity.findViewById(R.id.formulario_endereco);
        campoHorario = (EditText) activity.findViewById(R.id.formulario_horario);
        campoNota = (RatingBar) activity.findViewById(R.id.formulario_nota);

        agencia = new Agencia();
    }

    public Agencia pegaAgencia() {
        //Agencia agencia = new Agencia();
        agencia.setNumero(campoNumero.getText().toString());
        agencia.setNome(campoNome.getText().toString());
        agencia.setEndereco(campoEndereco.getText().toString());
        agencia.setHorario(campoHorario.getText().toString());
        agencia.setNota(Double.valueOf(campoNota.getProgress()));
        return agencia;
    }

    public void preencheFormulario(Agencia agencia) {
        //armazena a soma das notas ate a ultima avaliacao
        notaAcumulada = agencia.getNota();
        campoNumero.setText(agencia.getNumero());
        campoNome.setText(agencia.getNome());
        campoEndereco.setText(agencia.getEndereco());
        campoHorario.setText(agencia.getHorario());
        campoNota.setProgress(agencia.getNota().intValue());
        //campoNota.setProgress(agencia.getNota().intValue() / agencia.getQtde().intValue());
        this.agencia = agencia;
    }
}