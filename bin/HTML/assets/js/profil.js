const modif = document.getElementById("modifier");
const valid = document.getElementById('valider');

modif.addEventListener('click',() =>{
    document.getElementById("pseudo").removeAttribute('disabled');
    document.getElementById("mdp").removeAttribute('disabled');
    document.getElementById("prenom").removeAttribute('disabled');
    document.getElementById("nom").removeAttribute('disabled');
    document.getElementById("mail").removeAttribute('disabled');
    document.getElementById("valider").removeAttribute('disabled');
    document.getElementById("modifier").setAttribute('disabled', 'true');
    
})

valid.addEventListener('click',() =>{
    document.getElementById("pseudo").setAttribute('disabled', 'true');
    document.getElementById("mdp").setAttribute('disabled', 'true');
    document.getElementById("prenom").setAttribute('disabled', 'true');
    document.getElementById("nom").setAttribute('disabled', 'true');
    document.getElementById("mail").setAttribute('disabled', 'true');
    document.getElementById("valider").setAttribute('disabled', 'true');
    document.getElementById("modifier").removeAttribute('disabled');
    
})