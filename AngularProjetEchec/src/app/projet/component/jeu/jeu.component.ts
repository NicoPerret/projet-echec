import { Component, OnInit } from '@angular/core';
import { Utilisateur } from '../../model/utilisateur';

@Component({
  selector: 'app-jeu',
  templateUrl: './jeu.component.html',
  styleUrls: ['./jeu.component.css'],
})
export class JeuComponent implements OnInit {
  public couleurJoueur = 'blanc'; //a definir a la création de la game

  utilisateur!: Utilisateur;

  constructor() {}

  ngOnInit(): void {
    this.utilisateur = JSON.parse(sessionStorage.getItem('compte')!);
  }
}
