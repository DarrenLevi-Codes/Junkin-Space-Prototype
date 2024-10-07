package model;
import java.util.List;

public class Ruolo {
    private int ruoloId;
    private String nome;
    private List<Utente> utenti; // Relazione Uno-a-Molti con `Utente`

    // Costruttore
    public Ruolo(int ruoloId, String nome) {
        this.ruoloId = ruoloId;
        this.nome = nome;
    }

    // Getter e Setter
    public int getRuoloId() {
        return ruoloId;
    }

    public void setRuoloId(int ruoloId) {
        this.ruoloId = ruoloId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Utente> getUtenti() {
        return utenti;
    }

    public void setUtenti(List<Utente> utenti) {
        this.utenti = utenti;
    }
}

