import { timerJ1, stopJ1, stopJ2, secJ1,secJ2 } from "./chronos.js";

const couleurJoueur = "blanc"
const audio = new Audio('assets/372.mp3');
const typePartie = "1v2";

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
    document.querySelector('.grillePlateauAaH').append(c);
}

if (couleurJoueur == "blanc"){
    document.querySelector(".grillePlateauPrincipal").classList.add('blanc');
}else{
    document.querySelector(".grillePlateauPrincipal").classList.add('noir');
    document.querySelector(".grillePlateauPrincipal").classList.add('rotationY');
    document.querySelector(".grillePlateau1a8").classList.add('rotationY');
    document.querySelector(".grillePlateauAaH").classList.add('rotationX');
}

if (typePartie == '1v1'){
    document.getElementById('h1').innerHTML = ( secJ1/60 > 9 ? Math.trunc(secJ1/60) : "0" + Math.trunc(secJ1/60))
    + ":" + (secJ1%60 > 9 ? secJ1%60 : "0" + secJ1%60);
    document.getElementById('h2').innerHTML = ( secJ2/60 > 9 ? Math.trunc(secJ2/60) : "0" + Math.trunc(secJ2/60))
    + ":" + (secJ2%60 > 9 ? secJ2%60 : "0" + secJ2%60);
}else{
    document.querySelector(".btnNul").classList.add("caché");
}


const bonjour = document.querySelector('div[name="C8"]');
const img = document.createElement('img');
img.setAttribute('id', 'roi-noir');
img.setAttribute('src','assets/SpritePiecesPNG/RoiBlanc.png');
img.setAttribute('draggable','true'); 
bonjour.append(img);

const bonjour2 = document.querySelector('div[name="D4"]');
const img2 = document.createElement('img');
img2.setAttribute('id', 'roi-noir2');
img2.setAttribute('src','assets/SpritePiecesPNG/RoiNoir.png');
img2.setAttribute('draggable','true');
bonjour2.append(img2);

document.addEventListener('drop', (e) => {
    e.preventDefault();
});

let possibilites = [];
let cpt = 1;
let bool = true;

for (let i of document.querySelectorAll('.grillePlateauPrincipal div img')) {
    i.addEventListener('dragstart', (e) => {
        e.dataTransfer.setData("piece-id", e.target.id);
        possibilites = [];
        possibilites.push("C3", "C2", "F1");
        if (bool){
            bool = false;
            timerJ1();
        }
        for (let pos of possibilites) {
            document.querySelector(`div[name="${ pos }"]`).style.background = "green";
        }
    });
}

for (let div of document.querySelectorAll('.grillePlateauPrincipal div')) {
    div.addEventListener('dragenter', (e) => {
        e.preventDefault();

        if (possibilites.find(pos => pos === e.target.getAttribute('name'))) {
            e.target.style.background = "red";
        }
    });

    div.addEventListener('dragleave', (e) => {
        e.preventDefault();
        if (possibilites.find(pos => pos === e.target.getAttribute('name'))) {
            e.target.style.background = "green";
        }
    });

    div.addEventListener('dragover', (e) => {
        e.preventDefault();
    });

    div.addEventListener('drop', (e) => {
        const target = e.target.closest('div');
        console.log(target)

        if (possibilites.find(pos => pos === target.getAttribute('name'))) {
            const pieceId = e.dataTransfer.getData("piece-id");
            const piece = document.querySelector(`#${ pieceId }`);
        
            target.innerHTML = '';
            target.append(piece);
            //exécution bruit POC  
            audio.play();

            if (cpt %2==0){
                stopJ2();
            }else{
                stopJ1();
            }
            cpt++;
        }
        for (let pos of possibilites) {
            document.querySelector(`div[name="${ pos }"]`).style.background = "";
        }
    });
}
