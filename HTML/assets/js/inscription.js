

//initialise le mdp et l'id pour fonctions de vérification
var enregistrerMdp = false;
var enregistrerId = false;
var enregistrerEmail = false;
const idExistants = "test";
const emailExistants = "toto@toto.fr";

//récupère le bouton de connection
const btnvalid = document.querySelector('button.inscription');



btnvalid.addEventListener('click', () => {
    const identifiant = document.getElementById("ident").value;
    const motdepasse = document.getElementById("mdp").value;
    const confirmeMotdepasse = document.getElementById("confirmmdp").value;
    const pseudo = document.getElementById("pseudo").value;
    const nom = document.getElementById("nom").value;
    const prenom = document.getElementById("prenom").value;
    const email = document.getElementById("mail").value;
    if (identifiant==""){
        alert("Veuillez saisir un identifiant");
        return;
    }
    if (motdepasse==""){
        alert("Veuillez saisir un mot de passe");
        return;
    }
    if (confirmeMotdepasse==""){
        alert("Veuillez confirmer votre mot de passe");
        return;
    }
    if (pseudo==""){
        alert("Veuillez saisir un pseudo");
        return;
    }
    if (nom==""){
        alert("Veuillez saisir votre nom");
        return;
    }
    if (prenom==""){
        alert("Veuillez saisir votre prenom");
        return;
    }
    if (email==""){
        alert("Veuillez saisir une adresse e-mail");
        return;
    }
    verifId(identifiant);
    verifMdp(motdepasse);
    verifEmail(email);

    if (enregistrerId == true && enregistrerMdp == true && enregistrerEmail == true) {
        document.location.href = 'PageAccueil.html';
        //penser a enregistrer les coordonnées dans la BDD
    }
    })


function verifId() {
    if (document.getElementById("ident").value != idExistants) {
        enregistrerId = true;
    }
    else {
        // console.log("Cet identifiant est déjà utilisé")
        alert("Cet identifiant est déjà utilisé");
    }
}

// vérification du mot de passe pour savoir s'il est identique avec la confirmation du mot de passe
function verifMdp() {
    if (document.getElementById("mdp").value == document.getElementById("confirmmdp").value) {
        enregistrerMdp = true;
    }
    else {
        // console.log ("Le mot de passe est incorrect")
        alert("Le mot de passe est incorrect");
    }
}

function verifEmail() {
    if (document.getElementById("mail").value != emailExistants) {
        enregistrerEmail = true;
    }
    else {
        // console.log("cette adresse mail est déjà utilisée")
        alert("cette adresse mail est déjà utilisée");
    }
}
