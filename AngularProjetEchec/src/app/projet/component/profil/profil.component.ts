import { Utilisateur } from './../../model/utilisateur';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css'],
})
export class ProfilComponent implements OnInit {
  utilisateur!: Utilisateur;

  constructor() {}

  ngOnInit(): void {
    this.utilisateur = JSON.parse(sessionStorage.getItem('compte')!);
  }
modifier (){


}
  valider() {
    if (this.utilisateur.nom && this.utilisateur.prenom && this.utilisateur.pseudo && this.utilisateur.mdp && this.utilisateur.email){
      this.utilisateurPret.emit({
        nom: this.utilisateur.nom,
        prenom: this.utilisateur.prenom,
        pseudo: this.utilisateur.pseudo,
        mdp: this.utilisateur.mdp
        email:this.utilisateur.email
}
