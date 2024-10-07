package servlet;

import connessione.ConnessioneDb;
import dao.UtenteDAO;
import dao.RuoloDAO;
import model.Utente;
import model.Ruolo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/registrazione")
public class RegistrazioneServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Debug: Inizio doPost della RegistrazioneServlet");

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println("Debug: Parametri ricevuti - Username: " + username + ", Email: " + email + ", Password: " + password);

        Connection connection = null;

        try {
            // Tentativo di connessione al database
            System.out.println("Debug: Tentativo di connessione al database...");
            connection = ConnessioneDb.getCon();  // Ottenere una nuova connessione
            System.out.println("Debug: Connessione al database riuscita.");

            // Utilizzare il metodo della RuoloDAO per ottenere il ruolo base (es. ID ruolo "Utente normale")
            Ruolo ruoloBase = RuoloDAO.getRuoloById(1, connection);
            if (ruoloBase == null) {
                System.out.println("Debug: Ruolo base non trovato. Errore di sistema.");
                request.setAttribute("errorMessage", "Errore di sistema: ruolo non disponibile");
                request.getRequestDispatcher("loginregister.jsp").forward(request, response);
                return;
            }

            // Creare il nuovo utente con i dati ricevuti
            Utente nuovoUtente = new Utente(0, username, email, password, ruoloBase);
            boolean success = UtenteDAO.createUtente(nuovoUtente, connection);

            if (success) {
                System.out.println("Debug: Utente creato con successo. Reindirizzamento alla pagina di login.");
                response.sendRedirect("loginregister.jsp");
            } else {
                System.out.println("Debug: Errore durante la creazione dell'utente.");
                request.setAttribute("errorMessage", "Errore durante la registrazione. Riprova.");
                request.getRequestDispatcher("loginregister.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            System.out.println("Debug: SQLException durante l'operazione - " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("errorMessage", "Errore del server durante la registrazione.");
            request.getRequestDispatcher("loginregister.jsp").forward(request, response);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Debug: Connessione al database chiusa con successo.");
                } catch (SQLException e) {
                    System.out.println("Debug: Errore durante la chiusura della connessione - " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
}
