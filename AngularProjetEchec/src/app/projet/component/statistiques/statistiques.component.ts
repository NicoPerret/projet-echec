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
  // activatedRoute!: ActivatedRoute;
  srvUtilisateur!: UtilisateurService;
  // srvStat!: StatistiquesService;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private srvStat: StatistiquesService
  ) {}

  ngOnInit(): void {
    this.utilisateur = JSON.parse(sessionStorage.getItem('compte')!);

    this.srvStat.getByUtilisateur(this.utilisateur).subscribe((data) => {
      this.statistiques = data;
    });
  }
  // this.statistiques = this.srvStat.getByUtilisateur(this.utilisateur);

  // this.activatedRoute.params.subscribe((params) => {
  //   if (params['id']) {
  //     this.srvStat
  //       .getByUtilisateur(params['utilisateur.id'])
  //       .subscribe((data) => {
  //         this.statistiques = data;
  //       });
  //   }
  // });

  retour() {
    this.router.navigateByUrl('/accueil');
  }
}
