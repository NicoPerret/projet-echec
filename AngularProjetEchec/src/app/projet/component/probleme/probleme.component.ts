import { Probleme } from './../../model/probleme';
import { ProblemeService } from './../../../service/service/probleme.service';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-probleme',
  templateUrl: './probleme.component.html',
  styleUrls: ['./probleme.component.css']
})
export class ProblemeComponent implements OnInit {

  public listeProblemes!: Probleme[];

  constructor(
    private http: HttpClient,
    private router: Router,
    public problemeService: ProblemeService
  ) {

  }

  ngOnInit(): void {
    this.problemeService.getAll().subscribe((data) => {
      this.listeProblemes = data;
    });
    console.log(this.listeProblemes);
  }

}
