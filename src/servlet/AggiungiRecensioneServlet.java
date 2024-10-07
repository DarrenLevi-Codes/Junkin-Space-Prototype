package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connessione.ConnessioneDb;
import dao.RecensioneDao;
import model.Recensione;
import model.Utente;

@WebServlet("/aggiungiRecensione")
public class AggiungiRecensioneServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Debug: Inizio doPost della AggiungiRecensioneServlet");

        HttpSession session = request.getSession(false);
        Utente utente = (session != null) ? (Utente) session.getAttribute("utenteLoggato") : null;

        if (utente == null) {
            System.out.println("Debug: Utente non loggato, reindirizzo alla pagina di login");
            response.sendRedirect("loginregister.jsp"); // Reindirizza al login se l'utente non è loggato
            return;
        }

        String commento = request.getParameter("commento");
        String tmdbFilmId = request.getParameter("tmdbFilmId");
        System.out.println("Debug: Parametri ricevuti - Commento: " + commento + ", tmdbFilmId: " + tmdbFilmId);

        if (commento != null && !commento.isEmpty() && tmdbFilmId != null) {
            try (Connection connection = ConnessioneDb.getCon()) {
                System.out.println("Debug: Connessione al database riuscita.");

                Recensione nuovaRecensione = new Recensione();
                nuovaRecensione.setTmdbFilmId(tmdbFilmId);
                nuovaRecensione.setUtente(utente);
                nuovaRecensione.setCommento(commento);
                nuovaRecensione.setDataRecensione(LocalDateTime.now());

                boolean inserita = RecensioneDao.insertRecensione(nuovaRecensione, connection);
                if (inserita) {
                    System.out.println("Debug: Recensione inserita con successo.");
                } else {
                    System.out.println("Debug: Errore durante l'inserimento della recensione.");
                }
            } catch (SQLException e) {
                System.out.println("Debug: Errore durante la connessione al database o l'inserimento della recensione.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Debug: Parametri non validi per l'inserimento della recensione.");
        }

        // Redirect to the movie page to show the new review
        response.sendRedirect("scheda.jsp?movieId=" + tmdbFilmId);
    }
}
