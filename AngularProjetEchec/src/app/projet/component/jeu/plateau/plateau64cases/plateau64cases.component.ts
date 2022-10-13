import { outputAst, verifyHostBindings } from '@angular/compiler';
import {
  AfterViewInit,
  Component,
  HostListener,
  Input,
  OnInit,
  Output,
  EventEmitter,
  ElementRef,
  ViewChild,
} from '@angular/core';
import * as SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import { Utilisateur } from 'src/app/projet/model/utilisateur';
import { ActivatedRoute, Router } from '@angular/router';

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
  private id!: number;
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

  public modeJeu!: string;
  public initialisationMapping!: string;
  public jouerCoupMapping!: string;
  public destinationMapping!: string;

  public problemeId?: number;

  @ViewChild('eventTarget')
  private ER!: ElementRef;

  constructor(private activatedRoute: ActivatedRoute, private router: Router) {}

  ngAfterViewInit(): void {
    this.connect();
  }

  ngOnInit(): void {

    this.activatedRoute.params.subscribe((params) => {
      this.modeJeu = params['modeJeu'];
      //this.problemeId = params['problemeId'];
      this.problemeId = params['problemeId'];

      console.log(params);
      console.log("MODE JEU : " +  this.modeJeu);
      console.log("PROBLEME ID : " +this.problemeId);

      // this.modeJeu="Probleme";
      if (this.modeJeu=="JcJ") {
        this.initialisationMapping='/gkz/initialisation';
        this.jouerCoupMapping='/gkz/jouer-coup';
        this.destinationMapping='/topic/hi';
      } else if (this.modeJeu=="JcIA") {
        this.initialisationMapping='/gkz/init-IA_facile';
        this.jouerCoupMapping='';
        this.destinationMapping='/topic/JCIA-facile';
      } else if (this.modeJeu=="Probleme") {
        this.initialisationMapping='/gkz/init-probleme';
        this.jouerCoupMapping='/gkz/jc-pb';
        this.destinationMapping='/topic/probleme';
      }

    });

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
    // console.log(this.plateau!);
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
    console.log(this.caseArrivee.tagName);
    // console.log(this.caseArrivee.parentElement);
    //console.log(this.ER.nativeElement);
    let divoupas = this.caseArrivee.tagName;
    let caseArrId: any;
    if (divoupas == 'DIV') {
      caseArrId = this.caseArrivee.id as string;
    } else {
      caseArrId = this.caseArrivee.getAttribute('idDiv') as string;
    }

    if (this.coupsPossibles.find((pos) => pos === caseArrId)) {
      this.deplacement(this.coord as string, caseArrId as string);
      console.log(this.coord + ' ' + caseArrId);
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

      if (_this.modeJeu=="Probleme"){
        _this.stompClient.send(_this.initialisationMapping, {},
          JSON.stringify({ idProbleme: _this.problemeId }));
      } else {
        _this.stompClient.send(_this.initialisationMapping, {});
      }
      _this.stompClient.subscribe(_this.destinationMapping, function (hello: any) {

        console.log(hello.body);
        _this.plateau = JSON.parse(hello.body);
        _this.listePieces = JSON.parse(hello.body).pieces;
        document.querySelectorAll('img').forEach((el) => el.remove());

        _this.initImg();

        //pb
        // _this.stompClient.send('/gkz/init-probleme', {}, 1);
        // _this.stompClient.subscribe('/topic/probleme', function (hello: any) {
        //   console.log(hello.body);
        //   _this.plateau = JSON.parse(hello.body);
        //   _this.listePieces = JSON.parse(hello.body).pieces;
        //   document.querySelectorAll('img').forEach((el) => el.remove());

        //   _this.initImg();

        // console.log('ahah' + _this.coord);
      });
    });
  }

  initImg() {
    let cpt: number = 0;
    for (let piece of this.listePieces) {
      if (piece.coordonneeLettre != '') {
        let divImg = document.querySelector('#' + piece.coordonneeLettre);
        let img = document.createElement('img');
        img.setAttribute('style', 'max-width: 100%;');
        img.setAttribute('id', 'p' + cpt.toString());
        img.setAttribute('idDiv', piece.coordonneeLettre);
        img.setAttribute(
          'src',
          'assets/SpritePiecesPNG/' + piece.nom + piece.couleur + '.png'
        );
        img.setAttribute('draggable', 'true');
        divImg!.append(img);
      }
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
      this.jouerCoupMapping,
      {},
      JSON.stringify({ coupDepart: coordDep, coupArrivee: coordArr })
    );
  }

  addCoord(coordonnee: string) {
    this.coord = coordonnee;
  }
}
