package model;

import java.util.List;

public class Utente {
    private int utenteId;
    private String username;
    private String email;
    private String password;
    private Ruolo ruolo; // Relazione Molti-a-Uno con `Ruolo`
    private List<Recensione> recensioni; // Relazione Uno-a-Molti con `Recensione`
    private List<Valutazione> valutazioni; // Relazione Uno-a-Molti con `Valutazione`
    private List<Commento> commenti; // Relazione Uno-a-Molti con `Commento`

    // Costruttore
    public Utente(int utenteId, String username, String email, String password, Ruolo ruolo) {
        this.utenteId = utenteId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.ruolo = ruolo;
    }

    // Getter e Setter
    public int getUtenteId() {
        return utenteId;
    }

    public void setUtenteId(int utenteId) {
        this.utenteId = utenteId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Ruolo getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }

    public List<Recensione> getRecensioni() {
        return recensioni;
    }

    public void setRecensioni(List<Recensione> recensioni) {
        this.recensioni = recensioni;
    }

    public List<Valutazione> getValutazioni() {
        return valutazioni;
    }

    public void setValutazioni(List<Valutazione> valutazioni) {
        this.valutazioni = valutazioni;
    }

    public List<Commento> getCommenti() {
        return commenti;
    }

    public void setCommenti(List<Commento> commenti) {
        this.commenti = commenti;
    }
}
