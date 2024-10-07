package servlet;
import connessione.ConnessioneDb;
import dao.UtenteDAO;
import model.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Debug: Inizio doPost della LoginServlet");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println("Debug: Parametri ricevuti - Email: " + email + ", Password: " + password);

        Connection connection = null;

        try {
            // Tentativo di connessione al database
            System.out.println("Debug: Tentativo di connessione al database...");
            connection = ConnessioneDb.getCon();
            System.out.println("Debug: Connessione al database riuscita.");

            // Utilizzare il metodo della UtenteDAO per autenticare l'utente
            Utente utente = UtenteDAO.getUtenteByEmailAndPassword(email, password, connection);

            if (utente != null) {
                System.out.println("Debug: Utente trovato. Creazione della sessione...");
                HttpSession session = request.getSession(true);
                session.setAttribute("utenteLoggato", utente); // Aggiungere l'utente alla sessione
                response.sendRedirect("homepage.jsp"); // Reindirizzare alla homepage
            } else {
                System.out.println("Debug: Utente non trovato. Email o password errati.");
                request.setAttribute("errorMessage", "Email o password errati.");
                request.getRequestDispatcher("loginregister.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            System.out.println("Debug: SQLException durante l'operazione - " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("errorMessage", "Errore del server durante il login.");
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
