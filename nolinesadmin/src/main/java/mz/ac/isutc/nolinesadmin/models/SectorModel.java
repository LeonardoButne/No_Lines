package mz.ac.isutc.nolinesadmin.models;

public class SectorModel {
    private String id;
    private String nome;

    public SectorModel(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public SectorModel() {
    }


    @Override
    public String toString() {
        return "SectorModel{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                '}';
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
