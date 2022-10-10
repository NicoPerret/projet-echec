import { verifyHostBindings } from '@angular/compiler';
import {
  AfterViewInit,
  Component,
  HostListener,
  Input,
  OnInit,
} from '@angular/core';
import * as SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import { Utilisateur } from 'src/app/projet/model/utilisateur';

declare function dragDrop(): any;
@Component({
  selector: 'app-plateau64cases',
  templateUrl: './plateau64cases.component.html',
  styleUrls: ['./plateau64cases.component.css'],
})
export class Plateau64casesComponent implements OnInit, AfterViewInit {
  @Input()
  couleur!: string;

  greetings: string[] = [];
  disabled = true;
  name!: string;
  private stompClient: any;
  private listePieces!: any[];

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
        console.log(hello);
        _this.listePieces = JSON.parse(hello.body);
        _this.showGreeting(JSON.parse(hello.body).greeting);
        _this.initImg();
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
    dragDrop();
  }

  setConnected(connected: boolean) {
    this.disabled = !connected;

    if (connected) {
      this.greetings = [];
    }
  }

  showGreeting(truc: any) {
    this.greetings.push(truc);
  }

  deplacement() {
    this.stompClient.send('/gkz//jouer-coup', {}),
      JSON.stringify({ coup: 'E4' });
  }
  coupPossible() {
    this.stompClient.send(
      '/gkz/coup-possible',
      {},

      JSON.stringify({ coup: 'E2' })
    );
  }
}
