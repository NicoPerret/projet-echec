import { Observable } from 'rxjs';
import { Utilisateur } from './../../model/utilisateur';
import { ResultatProblemeService } from './../../service/service/resultat-probleme.service';
import { Probleme } from './../../model/probleme';
import { ProblemeService } from '../../service/service/probleme.service';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { EmptyExpr } from '@angular/compiler';
import { ResultatProbleme } from '../../model/resultat-probleme';

@Component({
  selector: 'app-probleme',
  templateUrl: './probleme.component.html',
  styleUrls: ['./probleme.component.css'],
})
export class ProblemeComponent implements OnInit {
  listeProblemes!: Probleme[];
  utilisateur!: Utilisateur;
  imageToShow: any;
  listeImages!: any[];
  resultatProbleme: ResultatProbleme | undefined;
  listeProblemesResolus!: number[];

  constructor(
    private http: HttpClient,
    private router: Router,
    public problemeService: ProblemeService,
    public resultatProblemeService: ResultatProblemeService
  ) {}

  ngOnInit(): void {
    this.utilisateur = JSON.parse(sessionStorage.getItem('compte')!);

    this.listeProblemesResolus = [];
    this.problemeService.getAll().subscribe((data) => {
      this.listeProblemes = data;
      this.listeProblemes.forEach( (probleme) => {
          this.problemeService.trouveDifficulteCategorie(probleme);
          this.resultatProblemeService.getByIdAndUser(probleme.id!, this.utilisateur.id!).subscribe((data) => {
            if (data == true) {
              this.listeProblemesResolus.push(probleme.id!);
            }
        });
      });
    });

    console.log("Liste des problemes resolus : " + this.listeProblemesResolus);
  }

  initListeImages() {
    for (let probleme of this.listeProblemes) {
      let probImg = document.createElement('img');
      probImg.setAttribute('id', '' + probleme.id);
      probImg.setAttribute('src',"http://www.fen-to-image.com/image/" + probleme.fenDepart);
      this.listeImages[probleme.id!] = probImg;
    }

    console.log(this.listeProblemes);
  }

  renvoiProbleme(id: Number) {
    this.router.navigateByUrl('/jeu');
  }

}
