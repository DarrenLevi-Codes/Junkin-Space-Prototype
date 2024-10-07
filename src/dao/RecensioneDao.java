package dao;

import model.Recensione;
import model.Utente;
import model.Ruolo;
import dao.RuoloDAO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RecensioneDao {

	public static boolean insertRecensione(Recensione recensione, Connection connection) {
		boolean result = false;
		System.out.println("Aggiunta recensione in corso...");
		String query = "INSERT INTO recensioni (recensione_id, tmdb_film_id, utente_id, commento, data_recensione) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement pst = null;

		try {
			pst = connection.prepareStatement(query);
			pst.setInt(1, recensione.getRecensioneId());
			pst.setString(2, recensione.getTmdbFilmId());
			pst.setInt(3, recensione.getUtente().getUtenteId());
			pst.setString(4, recensione.getCommento());
			pst.setTimestamp(5, Timestamp.valueOf(recensione.getDataRecensione()));

			pst.executeUpdate();
			result = true;
			System.out.println("Recensione aggiunta: " + result);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static boolean deleteRecensione(int codRecensione, Connection connection) {
		boolean cancellato = false;
		String query = "DELETE FROM recensioni WHERE recensione_id = ?";
		PreparedStatement st = null;

		try {
			st = connection.prepareStatement(query);
			st.setInt(1, codRecensione);
			cancellato = st.executeUpdate() > 0;

			System.out.println("Cancellato dal db recensione con codice " + codRecensione);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cancellato;
	}

	public static boolean updateRecensione(int codRecensione, Recensione recensioneAggiornata, Connection connection) {
		boolean aggiornato = false;
		String query = "UPDATE recensioni SET tmdb_film_id = ?, utente_id = ?, commento = ?, data_recensione = ? WHERE recensione_id = ?";
		PreparedStatement st = null;

		try {
			st = connection.prepareStatement(query);
			st.setString(1, recensioneAggiornata.getTmdbFilmId());
			st.setInt(2, recensioneAggiornata.getUtente().getUtenteId());
			st.setString(3, recensioneAggiornata.getCommento());
			st.setTimestamp(4, Timestamp.valueOf(recensioneAggiornata.getDataRecensione()));
			st.setInt(5, codRecensione);

			aggiornato = st.executeUpdate() > 0;

			if (aggiornato) {
				System.out.println("Aggiornato nel db la recensione con codice " + codRecensione);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return aggiornato;
	}

	public static Recensione getRecensione(int id, Connection connection) {
	    Recensione r = null;
	    String query = "SELECT r.recensione_id, r.tmdb_film_id, r.utente_id, r.commento, r.data_recensione, "
	            + "u.username, u.email, u.password, u.ruolo_id "
	            + "FROM recensioni r JOIN utenti u ON r.utente_id = u.utente_id "
	            + "WHERE r.recensione_id = ?";
	    PreparedStatement st = null;
	    ResultSet rs = null;

	    try {
	        st = connection.prepareStatement(query);
	        st.setInt(1, id);
	        rs = st.executeQuery();

	        if (rs.next()) {
	            int recId = rs.getInt("recensione_id");
	            String tmdb = rs.getString("tmdb_film_id");
	            String comment = rs.getString("commento");
	            LocalDateTime data = rs.getTimestamp("data_recensione").toLocalDateTime();

	            // Creare un oggetto Utente
	            int utenteId = rs.getInt("utente_id");
	            String username = rs.getString("username");
	            String email = rs.getString("email");
	            String password = rs.getString("password");
	            Ruolo ruolo = RuoloDAO.getRuoloById(rs.getInt("ruolo_id"), connection);

	            Utente u = new Utente(utenteId, username, email, password, ruolo);

	            // Creare un oggetto Recensione includendo anche il campo `username`
	            r = new Recensione(recId, tmdb, u, username, comment, data);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null)
	                rs.close();
	            if (st != null)
	                st.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return r;
	}


	public static List<Recensione> getRecensioniByFilmId(String tmdbFilmId, Connection connection) {
	    List<Recensione> recensioni = new ArrayList<>();
	    String query = "SELECT r.recensione_id, r.tmdb_film_id, r.utente_id, r.commento, r.data_recensione, "
	            + "u.username, u.email, u.password, u.ruolo_id "
	            + "FROM recensioni r JOIN utenti u ON r.utente_id = u.utente_id WHERE r.tmdb_film_id = ?";
	    PreparedStatement st = null;
	    ResultSet rs = null;

	    try {
	        st = connection.prepareStatement(query);
	        st.setString(1, tmdbFilmId);
	        rs = st.executeQuery();

	        while (rs.next()) {
	            int recId = rs.getInt("recensione_id");
	            String tmdb = rs.getString("tmdb_film_id");
	            String comment = rs.getString("commento");
	            LocalDateTime data = rs.getTimestamp("data_recensione").toLocalDateTime();

	            // Creare un oggetto Utente
	            int utenteId = rs.getInt("utente_id");
	            String username = rs.getString("username");
	            String email = rs.getString("email");
	            String password = rs.getString("password");
	            Ruolo ruolo = RuoloDAO.getRuoloById(rs.getInt("ruolo_id"), connection);

	            Utente u = new Utente(utenteId, username, email, password, ruolo);

	            // Creare la recensione
	            Recensione r = new Recensione(recId, tmdb, u, username, comment, data);
	            recensioni.add(r);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null)
	                rs.close();
	            if (st != null)
	                st.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return recensioni;
	}
}