function dragDrop(coupspossibles) {
  // let bonjour = document.querySelector("#C8");
  // let img = document.createElement("img");
  // img.setAttribute("style", "max-width: 100%;");
  // img.setAttribute("id", "roi-blanc");
  // img.setAttribute("src", "assets/SpritePiecesPNG/ROIBLANC.png");
  // img.setAttribute("draggable", "true");
  // bonjour.append(img);

  // bonjour = document.querySelector("#D4");
  // img = document.createElement("img");
  // img.setAttribute("style", "max-width: 100%;");
  // img.setAttribute("id", "roi-noir");
  // img.setAttribute("src", "assets/SpritePiecesPNG/ROINOIR.png");
  // img.setAttribute("draggable", "true");
  // bonjour.append(img);

  let possibilites = [];
  let cpt = 1;
  let bool = true;
  let audio = new Audio("assets/372.mp3");
  console.log(document.querySelectorAll("img"));
  for (let i of document.querySelectorAll("img")) {
    i.addEventListener("dragstart", (e) => {
      e.dataTransfer.setData("piece-id", e.target.id);

      possibilites = [];
      // possibilites.push("C3", "C2", "F1", "A5");
      // (async () => {
      //   let posts = await fetch(
      //     `http://localhost:8080/projet-echecs/api/coupspossibles/coord=${coordDepart}`
      //   ).then((resp) => resp.json());
      // })();

      if (bool) {
        bool = false;
        // timerJ1();
      }
      for (let pos of coupspossibles) {
        document.querySelector(`#${pos}`).style.background = "green";
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
      console.log(target);

      if (possibilites.find((pos) => pos === target.getAttribute("id"))) {
        const pieceId = e.dataTransfer.getData("piece-id");
        const piece = document.querySelector(`#${pieceId}`);

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
    });
  }
}
