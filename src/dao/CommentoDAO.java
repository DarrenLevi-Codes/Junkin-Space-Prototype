package dao;

import model.Commento;
import model.Recensione;
import model.Utente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentoDAO {

    public static List<Commento> getCommentiByRecensione(int recensioneId, Connection connection) {
        List<Commento> commenti = new ArrayList<>();
        String query = "SELECT * FROM commenti WHERE recensione_id = ?";
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = connection.prepareStatement(query);
            st.setInt(1, recensioneId);
            rs = st.executeQuery();

            while (rs.next()) {
                int commentoId = rs.getInt("commento_id");
                int utenteId = rs.getInt("utente_id");
                String testo = rs.getString("testo");
                Timestamp dataCommento = rs.getTimestamp("data_commento");
                Recensione recensione = RecensioneDao.getRecensione(recensioneId, connection);
                Utente utente = UtenteDAO.getUtenteById(utenteId, connection);

                Commento commento = new Commento(commentoId, recensione, utente, testo, dataCommento.toLocalDateTime());
                commenti.add(commento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return commenti;
    }

    public static boolean insertCommento(Commento commento, Connection connection) {
        boolean inserito = false;
        String query = "INSERT INTO commenti (recensione_id, utente_id, testo, data_commento) VALUES (?,?,?,?)";
        PreparedStatement st = null;

        try {
            st = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, commento.getRecensione().getRecensioneId());
            st.setInt(2, commento.getUtente().getUtenteId());
            st.setString(3, commento.getTesto());
            st.setTimestamp(4, Timestamp.valueOf(commento.getDataCommento()));

            inserito = st.executeUpdate() > 0;

            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                commento.setCommentoId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return inserito;
    }

    public static boolean deleteCommento(int id, Connection connection) {
        boolean cancellato = false;
        String query = "DELETE FROM commenti WHERE commento_id = ?";
        PreparedStatement st = null;

        try {
            st = connection.prepareStatement(query);
            st.setInt(1, id);

            cancellato = st.executeUpdate() > 0;
            System.out.println("Cancellato dal db commento con id: " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return cancellato;
    }

    public static boolean updateCommento(Commento commento, Connection connection) {
        boolean aggiornato = false;
        String query = "UPDATE commenti SET testo = ?, data_commento = ? WHERE commento_id = ?";
        PreparedStatement st = null;

        try {
            st = connection.prepareStatement(query);
            st.setString(1, commento.getTesto());
            st.setTimestamp(2, Timestamp.valueOf(commento.getDataCommento()));
            st.setInt(3, commento.getCommentoId());

            aggiornato = st.executeUpdate() > 0;
            System.out.println("Aggiornato nel db commento con id: " + commento.getCommentoId());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return aggiornato;
    }
}
