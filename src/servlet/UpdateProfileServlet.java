package servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connessione.ConnessioneDb;
import dao.UtenteDAO;
import model.Utente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/updateProfile")
public class UpdateProfileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Utente utente = (session != null) ? (Utente) session.getAttribute("utenteLoggato") : null;

        if (utente == null) {
            response.sendRedirect("loginregister.jsp"); // Reindirizza al login se l'utente non è loggato
            return;
        }

        // Leggi il JSON dal corpo della richiesta
        StringBuilder jsonBuffer = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonBuffer.append(line);
            }
        }

        // Parsing del JSON
        Gson gson = new GsonBuilder().setLenient().create();
        JsonObject jsonObject;
        try {
            jsonObject = gson.fromJson(jsonBuffer.toString(), JsonObject.class);
        } catch (JsonSyntaxException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato JSON non valido.");
            return;
        }

        // Recupera i nuovi dati dell'utente dal JSON
        String username = jsonObject.get("username").getAsString();
        String email = jsonObject.get("email").getAsString();
        String password = jsonObject.get("password").getAsString();

        // Stampa di debug per verificare i valori
        System.out.println("Debug: Username: " + username);
        System.out.println("Debug: Email: " + email);
        System.out.println("Debug: Password: " + password);

        try (Connection connection = ConnessioneDb.getCon()) {
            // Aggiorna l'utente nel database
            boolean aggiornato = UtenteDAO.updateUtente(utente.getUtenteId(), username, email, password, connection);

            if (aggiornato) {
                // Aggiorna l'oggetto utente nella sessione
                utente.setUsername(username);
                utente.setEmail(email);
                utente.setPassword(password);
                session.setAttribute("utenteLoggato", utente);
                
                // Invia la risposta OK
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.print("{\"message\": \"Profilo aggiornato con successo!\"}");
                out.flush();
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante l'aggiornamento del profilo.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante l'aggiornamento del profilo.");
        }
    }
}
