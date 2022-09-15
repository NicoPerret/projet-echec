
for (let i = 0; i < 8; i++) {
    const c = document.createElement('div');
    c.innerHTML = 8 - i;
    document.querySelector('.grillePlateau1a8').append(c);
}

const cases = [];
const lettres = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'];
for (let i = 0; i < 64; i++) {
    const c = document.createElement('div');

    c.setAttribute('name', lettres[(i % 8)] + (8 - Math.floor(i / 8, 1)));
    cases.splice(0, 0, c);
    document.querySelector('.grillePlateauPrincipal').append(c);
}

for (let i = 0; i < 8; i++) {
    const c = document.createElement('div');

    c.innerHTML = lettres[i];
    document.querySelector('.flexPlateauAaH').append(c);
}
