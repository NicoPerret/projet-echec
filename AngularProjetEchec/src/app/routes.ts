import { LoginComponent } from './projet/component/login/login.component';
import { AnonymousGuardService } from './projet/service/anymous-guard.service';
import { InscriptionComponent } from './projet/component/inscription/inscription.component';
import { HistoriqueComponent } from './projet/component/historique/historique.component';
import { DeconnexionComponent } from './projet/component/deconnexion/deconnexion.component';
import { AcceuilComponent } from './projet/component/acceuil/acceuil.component';
import { Routes } from '@angular/router';
import { UserGuardService } from './projet/service/user-guard.service';
import { JeuComponent } from './projet/component/jeu/jeu.component';
import { ProblemeComponent } from './projet/component/probleme/probleme.component';
import { ProfilComponent } from './projet/component/profil/profil.component';
import { StatistiquesComponent } from './projet/component/statistiques/statistiques.component';

export const routes: Routes = [
  {
    path: 'acceuil',
    component: AcceuilComponent,
    canActivate: [UserGuardService],
  },
  {
    path: 'deconnexion',
    component: DeconnexionComponent,
    canActivate: [UserGuardService],
  },
  {
    path: 'connexion',
    component: LoginComponent,
    canActivate: [AnonymousGuardService],
  },
  {
    path: '',
    component: LoginComponent,
    canActivate: [AnonymousGuardService],
  },
  {
    path: 'inscription',
    component: InscriptionComponent,
    canActivate: [AnonymousGuardService],
  },
  {
    path: 'historique',
    component: HistoriqueComponent,
    canActivate: [UserGuardService],
  },
  {
    path: 'jeu',
    component: JeuComponent,
    canActivate: [UserGuardService],
  },
  {
    path: 'probleme',
    component: ProblemeComponent,
    canActivate: [UserGuardService],
  },
  {
    path: 'profil',
    component: ProfilComponent,
    canActivate: [UserGuardService],
  },
  {
    path: 'statistiques',
    component: StatistiquesComponent,
    canActivate: [UserGuardService],
  },
];
