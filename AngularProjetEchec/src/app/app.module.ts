import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppComponent } from './app.component';
import { routes } from './routes';
import { AuthenticationInterceptor } from './projet/interceptor/authentication.interceptor';
import { LoginComponent } from './projet/component/login/login.component';
import { DeconnexionComponent } from './projet/component/deconnexion/deconnexion.component';
import { AcceuilComponent } from './projet/component/acceuil/acceuil.component';
import { MenuComponent } from './projet/component/elements-commun/menu/menu.component';
import { HeaderComponent } from './projet/component/elements-commun/header/header.component';
import { FooterComponent } from './projet/component/elements-commun/footer/footer.component';
import { HistoriqueComponent } from './projet/component/historique/historique.component';
import { JeuComponent } from './projet/component/jeu/jeu.component';
import { InscriptionComponent } from './projet/component/inscription/inscription.component';
import { ProblemeComponent } from './projet/component/probleme/probleme.component';
import { ProfilComponent } from './projet/component/profil/profil.component';
import { StatistiquesComponent } from './projet/component/statistiques/statistiques.component';
import { Tableau1a8Component } from './projet/component/jeu/plateau/tableau1a8/tableau1a8.component';
import { TableauAaHComponent } from './projet/component/jeu/plateau/tableau-aa-h/tableau-aa-h.component';
import { Plateau64casesComponent } from './projet/component/jeu/plateau/plateau64cases/plateau64cases.component';
import { PlateauComponent } from './projet/component/jeu/plateau/plateau.component';
import { ColonneBoutonsComponent } from './projet/component/jeu/colonne-boutons/colonne-boutons.component';
import { RxStompService } from '@stomp/ng2-stompjs';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,

    DeconnexionComponent,
    AcceuilComponent,
    MenuComponent,
    HeaderComponent,
    FooterComponent,
    HistoriqueComponent,
    JeuComponent,
    InscriptionComponent,
    ProblemeComponent,
    ProfilComponent,
    StatistiquesComponent,
    Tableau1a8Component,
    TableauAaHComponent,
    Plateau64casesComponent,
    PlateauComponent,
    ColonneBoutonsComponent,
  ],
  imports: [
    ReactiveFormsModule,
    BrowserModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
    FormsModule,
  ],
  providers: [
    {
      provide: [HTTP_INTERCEPTORS, RxStompService],
      useClass: AuthenticationInterceptor,

      multi: true,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
