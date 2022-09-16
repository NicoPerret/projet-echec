
const fen_to_img = "http://www.fen-to-image.com/image/"

for (let i = 0; i <= 10; i ++ ){

    const pro_id = i;
	const pro_fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR"
    const pro_image_lien = fen_to_img + pro_fen;
    const pro_difficulte = "facile";
    const pro_resultat = "NON";
	
	const img = document.createElement("img");
	img.src = pro_image_lien
    
    // Fabriquer la ligne du tableau
    const tableRow = document.createElement('tr');
    
    // Modifier le contenu de la ligne du tableau
    tableRow.innerHTML += `<td>${pro_id}</td>`;
    tableRow.innerHTML += `<td><img src=${pro_image_lien} alt="img"></td>`;
    tableRow.innerHTML += `<td>${pro_difficulte}</td>`;
    tableRow.innerHTML += `<td>${pro_resultat}</td>`;
    
    
    document.querySelector('tbody').append(tableRow);
}
