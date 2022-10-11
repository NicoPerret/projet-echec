function dragDrop(possibilites) {
  let coordArrivee;
  (async () => {
    let cpt = 1;
    let bool = true;
    let audio = new Audio("assets/372.mp3");

    for (let i of document.querySelectorAll("img")) {
      i.addEventListener("dragstart", (e) => {
        console.log(possibilites);
        e.dataTransfer.setData("piece-id", e.target.id);

        for (let pos of possibilites) {
          document.querySelector(`#${pos}`).style.background = "green";
        }
        if (bool) {
          bool = false;
          // timerJ1();
        }
      });
    }

    for (let div of document.querySelectorAll(".grillePlateauPrincipal div")) {
      div.addEventListener("dragenter", (e) => {
        e.preventDefault();

        if (possibilites.find((pos) => pos === e.target.getAttribute("id"))) {
          e.target.style.background = "red";
        }
      });

      div.addEventListener("dragleave", (e) => {
        e.preventDefault();
        if (possibilites.find((pos) => pos === e.target.getAttribute("id"))) {
          e.target.style.background = "green";
        }
      });

      div.addEventListener("dragover", (e) => {
        e.preventDefault();
      });

      div.addEventListener("drop", (e) => {
        const target = e.target.closest("div");

        if (possibilites.find((pos) => pos === target.getAttribute("id"))) {
          const pieceId = e.dataTransfer.getData("piece-id");

          const piece = document.querySelector(`#${pieceId}`);
          //coordArrivee = target.getAttribute("id");

          target.innerHTML = "";
          target.append(piece);
          //exécution bruit POC
          audio.play();

          /* if (cpt % 2 == 0) {
          stopJ2();
        } else {
          stopJ1();
        }
        cpt++;*/
        }
        for (let pos of possibilites) {
          document.querySelector(`#${pos}`).style.background = "";
        }
        possibilites = [];
      });
    }
  })();
  // console.log("fin" + possibilites);
  //console.log("arrive" + coordArrivee);
  //return [coordonnee, coordArrivee];
}
