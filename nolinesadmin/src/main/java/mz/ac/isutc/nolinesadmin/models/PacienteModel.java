package mz.ac.isutc.nolinesadmin.models;

public class PacienteModel {
    private String id;
    private String email;
    private String senha;
    private String telefone;
    private String nome;

    public PacienteModel(String id, String email, String senha, String telefone, String nome) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.nome = nome;
    }

    public PacienteModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
