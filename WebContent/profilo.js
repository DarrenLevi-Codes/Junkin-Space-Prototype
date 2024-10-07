document.addEventListener('DOMContentLoaded', function() {
    // Gestione del pulsante "Modifica"
    document.getElementById('edit-button').addEventListener('click', function() {
        // Mostra i campi di input e nascondi il testo
        document.getElementById('username-display').style.display = 'none';
        document.getElementById('username').style.display = 'block';

        document.getElementById('email-display').style.display = 'none';
        document.getElementById('email').style.display = 'block';

        document.getElementById('password-display').style.display = 'none';
        document.getElementById('password').style.display = 'block';

        // Mostra il pulsante di salvataggio
        document.getElementById('save-button').style.display = 'inline-block';
        this.style.display = 'none'; // Nascondi il pulsante "Modifica"
    });

    // Gestione del form di salvataggio del profilo
    document.getElementById('save-button').addEventListener('click', function(event) {
        event.preventDefault(); // Previeni l'invio del form predefinito

        // Recupera i valori dei campi di input
        const username = document.getElementById('username').value.trim();
        const email = document.getElementById('email').value.trim();
        const password = document.getElementById('password').value.trim();

        // Verifica se i valori sono validi
        if (!username || !email || !password) {
            alert("Tutti i campi devono essere compilati correttamente.");
            return;
        }

        // Crea i dati del form per l'invio
        const formData = {
            username: username,
            email: email,
            password: password
        };

        console.log("Dati inviati:", JSON.stringify(formData)); // Log per debug

        fetch(`${contextPath}/updateProfile`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        })
        .then(response => {
            if (response.ok) {
                alert('Profilo aggiornato con successo!');
                window.location.reload();
            } else {
                alert('Errore durante l\'aggiornamento del profilo.');
            }
        })
        .catch(error => {
            console.error('Errore durante l\'aggiornamento del profilo:', error);
            alert('Errore durante l\'aggiornamento del profilo.');
        });
    });
});
