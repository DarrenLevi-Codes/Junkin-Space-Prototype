package model;

import java.time.LocalDateTime;
import java.util.List;

public class Recensione {
    private int recensioneId;
    private String tmdbFilmId; // ID del film di TMDB
    private Utente utente; // Relazione Molti-a-Uno con `Utente`
    private String username; // Username dell'utente
    private String commento;
    private LocalDateTime dataRecensione;
    private List<Commento> commenti; // Relazione Uno-a-Molti con `Commento`

    // Costruttore vuoto
    public Recensione() {
    }

    // Costruttore completo
    public Recensione(int recensioneId, String tmdbFilmId, Utente utente, String username, String commento, LocalDateTime dataRecensione) {
        this.recensioneId = recensioneId;
        this.tmdbFilmId = tmdbFilmId;
        this.utente = utente;
        this.username = username;
        this.commento = commento;
        this.dataRecensione = dataRecensione;
    }

    // Getter e Setter
    public int getRecensioneId() {
        return recensioneId;
    }

    public void setRecensioneId(int recensioneId) {
        this.recensioneId = recensioneId;
    }

    public String getTmdbFilmId() {
        return tmdbFilmId;
    }

    public void setTmdbFilmId(String tmdbFilmId) {
        this.tmdbFilmId = tmdbFilmId;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCommento() {
        return commento;
    }

    public void setCommento(String commento) {
        this.commento = commento;
    }

    public LocalDateTime getDataRecensione() {
        return dataRecensione;
    }

    public void setDataRecensione(LocalDateTime dataRecensione) {
        this.dataRecensione = dataRecensione;
    }

    public List<Commento> getCommenti() {
        return commenti;
    }

    public void setCommenti(List<Commento> commenti) {
        this.commenti = commenti;
    }
}
