import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-jeu',
  templateUrl: './jeu.component.html',
  styleUrls: ['./jeu.component.css'],
})
export class JeuComponent implements OnInit {
  public couleurJoueur = 'blanc'; //a definir a la création de la game

  // private audio = new Audio('assets/372.mp3');

  // private couleurJoueur: string = 'blanc';
  // private typePartie: string = '1v1';

  constructor() {}

  ngOnInit(): void {}
}
