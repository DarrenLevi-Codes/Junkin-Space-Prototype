package dao;

import model.Utente;
import model.Ruolo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteDAO {

    public static Utente getUtenteByEmailAndPassword(String email, String password, Connection connection) {
        String query = "SELECT * FROM utenti WHERE email = ? AND password = ?";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Ruolo ruolo = RuoloDAO.getRuoloById(rs.getInt("ruolo_id"), connection);
                return new Utente(
                        rs.getInt("utente_id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        ruolo
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null; // Restituisce null se l'utente non viene trovato o se si verifica un errore
    }

    public static Utente getUtenteById(int utenteId, Connection connection) {
        String query = "SELECT * FROM utenti WHERE utente_id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, utenteId);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Ruolo ruolo = RuoloDAO.getRuoloById(rs.getInt("ruolo_id"), connection);
                return new Utente(
                        rs.getInt("utente_id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        ruolo
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null; // Restituisce null se l'utente non viene trovato o si verifica un errore
    }

    public static boolean createUtente(Utente utente, Connection connection) {
        String query = "INSERT INTO utenti (username, email, password, ruolo_id) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, utente.getUsername());
            preparedStatement.setString(2, utente.getEmail());
            preparedStatement.setString(3, utente.getPassword());
            preparedStatement.setInt(4, utente.getRuolo().getRuoloId());

            int result = preparedStatement.executeUpdate();
            return result > 0; // Restituisce true se l'inserimento è avvenuto con successo
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean updateUtente(int utenteId, String username, String email, String password, Connection connection) {
        String query = "UPDATE utenti SET username = ?, email = ?, password = ? WHERE utente_id = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, username);
            pst.setString(2, email);
            pst.setString(3, password);
            pst.setInt(4, utenteId);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean deleteUtente(int utenteId, Connection connection) {
        String query = "DELETE FROM utenti WHERE utente_id = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, utenteId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
