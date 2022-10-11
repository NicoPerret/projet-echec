import { HistoriquePartie } from './../../projet/model/historique-partie';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class HistoriqueService {
  private static URL = 'http://localhost:8080/projet-echecs/api/historique';

  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<HistoriquePartie[]> {
    //on traite les donnees avec une reference de type fournisseur
    return this.httpClient.get<HistoriquePartie[]>(HistoriqueService.URL);
  }

  public getById(id: number): Observable<HistoriquePartie> {
    return this.httpClient.get<HistoriquePartie>(
      HistoriqueService.URL + '/' + id
    );
  }

  public delete(id: number): Observable<void> {
    return this.httpClient.delete<void>(HistoriqueService.URL + '/' + id);
  }

  public create(
    historiquePartie: HistoriquePartie
  ): Observable<HistoriquePartie> {
    return this.httpClient.post<HistoriquePartie>(
      HistoriqueService.URL,
      this.historiquePartieToJson(historiquePartie)
    );
  }

  public historiquePartieToJson(historiquePartie: HistoriquePartie): any {
    let obj = {
      id: historiquePartie.id,
      j1: historiquePartie.j1,
      j2: historiquePartie.j2,
      messages: historiquePartie.messages,
      listeCoups: historiquePartie.listCoups,
      date: historiquePartie.date,
      vainqueur: historiquePartie.vainqueur,
    };
    return obj;
  }
}
