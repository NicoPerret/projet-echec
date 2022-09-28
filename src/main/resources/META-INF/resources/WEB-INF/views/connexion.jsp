<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Connexion</title>
</head>
<body>
	<section>
        <form method="POST">
            <!-- <legend style="color: white;">Connexion</legend> -->
            <div class = "fenetre">
                <p>
                    <h2 style="text-align: center;">Connexion</h2>
                </p>
                <table>
                    <tr>
                        <td><label>Identifiant : </label></td>
                        <td><input type="text" name="username" id="ident" placeholder="Entrez votre identifiant"/></td>
                    </tr>
                    <tr>
                        <td><label>Mot de passe : </label></td>
                        <td><input type="password" name="password" id="mdp" placeholder="Entrez votre mdp"/></td>
                    </tr>
                </table>
            </div>
            <div name="validation" style="text-align:center">
                <p>
                    <button type="submit" class = "boutonconnexion" >Se connecter</button>
                    <button class = "boutoninscription">S'inscrire</button>
                </p>
                <p>
                    <a href="PageInscription.html" target="_self" style="color:white;">Mot de passe oublié</a>
                </p>
            </div> 
        </form>
    </section>
</body>
</html>