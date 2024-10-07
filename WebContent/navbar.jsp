<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="navbar.css">
    <link rel="stylesheet" href="style.css">
    <title>Junkin Space</title>
</head>

<body>
    
<!--NavBar-->
<header>
    <div id="logo">
        <button class="logo" onclick="window.location.href='homepage.jsp';" >
            <h1>Junkin</h1>
            <img src="immagini/Logo_1.png" alt="" style="width: 50px; height: 50px;">
            <h1>Space</h1>
        </button>
        </div>
        <div class="menu">
            <button class="button-navbar" onclick="window.location.href='film.jsp'">Film</button>
            <button class="button-navbar" onclick="window.location.href='serietv.jsp'">Serie TV</button>
            <button class="button-navbar" onclick="window.location.href='anime.jsp'">Anime</button>
            <button class="button-navbar" onclick="window.location.href='profilo.jsp'">Account</button>
            <button class="button-navbar" onclick="window.location.href='<%= request.getContextPath() %>/logout'">Logout</button>

        </div>
        <div class="ricerca">
            <form id="form" class="head-form">
                <input type="text" placeholder="Cerca la tua spazzatura" id="search" class="search">
            </form>
        </div>
    </header>
        
<!--Footer
<footer>
    <p>&copy; 2024 Junkin Space</p>
</footer>
-->
</body>
</html>