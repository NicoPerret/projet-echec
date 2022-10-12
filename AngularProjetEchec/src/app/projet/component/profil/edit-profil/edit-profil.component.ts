import {
  AbstractControl,
  AsyncValidatorFn,
  FormControl,
  FormGroup,
  NgForm,
  ValidationErrors,
  Validators,
} from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Utilisateur } from 'src/app/projet/model/utilisateur';
import { Router } from '@angular/router';
import { UtilisateurService } from 'src/app/projet/service/service/utilisateur.service';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';

@Component({
  selector: 'app-edit-profil',
  templateUrl: './edit-profil.component.html',
  styleUrls: ['./edit-profil.component.css'],
})
export class EditProfilComponent implements OnInit {
  utilisateur!: Utilisateur;
  form!: FormGroup;

  constructor(
    private srvUtilisateur: UtilisateurService,
    private router: Router,
    private httpClient: HttpClient
  ) {}

  ngOnInit(): void {
    this.utilisateur = JSON.parse(sessionStorage.getItem('compte')!);

    this.form = new FormGroup({
      loginGroup: new FormGroup(
        {
          pseudo: new FormControl('', [
            Validators.required,
            Validators.minLength(2),
          ]),
          prenom: new FormControl('', [
            Validators.required,
            Validators.minLength(2),
          ]),
          nom: new FormControl('', [
            Validators.required,
            Validators.minLength(2),
          ]),
        },
        this.pseudoDiffNom
      ),

      emailCtrl: new FormControl('', [
        Validators.required,
        Validators.minLength(2),
      ]),

      mdpGroup: new FormGroup(
        {
          mdp: new FormControl('', [
            Validators.required,
            Validators.minLength(2),
            Validators.pattern('[a-zA-Z]*[0-9]'),
          ]),
          confirmmdp: new FormControl('', [Validators.required]),
        },
        this.mdpIdentique
      ),
    });
  }

  mdpIdentique(control: AbstractControl): ValidationErrors | null {
    let group = control as FormGroup;
    let mdp = group.get('mdp');
    let confirmmdp = group.get('confirmmdp');
    if (mdp?.invalid) {
      return null;
    }
    return mdp?.value != confirmmdp?.value ? { prix: true } : null;
  }
  pseudoDiffNom(control: AbstractControl): ValidationErrors | null {
    let group = control as FormGroup;
    let pseudo = group.get('pseudo');
    let nom = group.get('nom');
    let prenom = group.get('prenom');
    if (pseudo?.invalid) {
      return null;
    }
    if (pseudo?.value === nom?.value || pseudo?.value === prenom?.value) {
      return { prix: true };
    } else {
      return null;
    }
  }
  pseudoExist(): AsyncValidatorFn {
    return (control: AbstractControl): Observable<ValidationErrors | null> => {
      return this.httpClient
        .get('http://localhost:8080/echecs/api/inscription' + control.value)
        .pipe(
          map((boolResultatDuGet: any) => {
            return boolResultatDuGet ? { pseudoExist: true } : null;
          })
        );
    };
  }
  retour() {
    this.router.navigateByUrl('/connexion');
  }

  modifier() {}
}
