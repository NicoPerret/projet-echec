
document
.querySelector('button.open-popup-jcj')
.addEventListener('click', () => {
    document
	.querySelector('.popup-overlay')
	.classList.add('open');
	document
	.querySelector('.popup-container')
	.classList.add('open');
	document
	.querySelector('.popup-content > form > table.jcj')
	.classList.add('open');
});

document
.querySelector('button.open-popup-jcia')
.addEventListener('click', () => {
    document
	.querySelector('.popup-overlay')
	.classList.add('open');
	document
	.querySelector('.popup-container')
	.classList.add('open');
	document
	.querySelector('.popup-content > form > table.jcia')
	.classList.add('open');
});

document
.querySelector('button.open-popup-probleme')
.addEventListener('click', () => {
    document
	.querySelector('.popup-overlay')
	.classList.add('open');
	document
	.querySelector('.popup-container')
	.classList.add('open');
	document
	.querySelector('.popup-content > form > table.probleme')
	.classList.add('open');
});

document
.querySelector('button.close-popup')
.addEventListener('click', () => {
    document.
	querySelector('.popup-overlay').
	classList.remove('open');
	document.
	querySelector('.popup-container').
	classList.remove('open');
	
	const listTab =  document.querySelectorAll('.popup-content > form > table');
	for (let tab of listTab) {
		tab.classList.remove('open');
	}
	
});