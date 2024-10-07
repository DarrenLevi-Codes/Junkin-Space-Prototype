package model;

import java.util.List;

public class Genere {
    private int genereId;
    private String nome;

    // Costruttore
    public Genere(int genereId, String nome) {
        this.genereId = genereId;
        this.nome = nome;
    }

    // Getter e Setter
    public int getGenereId() {
        return genereId;
    }

    public void setGenereId(int genereId) {
        this.genereId = genereId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
