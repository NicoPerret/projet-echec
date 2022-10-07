import { Component, OnInit } from '@angular/core';
import { Utilisateur } from '../../model/utilisateur';

@Component({
  selector: 'app-statistiques',
  templateUrl: './statistiques.component.html',
  styleUrls: ['./statistiques.component.css'],
})
export class StatistiquesComponent implements OnInit {
  utilisateur!: Utilisateur;
  constructor() {}

  ngOnInit(): void {}
}
