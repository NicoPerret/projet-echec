import { Component, OnInit } from '@angular/core';
import { Utilisateur } from '../../model/utilisateur';

@Component({
  selector: 'app-acceuil',
  templateUrl: './acceuil.component.html',
  styleUrls: ['./acceuil.component.css'],
})
export class AcceuilComponent implements OnInit {
  utilisateur!: Utilisateur;
  constructor() {}

  ngOnInit(): void {}
}
