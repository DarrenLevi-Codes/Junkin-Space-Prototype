// Costanti per l'uso dell'API
const API_KEY = "api_key=1713308ebdf16589946ead11346842ef";
const BASE_URL = "https://api.themoviedb.org/3";
const IMG_URL = "https://image.tmdb.org/t/p/w500";

window.onload = function() {
	const urlParams = new URLSearchParams(window.location.search);
	const movieId = urlParams.get('movieId');

	if (movieId) {
		// Mostra i dettagli del film utilizzando TMDB
		fetch(`${BASE_URL}/movie/${movieId}?${API_KEY}`)
			.then(response => response.json())
			.then(movie => {
				if (movie) {
					// Popola il titolo del film
					document.getElementById('movie-title').innerText = movie.title;

					// Popola la descrizione del film
					document.getElementById('movie-overview').innerText = movie.overview;

					// Popola la locandina del film
					if (movie.poster_path) {
						document.getElementById('movie-poster').src = `${IMG_URL}${movie.poster_path}`;
					} else {
						document.getElementById('movie-poster').src = "http://via.placeholder.com/200x300";
					}

					// Recupera e mostra le recensioni associate al film
					getReviews(movieId);
				} else {
					console.error("Errore nel recupero dei dati del film");
				}
			})
			.catch(error => {
				console.error("Errore durante il recupero dei dettagli del film:", error);
			});
	}

	// Aggiungi logica per salvare la recensione
	const reviewForm = document.getElementById('review-form');
	reviewForm.addEventListener('submit', function(e) {
		e.preventDefault();
		const reviewText = document.getElementById('review-text').value;

		if (reviewText) {
			// Crea una richiesta POST per inviare la recensione al server
			fetch(`${contextPath}/aggiungiRecensione`, {
				method: 'POST',
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded'
				},
				body: `tmdbFilmId=${movieId}&commento=${encodeURIComponent(reviewText)}`
			})
				.then(response => {
					if (response.ok) {
						// Mostra un messaggio di conferma e ricarica la pagina per vedere la nuova recensione
						alert("Review submitted successfully!");
						window.location.reload();
					} else {
						alert("Failed to submit review. Please try again.");
					}
				})
				.catch(error => {
					console.error("Errore durante l'invio della recensione:", error);
					alert("Errore durante l'invio della recensione.");
				});

			// Svuota il campo di testo della recensione
			document.getElementById('review-text').value = '';
		}
	});
};

// Funzione per recuperare le recensioni dal server
function getReviews(movieId) {
	fetch(`${contextPath}/getRecensioni?movieId=${movieId}`)
		.then(response => response.json())
		.then(data => {
			const userReviews = document.getElementById('user-reviews');
			userReviews.innerHTML = "<h3>User Reviews</h3>"; // Ripristina il titolo

			data.forEach(review => {
				const reviewElement = document.createElement('div');
				reviewElement.classList.add('user-review');

				// Estrai il nome utente dal campo utente
				const username = review.utente.username;

				// Costruisci l'elemento HTML per la recensione
				reviewElement.innerHTML = `<p><strong>${username}:</strong> ${review.commento}</p><hr>`;
				userReviews.appendChild(reviewElement);
			});
		})
		.catch(error => {
			console.error("Errore durante il recupero delle recensioni:", error);
		});
}