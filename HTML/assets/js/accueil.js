
document
.querySelector('button.open-parametres-jcj')
.addEventListener('click', () => {
	const listTab =  document.querySelectorAll('.parametres-content > form > table');
	for (let tab of listTab) {
		tab.classList.remove('open');
	}
	document
	.querySelector('.parametres-container')
	.classList.add('open');
	document
	.querySelector('.parametres-content > form > table.jcj')
	.classList.add('open');
});

document
.querySelector('button.open-parametres-jcia')
.addEventListener('click', () => {
	const listTab =  document.querySelectorAll('.parametres-content > form > table');
	for (let tab of listTab) {
		tab.classList.remove('open');
	}
	document
	.querySelector('.parametres-container')
	.classList.add('open');
	document
	.querySelector('.parametres-content > form > table.jcia')
	.classList.add('open');
});

document
.querySelector('button.open-parametres-probleme')
.addEventListener('click', () => {
	const listTab =  document.querySelectorAll('.parametres-content > form > table');
	for (let tab of listTab) {
		tab.classList.remove('open');
	}
	document
	.querySelector('.parametres-container')
	.classList.add('open');
	document
	.querySelector('.parametres-content > form > table.probleme')
	.classList.add('open');
});

document
.querySelector('button.close-parametres')
.addEventListener('click', () => {
	document.
	querySelector('.parametres-container').
	classList.remove('open');
	
	const listTab =  document.querySelectorAll('.parametres-content > form > table');
	for (let tab of listTab) {
		tab.classList.remove('open');
	}
	
});

for (let i = 0; i <=5; i ++ ){

    const utilisateur = "J1";
    const adversaire = "J2";
    
    const resultat = "V";
  
    
    // Fabriquer la ligne du tableau
    const tableRow = document.createElement('tr');
    
    // Modifier le contenu de la ligne du tableau
    tableRow.innerHTML = `<td>${utilisateur}</td>`;
    tableRow.innerHTML += `<td>${adversaire}</td>`;
   
    tableRow.innerHTML += `<td>${resultat}</td>`;
    
    
    
    
    
    document.querySelector('tbody.historique').append(tableRow);
}