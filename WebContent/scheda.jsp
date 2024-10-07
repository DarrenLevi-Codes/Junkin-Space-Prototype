<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="navbar.css">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="scheda.css">
    <title>Junkin Space</title>
</head>

<body>

<script>
    const contextPath = '<%= request.getContextPath() %>';
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

    <div id="tags"></div>
    <main id="main" class="review-page">
        <!-- Sezione superiore: Locandina del film e descrizione -->
        <div class="movie-details-container">
            <img id="movie-poster" class="movie-poster" src="" alt="Movie Poster">
            <div class="movie-description">
                <h2 id="movie-title"></h2>
                <p id="movie-overview"></p>
            </div>
        </div>

        <!-- Sezione delle recensioni -->
        <div class="reviews-section">
            <div class="user-reviews" id="user-reviews">
                <h3>User Reviews</h3>
                <!-- Le recensioni degli utenti verranno aggiunte qui dinamicamente -->
            </div>

            <!-- Sezione per aggiungere una nuova recensione -->
            <div class="add-review">
                <h3>Add Your Review</h3>
                <form id="review-form" action="<%= request.getContextPath() %>/aggiungiRecensione" method="post">
                    <textarea id="review-text" name="commento" placeholder="Write your review here..." rows="6" cols="50"></textarea>
                    <input type="hidden" name="tmdbFilmId" value="<%= request.getParameter("movieId") %>">
                    <button type="submit" class="know-more">Submit Review</button>
                </form>
            </div>
        </div>
    </main>

<!--Footer-->    
    <footer>
        <p>&copy; 2024 Junkin Space</p>
    </footer>

    <script src="scheda.js"></script>

</body>

</html>