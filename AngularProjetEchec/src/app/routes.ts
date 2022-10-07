import { LoginComponent } from './projet/component/login/login.component';
import { InscriptionComponent } from './projet/component/inscription/inscription.component';
import { HistoriqueComponent } from './projet/component/historique/historique.component';
import { DeconnexionComponent } from './projet/component/deconnexion/deconnexion.component';
import { AcceuilComponent } from './projet/component/acceuil/acceuil.component';
import { Routes } from '@angular/router';
import { AnonymousGuardService } from './projet/service/guard/anymous-guard.service';
import { UserGuardService } from './projet/service/guard/user-guard.service';
import { AdminGuardService } from './projet/service/guard/admin-guard.service';
import { CoachGuardService } from './projet/service/guard/coach-guard.service';
import { JeuComponent } from './projet/component/jeu/jeu.component';
import { ProblemeComponent } from './projet/component/probleme/probleme.component';
import { ProfilComponent } from './projet/component/profil/profil.component';
import { StatistiquesComponent } from './projet/component/statistiques/statistiques.component';

export const routes: Routes = [
  {
    path: 'accueil',
    component: AcceuilComponent,
    canActivate: [UserGuardService, CoachGuardService, AdminGuardService],
  },
  {
    path: 'deconnexion',
    component: DeconnexionComponent,
    canActivate: [UserGuardService, CoachGuardService, AdminGuardService],
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
    canActivate: [UserGuardService, CoachGuardService, AdminGuardService],
  },
  {
    path: 'jeu',
    component: JeuComponent,
    canActivate: [UserGuardService, CoachGuardService, AdminGuardService],
  },
  {
    path: 'probleme',
    component: ProblemeComponent,
    canActivate: [UserGuardService, CoachGuardService, AdminGuardService],
  },
  {
    path: 'profil',
    component: ProfilComponent,
    canActivate: [UserGuardService, CoachGuardService, AdminGuardService],
  },
  {
    path: 'statistiques',
    component: StatistiquesComponent,
    canActivate: [UserGuardService, CoachGuardService, AdminGuardService],
  },
];
