import { HistoriquePartie } from './../../model/historique-partie';
import { Utilisateur } from './../../model/utilisateur';
import { HttpClient } from '@angular/common/http';
import { UtilisateurService } from '../../service/service/utilisateur.service';
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
  ) {}

  ngOnInit(): void {
    this.utilisateur = JSON.parse(sessionStorage.getItem('compte')!);
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
    if (this.parametresTypePartie == 'JcJ') {
      this.router.navigateByUrl(
        '/jeu/' + this.parametresTypePartie + '/1(droite:chat)'
      );
    } else if (this.parametresTypePartie == 'JcIA') {
      if (this.paramJcIADifficulte=='facile') {
        this.parametresTypePartie='JcIA-facile'
      } else {
        this.parametresTypePartie='JcIA-stockfish'
      }
      this.router.navigateByUrl('/jeu/' + this.parametresTypePartie + '/1');
    } else {
      this.router.navigateByUrl('/jeu/' + this.parametresTypePartie + '/1');
    }
  }

  listePb() {
    this.router.navigateByUrl('/probleme');
  }
}
