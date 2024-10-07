<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <link rel="stylesheet" href="navbar.css">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="loginregister.css">
    <title>Login - Junkin Space</title>
</head>

<body>
     
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
        <div class="container" id="container">
            <div class="form-container sign-up">
                <form action="${pageContext.request.contextPath}/registrazione" method="post">
                    <h1>Crea un Account</h1>
                    <div class="social-icons">
                        <a href="#" class="icon"><i class="fa-brands fa-google-plus-g"></i></a>
                        <a href="#" class="icon"><i class="fa-brands fa-facebook-f"></i></a>
                        <a href="#" class="icon"><i class="fa-brands fa-github"></i></a>
                        <a href="#" class="icon"><i class="fa-brands fa-linkedin-in"></i></a>
                    </div>
                    <span>o utilizza la tua email per registrarti</span>
                    <input type="text" name="username" placeholder="Nome" required>
    				<input type="email" name="email" placeholder="Email" required>
    				<input type="password" name="password" placeholder="Password" required>
    				<button type="submit">Registrati</button>
                </form>
            </div>
            <div class="form-container sign-in">
                <form action="${pageContext.request.contextPath}/login" method="post">
                    <h1>Accedi</h1>
                    <div class="social-icons">
                        <a href="#" class="icon"><i class="fa-brands fa-google-plus-g"></i></a>
                        <a href="#" class="icon"><i class="fa-brands fa-facebook-f"></i></a>
                        <a href="#" class="icon"><i class="fa-brands fa-github"></i></a>
                        <a href="#" class="icon"><i class="fa-brands fa-linkedin-in"></i></a>
                    </div>
                    <span>o utilizza la tua email e password</span>
                    <input type="email" name="email" placeholder="Email">
                    <input type="password" name="password" placeholder="Password">
                    <a href="#">Dimenticata la tua Junkin Password?</a>
                    <button>Entra</button>
                </form>
            </div>
            <div class="toggle-container">
                <div class="toggle">
                    <div class="toggle-panel toggle-left">
                        <h1>Bentornato!</h1>
                        <p>Inserisci le tue credeziali per utilizzare tutte le features</p>
                        <button class="hidden" id="login">Accedi</button>
                    </div>
                    <div class="toggle-panel toggle-right">
                        <h1>Benvenuto, Junk!</h1>
                        <p>Registrati con le tue credeziali per utilizzare tutte le features</p>
                        <button class="hidden" id="register">Registrati</button>
                    </div>
                </div>
            </div>
        </div>
    </main>

<!--Footer-->    
   <footer>
        <p>&copy; 2024 Junkin Space</p>
    </footer>

    <script src="loginregister.js"></script>
</body>

</html>