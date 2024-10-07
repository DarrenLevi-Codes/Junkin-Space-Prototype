package dao;

import connessione.ConnessioneDb;
import model.Ruolo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RuoloDAO {

    // Metodo per ottenere un ruolo in base al suo ID
	public static Ruolo getRuoloById(int ruoloId, Connection connection) {
        String query = "SELECT * FROM ruoli WHERE ruolo_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, ruoloId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return new Ruolo(
                        rs.getInt("ruolo_id"),
                        rs.getString("nome")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Restituisce null se il ruolo non viene trovato o si verifica un errore
    }


    // Metodo per ottenere tutti i ruoli disponibili
    public static List<Ruolo> getAllRuoli() {
        List<Ruolo> ruoli = new ArrayList<>();
        String query = "SELECT * FROM ruoli";
        try (Connection connection = ConnessioneDb.getCon();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                Ruolo ruolo = new Ruolo(
                        rs.getInt("ruolo_id"),
                        rs.getString("nome")
                );
                ruoli.add(ruolo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ruoli;
    }

    // Metodo per creare un nuovo ruolo
    public static boolean createRuolo(Ruolo ruolo) {
        String query = "INSERT INTO ruoli (nome) VALUES (?)";
        try (Connection connection = ConnessioneDb.getCon();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, ruolo.getNome());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Metodo per aggiornare un ruolo esistente
    public static boolean updateRuolo(Ruolo ruolo) {
        String query = "UPDATE ruoli SET nome = ? WHERE ruolo_id = ?";
        try (Connection connection = ConnessioneDb.getCon();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, ruolo.getNome());
            preparedStatement.setInt(2, ruolo.getRuoloId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Metodo per eliminare un ruolo
    public static boolean deleteRuolo(int ruoloId) {
        String query = "DELETE FROM ruoli WHERE ruolo_id = ?";
        try (Connection connection = ConnessioneDb.getCon();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, ruoloId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
