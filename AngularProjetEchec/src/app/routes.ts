import { DroiteVideComponent } from './projet/component/elements-commun/droite-vide/droite-vide.component';
import { ColonneHistChatComponent } from './projet/component/jeu/colonne-hist-chat/colonne-hist-chat.component';
import { EditProfilComponent } from './projet/component/profil/edit-profil/edit-profil.component';
import { DemoWebsocketComponent } from './projet/component/demo-websocket/demo-websocket/demo-websocket.component';
import { LoginComponent } from './projet/component/login/login.component';
import { InscriptionComponent } from './projet/component/inscription/inscription.component';
import { HistoriqueComponent } from './projet/component/historique/historique.component';
import { DeconnexionComponent } from './projet/component/deconnexion/deconnexion.component';
import { AccueilComponent } from './projet/component/accueil/accueil.component';
import { Routes } from '@angular/router';
import { AnonymousGuardService } from './projet/service/guard/anymous-guard.service';
import { UserGuardService } from './projet/service/guard/user-guard.service';
import { AdminGuardService } from './projet/service/guard/admin-guard.service';
import { CoachGuardService } from './projet/service/guard/coach-guard.service';
import { JeuComponent } from './projet/component/jeu/jeu.component';
import { ProblemeComponent } from './projet/component/probleme/probleme.component';
import { ProfilComponent } from './projet/component/profil/profil.component';
import { StatistiquesComponent } from './projet/component/statistiques/statistiques.component';
import { DropListRef } from '@angular/cdk/drag-drop';

export const routes: Routes = [
  {
    path: 'accueil',
    component: AccueilComponent,
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
  { path: 'divDroite', component: DroiteVideComponent, outlet: 'droite' },
  {
    path: 'chat',
    component: ColonneHistChatComponent,
    outlet: 'droite',
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
    path: 'jeu/:modeJeu/:problemeId',
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
  {
    path: 'web',
    component: DemoWebsocketComponent,
    canActivate: [UserGuardService],
  },
  {
    path: 'edit-profil',
    component: EditProfilComponent,
    canActivate: [UserGuardService],
  },
];
