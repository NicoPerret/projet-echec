import { UtilisateurService } from 'src/app/projet/service/service/utilisateur.service';
import { Utilisateur } from './../../model/utilisateur';
import { Component, OnInit } from '@angular/core';
import { FormGroup, NgForm } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css'],
})
export class ProfilComponent implements OnInit {
  utilisateur!: Utilisateur;
  id!: number;
  constructor(
    private router: Router,
    private srvUtilisateur: UtilisateurService
  ) {}

  ngOnInit(): void {
    // this.srvUtilisateur.getById(this.utilisateur.id).subscribe((data) => {
    //   this.utilisateur = data;
    // });
    this.utilisateur = JSON.parse(sessionStorage.getItem('compte')!);
  }
  modifier() {
    this.router.navigateByUrl('/edit-profil');
  }
}
