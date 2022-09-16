var h1 = document.getElementById('h1');
var h2 = document.getElementById('h2');

var secJ1 = 300;

var secJ2 = 300;

var increment = 2;



var t;
var tJ2;

function tick() {
    secJ1--;
}

function add() {
    tick();
    h1.textContent =
    ( secJ1/60 > 9 ? Math.trunc(secJ1/60) : "0" + Math.trunc(secJ1/60))
        + ":" + (secJ1%60 > 9 ? secJ1%60 : "0" + secJ1%60);
    timerJ1();
}

function timerJ1() {
    t = setTimeout(add, 1000);
}

function stopJ1() {
    clearTimeout(t);
    timerJ2();
    secJ1 += increment;
}



// function stop(t,sec,h,t2){
//     clearTimeout(t);
//     timer(t2);
//     sec += increment;
// }

// function timer(t,h){
//     t = setTimeout(add(t,sec,h), 1000);
// }

// function add(t,sec,h){
//     tick(t);
//     h.textContent =
//     ( sec/60 > 9 ? Math.trunc(sec/60) : "0" + Math.trunc(sec/60))
//         + ":" + (sec%60 > 9 ? sec%60 : "0" + sec%60);
//     timer(t);
// }

// function tick(sec){
//     sec--;
// }





function tickJ2() {
   
    secJ2--;
}

function addJ2() {
    tickJ2();
    h2.textContent =
    ( secJ2/60 > 9 ? Math.trunc(secJ2/60) : "0" + Math.trunc(secJ2/60))
    + ":" + (secJ2%60 > 9 ? secJ2%60 : "0" + secJ2%60);
    timerJ2();
}

function timerJ2() {
    tJ2 = setTimeout(addJ2, 1000);
}

function stopJ2() {
    clearTimeout(tJ2);
    timerJ1();
    secJ2 += increment;
}

export{timerJ1, stopJ1, stopJ2, secJ1,secJ2}