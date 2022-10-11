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
  utilisateur!: Utilisateur;
  private utilisateurHistorique: HistoriquePartie[] | undefined;

  parametresTypePartie = '';

  paramJcJPseudoAdversaire = 'aleatoire';
  paramJcJModeJeu!: String;
  paramJcJHandicap!: String;

  paramJcIADifficulte!: String;

  paramProblemeDifficulte!: String;

  constructor(
    private http: HttpClient,
    private router: Router,
    private utilisateurService: UtilisateurService
  ) {
    this.utilisateur = JSON.parse(sessionStorage.getItem('compte')!);
    this.utilisateurService.getById(this.utilisateur.id!).subscribe((data) => {
      this.utilisateur = data;
    });
    this.utilisateurHistorique = this.utilisateur.historiqueparties;
  }

  ngOnInit(): void {}

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
