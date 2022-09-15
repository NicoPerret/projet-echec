
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