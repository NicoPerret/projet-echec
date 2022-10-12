import { outputAst, verifyHostBindings } from '@angular/compiler';
import {
  AfterViewInit,
  Component,
  HostListener,
  Input,
  OnInit,
  Output,
  EventEmitter,
} from '@angular/core';
import * as SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import { Utilisateur } from 'src/app/projet/model/utilisateur';

declare function dragDrop(coupsPossibles: string[]): any;
@Component({
  selector: 'app-plateau64cases',
  templateUrl: './plateau64cases.component.html',
  styleUrls: ['./plateau64cases.component.css'],
})
export class Plateau64casesComponent implements OnInit, AfterViewInit {
  @Input()
  couleur!: string;

  private caseArrivee!: any;
  private coord!: string;
  private listeCoups!: string;
  private coupsPossibles!: string[];

  greetings: string[] = [];
  disabled = true;
  name!: string;
  private stompClient: any;
  private listePieces!: any[];

  private prems: boolean = true;
  private plateau!: any;

  public lettres = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'];
  public nombres = [8, 7, 6, 5, 4, 3, 2, 1];

  constructor() {}

  ngAfterViewInit(): void {
    this.connect();

    // dragDrop();
  }

  ngOnInit(): void {
    // this.connect();
    // this.init();
  }

  @HostListener('mousedown') merciJeremyDeNousAider() {
    const options = {
      method: 'POST',
      body: JSON.stringify(this.plateau),
      headers: {
        'Content-Type': 'application/json',
      },
    };

    (async () => {
      this.coupsPossibles = await fetch(
        `http://localhost:8080/projet-echecs/api/coup-possible/${this.coord}`,
        options
      ).then((resp) => resp.json());
      //console.log('apres fetch' + this.coupsPossibles);
      dragDrop(this.coupsPossibles);
    })();
  }

  @HostListener('drop') JeremyJetaime() {
    this.caseArrivee = event?.target;

    //console.log(('arrivee dans hostlistener ' + this.caseArrivee.id) as string);
    if (this.coupsPossibles.find((pos) => pos === this.caseArrivee.id)) {
      this.deplacement(this.coord as string, this.caseArrivee.id as string);
      console.log(this.coord + ' ' + this.caseArrivee.id);
    }
  }

  connect(): any {
    const socket = new SockJS(
      'http://localhost:8080/projet-echecs/gkz-stomp-endpoint'
    );
    this.stompClient = Stomp.over(socket);
    const _this = this;
    this.stompClient.connect({}, function (frame: any) {
      _this.setConnected(true);
      console.log('Connected: ' + frame);
      _this.stompClient.send('/gkz/initialisation', {});
      _this.stompClient.subscribe('/topic/hi', function (hello: any) {
        console.log(hello.body);
        _this.plateau = JSON.parse(hello.body);
        _this.listePieces = JSON.parse(hello.body).pieces;
        if (_this.prems) {
          _this.initImg();
          _this.prems = false;
        }

        // console.log('ahah' + _this.coord);
      });
    });
  }

  initImg() {
    let cpt: number = 0;
    for (let piece of this.listePieces) {
      let divImg = document.querySelector('#' + piece.coordonneeLettre);
      let img = document.createElement('img');
      img.setAttribute('style', 'max-width: 100%;');
      img.setAttribute('id', 'p' + cpt.toString());
      img.setAttribute(
        'src',
        'assets/SpritePiecesPNG/' + piece.nom + piece.couleur + '.png'
      );
      img.setAttribute('draggable', 'true');
      divImg!.append(img);
      cpt++;
    }
  }

  setConnected(connected: boolean) {
    this.disabled = !connected;

    if (connected) {
      this.greetings = [];
    }
  }

  deplacement(coordDep: string, coordArr: string) {
    this.stompClient.send(
      '/gkz/jouer-coup',
      {},
      JSON.stringify({ coupDepart: coordDep, coupArrivee: coordArr })
    );
  }

  addCoord(coordonnee: string) {
    this.coord = coordonnee;
  }
}
