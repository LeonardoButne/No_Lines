package mz.ac.isutc.help.ui.models;

import com.google.firebase.database.DatabaseReference;

import mz.ac.isutc.help.ui.config.ConfiguracaoFireBase;

public class Usuario {
    private String id;
    private String email;
    private String senha;
    private String telefone;
    private String nome;

    public Usuario(String id, String email, String senha) {
        this.id = id;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(String email, String senha, String telefone, String nome) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.nome = nome;
    }

    public Usuario(){

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

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", senha=" + senha +
                '}';
    }
    public void salvarDados(){
        DatabaseReference firebase = ConfiguracaoFireBase.getFirebaseDatabase();
        firebase.child("usuarios").child(this.id).setValue(this);
    }
}
