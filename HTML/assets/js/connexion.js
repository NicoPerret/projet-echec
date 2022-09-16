const idtest = 'test';   // à modifier, faire lien avec BDD
const mdptest = 'test';


//recupérer bouton connexion
const connect = document.querySelector('button.boutonconnexion');
//recupérer bouton inscription
const subscribe = document.querySelector('button.boutoninscription');



// clic sur le bouton "se connecter" renvoie vers la page accueil (ou id et mdp incorrect en cas d'erreur)
connect.addEventListener('click', () => {
// const identifiant = document.querySelector('.fenetre > input.identifiant');
// const motdepasse = document.querySelector('.fenetre > input.mdp');
const identifiant = document.getElementById('ident').value;
const motdepasse = document.getElementById('mdp').value;
if (identifiant==""){
    alert("Veuillez saisir un identifiant");
    return;
}
    if (motdepasse==""){
        alert("Veuillez saisir un mot de passe");
        return;
    }
if (identifiant==idtest && motdepasse==mdptest){
    document.location.href='PageAccueil.html';
}
else if (identifiant!=idtest){
    //  console.log("Identifiant incorrect");
    alert("Identifiant incorrect");
}
else if (motdepasse!=mdptest){
    // console.log("Mot de passe incorrect")
    alert("Mot de passe incorrect");
}
})


// clic sur le bouton "s'inscrire" renvoie vers la page inscription
 subscribe.addEventListener('click', () => {
    document.location.href='PageInscription.html';
})

