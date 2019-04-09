package filho.arthur.filasagencias.modelo;

public class Agencia {
    private String numero;
    private String nome;
    private String endereco;
    private String horario;
    private Double nota;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return getNumero() + " - " + getNome() + " - " + getEndereco() + " - " + getHorario() + " - " + getNota();
    }
}
