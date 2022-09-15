var h1 = document.getElementById('h1');
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
    timerJ1();
}

function timerJ1() {
    t = setTimeout(add, 1000);
}

function stopJ1() {
    clearTimeout(t);
    timerJ2();
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

function stopJ2() {
    clearTimeout(tJ2);
    timerJ1();
}

export{timerJ1, stopJ1, stopJ2}