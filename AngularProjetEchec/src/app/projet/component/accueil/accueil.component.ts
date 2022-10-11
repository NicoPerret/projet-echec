import { HistoriquePartie } from './../../model/historique-partie';
import { Utilisateur } from './../../model/utilisateur';
import { HttpClient } from '@angular/common/http';
import { UtilisateurService } from './../../../service/service/utilisateur.service';
import { Router } from '@angular/router';
import { Component, Injectable, OnInit } from '@angular/core';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.css'],
})
export class AccueilComponent implements OnInit {
  utilisateurService!: UtilisateurService;

  utilisateur!: Utilisateur;
  utilisateur2!: Observable<Utilisateur>;
  utilisateurHistorique!: HistoriquePartie[] | undefined;

  parametresTypePartie = '';

  paramJcJPseudoAdversaire = 'aleatoire';
  paramJcJModeJeu!: String;
  paramJcJHandicap!: String;

  paramJcIADifficulte!: String;

  paramProblemeDifficulte!: String;

  constructor(private http: HttpClient, private router: Router) {
    this.utilisateur = JSON.parse(sessionStorage.getItem('compte')!);
  }

  ngOnInit(): void {
    this.utilisateurService.getById(this.utilisateur.id!).subscribe((data) => {
      this.utilisateur = data;
    });
    this.utilisateurHistorique = this.utilisateur.historiqueparties;
  }

  openJcJ() {
    this.parametresTypePartie = 'JcJ';
  }

  openJcIA() {
    this.parametresTypePartie = 'JcIA';
  }

  openProbleme() {
    this.parametresTypePartie = 'Probleme';
  }

  annuler() {
    this.parametresTypePartie = '';
  }

  jouer() {
    this.router.navigateByUrl('/jeu');
  }

  listePb() {
    this.router.navigateByUrl('/probleme');
  }
}
