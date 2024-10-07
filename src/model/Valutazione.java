package model;

import java.time.LocalDateTime;

public class Valutazione {
    private int valutazioneId;
    private Utente utente; // Relazione Molti-a-Uno con `Utente`
    private String tmdbFilmId; // ID del film di TMDB
    private int valutazione; // Assume un punteggio numerico
    private LocalDateTime dataValutazione;

    // Costruttore
    public Valutazione(int valutazioneId, Utente utente, String tmdbFilmId, int valutazione, LocalDateTime dataValutazione) {
        this.valutazioneId = valutazioneId;
        this.utente = utente;
        this.tmdbFilmId = tmdbFilmId;
        this.valutazione = valutazione;
        this.dataValutazione = dataValutazione;
    }

    // Getter e Setter
    public int getValutazioneId() {
        return valutazioneId;
    }

    public void setValutazioneId(int valutazioneId) {
        this.valutazioneId = valutazioneId;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public String getTmdbFilmId() {
        return tmdbFilmId;
    }

    public void setTmdbFilmId(String tmdbFilmId) {
        this.tmdbFilmId = tmdbFilmId;
    }

    public int getValutazione() {
        return valutazione;
    }

    public void setValutazione(int valutazione) {
        this.valutazione = valutazione;
    }

    public LocalDateTime getDataValutazione() {
        return dataValutazione;
    }

    public void setDataValutazione(LocalDateTime dataValutazione) {
        this.dataValutazione = dataValutazione;
    }
}
