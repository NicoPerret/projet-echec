import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Utilisateur } from 'src/app/projet/model/utilisateur';

@Component({
  selector: 'app-div-droite-accueil',
  templateUrl: './div-droite-accueil.component.html',
  styleUrls: ['./div-droite-accueil.component.css'],
})
export class DivDroiteAccueilComponent implements OnInit {
  utilisateur!: Utilisateur;

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.utilisateur = JSON.parse(sessionStorage.getItem('compte')!);
  }

  renvoiHistorique() {
    this.router.navigateByUrl('/historique');
  }
}
