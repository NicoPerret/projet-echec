import { Statistiques } from './../../model/statistiques';
import { StatistiquesService } from './../../service/service/statistiques.service';
import { Component, OnInit } from '@angular/core';
import { Utilisateur } from '../../model/utilisateur';
import { Router, ActivatedRoute } from '@angular/router';
import { UtilisateurService } from '../../service/service/utilisateur.service';

@Component({
  selector: 'app-statistiques',
  templateUrl: './statistiques.component.html',
  styleUrls: ['./statistiques.component.css'],
})
export class StatistiquesComponent implements OnInit {
  statistiques!: Statistiques;
  utilisateur: Utilisateur = new Utilisateur();
  activatedRoute!: ActivatedRoute;
  srvUtilisateur!: UtilisateurService;

  constructor(
    private srvStat: StatistiquesService,
    private router: Router // activatedRoute: ActivatedRoute, // srvUtilisateur: UtilisateurService
  ) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      if (params['id']) {
        this.srvUtilisateur.getById(params['id']).subscribe((data) => {
          this.utilisateur = data;
        });
      }
    });
  }

  retour() {
    this.router.navigateByUrl('/accueil');
  }
}
