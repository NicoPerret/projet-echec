import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Utilisateur } from 'src/app/projet/model/utilisateur';

@Injectable({
  providedIn: 'root',
})
export class UtilisateurService {
  private static URL = 'http://localhost:8080/projet-echecs/api/utilisateur';
  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<Utilisateur[]> {
    return this.httpClient.get<Utilisateur[]>(UtilisateurService.URL);
  }

  public getById(id: number): Observable<Utilisateur> {
    return this.httpClient.get<Utilisateur>(UtilisateurService.URL + '/' + id);
  }

  public delete(id: number): Observable<void> {
    return this.httpClient.delete<void>(UtilisateurService.URL + '/' + id);
  }

  public create(utilisateur: Utilisateur): Observable<Utilisateur> {
    return this.httpClient.post<Utilisateur>(
      UtilisateurService.URL,
      this.utilisateurToJson(utilisateur)
    );
  }

  public update(utilisateur: Utilisateur): Observable<Utilisateur> {
    return this.httpClient.put<Utilisateur>(
      `${UtilisateurService.URL}/${utilisateur.id}`,
      this.utilisateurToJson(utilisateur)
    );
  }

  public utilisateurToJson(utilisateur: Utilisateur): any {
    let obj = {
      id: utilisateur.id,
      pseudo: utilisateur.pseudo,
      nom: utilisateur.nom,
      prenom: utilisateur.prenom,
      mdp: utilisateur.mdp,
      email: utilisateur.email,
    };
    return obj;
  }

  public getByIdWithHistorique(id: number): Observable<Utilisateur> {
    return this.httpClient.get<Utilisateur>(
      `${UtilisateurService.URL}/${id}/historique`
    );
  }
}
