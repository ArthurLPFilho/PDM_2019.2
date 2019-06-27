package filho.arthur.filasagencias.modelo;

import java.io.Serializable;

public class Agencia implements Serializable {
    private Long id;
    private String numero;
    private String nome;
    private String endereco;
    private String horario;
    private Double nota;
    private Double qtde;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Double getQtde() {
        return qtde;
    }

    public void setQtde(Double qtde) {
        this.qtde = qtde;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }


    @Override
    public String toString() {
        return getNumero() + " - " + getNome() + " - " + getEndereco() + " - " + getHorario() + " - " + getNota() + " - " + getQtde();
    }
}