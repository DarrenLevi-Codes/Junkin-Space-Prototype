package model;

import java.time.LocalDateTime;

public class Commento {
    private int commentoId;
    private Recensione recensione; // Relazione Molti-a-Uno con `Recensione`
    private Utente utente; // Relazione Molti-a-Uno con `Utente`
    private String testo;
    private LocalDateTime dataCommento;

    // Costruttore
    public Commento(int commentoId, Recensione recensione, Utente utente, String testo, LocalDateTime dataCommento) {
        this.commentoId = commentoId;
        this.recensione = recensione;
        this.utente = utente;
        this.testo = testo;
        this.dataCommento = dataCommento;
    }

    // Getter e Setter
    public int getCommentoId() {
        return commentoId;
    }

    public void setCommentoId(int commentoId) {
        this.commentoId = commentoId;
    }

    public Recensione getRecensione() {
        return recensione;
    }

    public void setRecensione(Recensione recensione) {
        this.recensione = recensione;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public LocalDateTime getDataCommento() {
        return dataCommento;
    }

    public void setDataCommento(LocalDateTime dataCommento) {
        this.dataCommento = dataCommento;
    }
}
