import { UtilisateurService } from './../../../service/service/utilisateur.service';
import { Utilisateur } from './../../model/utilisateur';

import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  ValidationErrors,
  ValidatorFn,
  Validators,
} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css'],
})
export class InscriptionComponent implements OnInit {
  form!: FormGroup;
  utilisateur!: Utilisateur;

  constructor(
    private srvUtilisateur: UtilisateurService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
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

  chargeUtil(): Utilisateur {
    this.utilisateur.nom = this.form.get('nom')?.value;
    this.utilisateur.prenom = this.form.get('prenom')?.value;
    this.utilisateur.email = this.form.get('email')?.value;
    this.utilisateur.mdp = this.form.get('mdp')?.value;
    this.utilisateur.pseudo = this.form.get('pseudo')?.value;

    return this.utilisateur;
  }

  inscription() {
    this.srvUtilisateur.create(this.chargeUtil()).subscribe((data) => {
      this.router.navigateByUrl('/profil?action=create&id=' + data.id);
    });
  }
}
