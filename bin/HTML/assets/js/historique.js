
for (let i = 0; i <= 10; i ++ ){

    const utilisateur = "J1";
    const adversaire = "J2";
    const typePartie = "Blitz";
    const resultat = "V";
    const date = "15/09/2022";
    
    // Fabriquer la ligne du tableau
    const tableRow = document.createElement('tr');
    
    // Modifier le contenu de la ligne du tableau
    tableRow.innerHTML = `<td>${utilisateur}</td>`;
    tableRow.innerHTML += `<td>${adversaire}</td>`;
    tableRow.innerHTML += `<td>${typePartie}</td>`;
    tableRow.innerHTML += `<td>${resultat}</td>`;
    tableRow.innerHTML += `<td>${date}</td>`;
    
    
    
    
    document.querySelector('tbody').append(tableRow);
}
