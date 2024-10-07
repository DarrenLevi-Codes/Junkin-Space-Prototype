<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Utente"%>
<%
model.Utente utente = (model.Utente) session.getAttribute("utenteLoggato");
if (utente == null) {
	// Se l'utente non è nella sessione, aggiungi un messaggio di debug
	out.println("Utente non trovato nella sessione. Reindirizzamento al login...");
	response.sendRedirect("loginregister.jsp");
	return;
} else {
	out.println("Utente trovato nella sessione: " + utente.getUsername());
}
%>



<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="navbar.css">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="profilo.css">
<title>User - Junkin Space</title>
</head>
<body>

	<script>
    const contextPath = '<%=request.getContextPath()%>';
</script>



	<!-- Includi la navbar -->
	<div id="navbar"></div>

	<script>
        // Carica la navbar
        fetch('navbar.jsp')
        .then(response => response.text())
        .then(data => {
            document.getElementById('navbar').innerHTML = data;
        });
    </script>

	<!--Contenuto Pagina-->
	<main>
		<div class="welcome-user">
			<h1>Benvenuto Junkin!</h1>
		</div>
		<div class="container">
			<div class="box" id="dati">
				<h2>I tuoi dati</h2>
				<form id="profile-form">
					<div class="form-group">
						<label for="username">Username</label>
						<div class="text-display" id="username-display"><%=utente.getUsername()%></div>
						<input type="text" id="username" name="username"
							value="<%=utente.getUsername()%>" style="display: none;">
					</div>
					<div class="form-group">
						<label for="email">Email</label>
						<div class="text-display" id="email-display"><%=utente.getEmail()%></div>
						<input type="email" id="email" name="email"
							value="<%=utente.getEmail()%>" style="display: none;">
					</div>
					<div class="form-group">
						<label for="password">Password</label>
						<div class="text-display" id="password-display">********</div>
						<input type="password" id="password" name="password"
							value="<%=utente.getPassword()%>" style="display: none;">
					</div>
					<input type="submit" value="Salva" id="save-button"
						style="display: none;">
					<button type="button" class="edit-button" id="edit-button">Modifica</button>
				</form>



			</div>
			<div class="box" id="spazzatura">
				<h2>La tua spazzatura</h2>
				<div class="form">
					<input type="text" id="reviews" name="username"
						value="le_tue_recensioni">
				</div>
			</div>
		</div>
	</main>

	<!--Footer-->
	<footer>
		<p>&copy; 2024 Junkin Space</p>
	</footer>

	<script src="profilo.js"></script>
</body>
</html>
