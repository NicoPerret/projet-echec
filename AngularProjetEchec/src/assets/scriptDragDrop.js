function dragDrop() {
  // for (let piece of ){
  //   let bonjour = document.querySelector("#C8");
  //   let img = document.createElement("img");
  //   img.setAttribute("style", "max-width: 100%;");
  //   img.setAttribute("id", );
  //   img.setAttribute("src", "assets/SpritePiecesPNG/RoiBlanc.png");
  //   img.setAttribute("draggable", "true");
  //   bonjour.append(img);
  // }

  let bonjour = document.querySelector("#C8");
  let img = document.createElement("img");
  img.setAttribute("style", "max-width: 100%;");
  img.setAttribute("id", "roi-blanc");
  img.setAttribute("src", "assets/SpritePiecesPNG/RoiBlanc.png");
  img.setAttribute("draggable", "true");
  bonjour.append(img);

  const bonjour2 = document.querySelector("#D4");
  const img2 = document.createElement("img");
  img2.setAttribute("style", "max-width: 100%;");
  img2.setAttribute("id", "roi-noir");
  img2.setAttribute("src", "assets/SpritePiecesPNG/RoiNoir.png");
  img2.setAttribute("draggable", "true");
  bonjour2.append(img2);

  let possibilites = [];
  let cpt = 1;
  let bool = true;
  let audio = new Audio("assets/372.mp3");

  // @hostlistener a mettre sur le component

  for (let i of document.querySelectorAll("img")) {
    i.addEventListener("dragstart", (e) => {
      e.dataTransfer.setData("piece-id", e.target.id);
      console.log(i.parentNode.id);
      possibilites = [];
      possibilites.push("C3", "C2", "F1", "A5");
      // (async () => {
      //   let posts = await fetch(
      //     `http://localhost:8080/projet-echecs/api/coupspossibles/coord=${coordDepart}`
      //   ).then((resp) => resp.json());

      //   for (let post of posts.filter((post) => post.id < 5)) {
      //     console.log(post.title);
      //   }
      // })();

      if (bool) {
        bool = false;
        // timerJ1();
      }
      for (let pos of possibilites) {
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
