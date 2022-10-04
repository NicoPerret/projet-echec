
const fen_to_img = "http://www.fen-to-image.com/image/"

const liste_fen = 
["r6k/pp2r2p/4Rp1Q/3p4/8/1N1P2R1/PqP2bPP/7K",
 "5rk1/1p3ppp/pq3b2/8/8/1P1Q1N2/P4PPP/3R2K1",
 "r2qr1k1/b1p2ppp/pp4n1/P1P1p3/4P1n1/B2P2Pb/3NBP1P/RN1QR1K1",
 "4r3/1k6/pp3r2/1b2P2p/3R1p2/P1R2P2/1P4PP/6K1",
 "r4rk1/pp3ppp/2n1b3/q1pp2B1/8/P1Q2NP1/1PP1PP1P/2KR3R",
 "r1bqk2r/pp1nbNp1/2p1p2p/8/2BP4/1PN3P1/P3QP1P/3R1RK1",
 "5r1k/5rp1/p7/1b2B2p/1P1P1Pq1/2R1Q3/P3p1P1/2R3K1",
 "3R4/8/K7/pB2b3/1p6/1P2k3/3p4/8",
 "4r3/5pk1/1p3np1/3p3p/2qQ4/P4N1P/1P3RP1/7K",
 "r2q1rk1/5ppp/1np5/p1b5/2p1B3/P7/1P3PPP/R1BQ1RK1",
 "2kr3r/pp3p2/4p2p/1N1p2p1/3Q4/1P1P4/2q2PPP/5RK1",
 "4r1k1/5ppp/r1p5/p1n1RP2/8/2P2N1P/2P3P1/3R2K1'",
 "1qr2rk1/pb2bppp/8/8/2p1N3/P1Bn2P1/2Q2PBP/1R3RK1",
 "r6r/1pNk1ppp/2np4/b3p3/4P1b1/N1Q5/P4PPP/R3KB1R",
 "5r1k/pp4pp/5p2/1BbQp1r1/6K1/7P/1PP3P1/3R3R"];


for (let i = 0; i < liste_fen.length; i ++ ){

    const pro_id = i;
	const pro_fen = liste_fen[i];
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
    
    tableRow
	.addEventListener('click', () => {
		console.log("probleme " + pro_id + " choisi !")
		/*document.location.href='PageJeu.html';*/
	});
	
    document.querySelector('tbody').append(tableRow);
}



