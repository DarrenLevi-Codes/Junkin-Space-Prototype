<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="navbar.css">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="film.css">
    <title>Film - Junkin Space</title>
</head>

<body>
     
<!--NavBar-->
    <!-- Includi la navbar -->
    <div id="navbar"></div>
    
   <script>
    // Carica la navbar
    fetch('navbar.jsp')
        .then(response => response.text())
        .then(data => {
            document.getElementById('navbar').innerHTML = data;

            // Dopo aver caricato la navbar, inizializza gli eventi JavaScript
            initializeSearch();
        });

    // Funzione per inizializzare la ricerca dopo il caricamento della navbar
    function initializeSearch() {
        const form = document.getElementById('form');
        const search = document.getElementById('search');

        if (form && search) {
            form.addEventListener("submit", (e) => {
                e.preventDefault();

                const searchTerm = search.value;
                selectedGenre = [];
                setGenre();

                if (searchTerm) {
                    getMovies(searchURL + "&query=" + searchTerm);
                } else {
                    getMovies(API_URL);
                }
            });
        }
    }
</script>


    
<!--Contenuto Pagina-->
    <div id="tags"></div>
    <main id="main"></main>
    
<!--Footer-->    
    <footer>
        <p>&copy; 2024 Junkin Space</p>
    </footer>

    <script src="film.js"></script>
</body>
</html>