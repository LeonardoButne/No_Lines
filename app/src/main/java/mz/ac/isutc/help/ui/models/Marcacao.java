package mz.ac.isutc.help.ui.models;

import com.google.firebase.database.DatabaseReference;

import mz.ac.isutc.help.ui.config.ConfiguracaoFireBase;



public class Marcacao {
    private String id;
    private String sector;
    private String data;
    private String hora;

    public Marcacao() {
    }

    public Marcacao(String id, String sector, String data, String hora) {
        this.id = id;
        this.sector = sector;
        this.data = data;
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "Marcacao{" +
                "id='" + id + '\'' +
                ", sector='" + sector + '\'' +
                ", data='" + data + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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


}
