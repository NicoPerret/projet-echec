import { Statistiques } from '../../model/statistiques';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Utilisateur } from '../../model/utilisateur';

@Injectable({
  providedIn: 'root',
})
export class StatistiquesService {
  private static URL = 'http://localhost:8080/projet-echecs/api/statistiques';

  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<Statistiques[]> {
    //on traite les donnees avec une reference de type fournisseur
    return this.httpClient.get<Statistiques[]>(StatistiquesService.URL);
  }

  public getById(id: number): Observable<Statistiques> {
    return this.httpClient.get<Statistiques>(
      StatistiquesService.URL + '/' + id
    );
  }

  public delete(id: number): Observable<void> {
    return this.httpClient.delete<void>(StatistiquesService.URL + '/' + id);
  }

  public create(statistiques: Statistiques): Observable<Statistiques> {
    return this.httpClient.post<Statistiques>(
      StatistiquesService.URL,
      this.statistiquesToJson(statistiques)
    );
  }

  public statistiquesToJson(statistiques: Statistiques): any {
    let obj = {
      id: statistiques.id,
      utilisateur: statistiques.utilisateur,
      tauxVictoire: statistiques.tauxVictoire,
      partieJouees: statistiques.partieJouees,
    };
    return obj;
  }

  public getByUtilisateur(utilisateur: Utilisateur): Observable<Statistiques> {
    return this.httpClient.get<Statistiques>(
      `${StatistiquesService.URL}/utilisateur/${utilisateur.id}`
    );
  }
}
