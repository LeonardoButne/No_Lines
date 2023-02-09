package mz.ac.isutc.help.ui.models;

public class Horario {
    private String id;
    private String nome;

    public Horario(String id, String horario) {
        this.id = id;
        this.nome = horario;
    }

    public Horario() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
