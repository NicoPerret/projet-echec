import { Probleme } from './../../model/probleme';
import { ProblemeService } from '../../service/service/probleme.service';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-probleme',
  templateUrl: './probleme.component.html',
  styleUrls: ['./probleme.component.css'],
})
export class ProblemeComponent implements OnInit {
  public listeProblemes!: Probleme[];
  imageToShow: any;
  listeImages!: any[];

  constructor(
    private http: HttpClient,
    private router: Router,
    public problemeService: ProblemeService
  ) {}

  ngOnInit(): void {
    this.problemeService.getAll().subscribe((data) => {
      this.listeProblemes = data;
    });
    console.log(this.listeProblemes);
  }

  initListeImages() {
    for (let probleme of this.listeProblemes) {
      let probImg = document.createElement('img');
      probImg.setAttribute('id', '' + probleme.id);
      probImg.setAttribute('src',"http://www.fen-to-image.com/image/" + probleme.fenDepart);
      this.listeImages[probleme.id!] = probImg;
    }
  }

}
