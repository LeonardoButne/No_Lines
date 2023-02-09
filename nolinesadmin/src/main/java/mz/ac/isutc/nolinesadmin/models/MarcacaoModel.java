package mz.ac.isutc.nolinesadmin.models;

public class MarcacaoModel {
    private String id;
    private String id_agenda;
    private String sector;
    private String data;
    private String hora;
    private String nome_paciente;



    public MarcacaoModel(String id, String id_agenda, String sector, String data, String hora, String nome_paciente) {
        this.id = id;
        this.id_agenda = id_agenda;
        this.sector = sector;
        this.data = data;
        this.hora = hora;
        this.nome_paciente = nome_paciente;
    }

    public MarcacaoModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_agenda() {
        return id_agenda;
    }

    public void setId_agenda(String id_agenda) {
        this.id_agenda = id_agenda;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getNome_paciente() {
        return nome_paciente;
    }

    public void setNome_paciente(String nome_paciente) {
        this.nome_paciente = nome_paciente;
    }
}
