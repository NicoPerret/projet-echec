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
import { Router, ActivatedRoute } from '@angular/router';
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
    private httpClient: HttpClient,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.utilisateur = JSON.parse(sessionStorage.getItem('compte')!);

    this.form = new FormGroup({
      loginGroup: new FormGroup(
        {
          pseudo: new FormControl(this.utilisateur.pseudo, [
            Validators.required,
            Validators.minLength(2),
          ]),
          prenom: new FormControl(this.utilisateur.prenom, [
            Validators.required,
            Validators.minLength(2),
          ]),
          nom: new FormControl(this.utilisateur.nom, [
            Validators.required,
            Validators.minLength(2),
          ]),
        },
        this.pseudoDiffNom
      ),

      emailCtrl: new FormControl(this.utilisateur.email, [
        Validators.required,
        Validators.minLength(2),
      ]),
    });

    // this.activatedRoute.params.subscribe((params) => {
    //   if (params['id']) {
    //     this.srvUtilisateur.getById(params['id']).subscribe((data) => {
    //       this.form.get('id')?.setValue(data.id);
    //       this.form.get('loginGroup.nom')?.setValue(data.nom);
    //       this.form.get('loginGroup.prenom')?.setValue(data.prenom);
    //       this.form.get('emailCtrl')?.setValue(data.email);
    //       this.form.get('loginGroup.pseudo')?.setValue(data.pseudo);
    //     });
    //   }
    // });
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
        .get('http://localhost:8080/echecs/api/edit-profil' + control.value)
        .pipe(
          map((boolResultatDuGet: any) => {
            return boolResultatDuGet ? { pseudoExist: true } : null;
          })
        );
    };
  }

  retour() {
    this.router.navigateByUrl('/profil');
  }

  modifier() {
    this.utilisateur.nom = this.form.get('loginGroup.nom')?.value;
    this.utilisateur.prenom = this.form.get('loginGroup.prenom')?.value;
    this.utilisateur.email = this.form.get('emailCtrl')?.value;
    this.utilisateur.pseudo = this.form.get('loginGroup.pseudo')?.value;
    this.srvUtilisateur.update(this.utilisateur).subscribe((data) => {
      this.router.navigateByUrl('/profil');
    });
  }
}
