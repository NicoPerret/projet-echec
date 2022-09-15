var h1 = document.getElementsByTagName('h1')[0];
var startJ1 = document.getElementById('strtJ1');
var stopJ1 = document.getElementById('stpJ1');
var resetJ1 = document.getElementById('rstJ1');

var startJ2 = document.getElementById('strtJ2');
var stopJ2 = document.getElementById('stpJ2');
var resetJ2 = document.getElementById('rstJ2');
var h2 = document.getElementById('h2');

var sec = 0;
var min = 5;
var secJ2 = 0;
var minJ2 = 5;

var t;
var tJ2;

function tick() {
    if (sec == 0 && min > 0) {
        sec = 60;
        min--;
    }
    sec--;

}
function add() {
    tick();
    h1.textContent =
        (min > 9 ? min : "0" + min)
        + ":" + (sec > 9 ? sec : "0" + sec);
    timer();
}

function timer() {
    t = setTimeout(add, 1000);
}

startJ1.onclick = function () {
    timer();
   
}


stopJ1.onclick = function () {
    clearTimeout(t);
    timerJ2();
}

resetJ1.onclick = function () {
    h1.textContent = "00:00";
    sec = 0; min = 5;
    clearTimeout(t);
}


function tickJ2() {
    if (secJ2 == 0 && minJ2 > 0) {
        secJ2 = 60;
        minJ2--;
    }
    secJ2--;

}

function addJ2() {
    tickJ2();
    h2.textContent =
        (minJ2 > 9 ? minJ2 : "0" + minJ2)
        + ":" + (secJ2 > 9 ? secJ2 : "0" + secJ2);
    timerJ2();
}
function timerJ2() {
    tJ2 = setTimeout(addJ2, 1000);
}



startJ2.onclick = timerJ2;

stopJ2.onclick = function () {
    clearTimeout(tJ2);
    timer();
}

resetJ2.onclick = function () {
    h2.textContent = "00:00";
    secJ2 = 0; minJ2 = 5;
    clearTimeout(tJ2);
}