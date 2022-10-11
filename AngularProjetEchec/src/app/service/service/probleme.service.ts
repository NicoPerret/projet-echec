import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Probleme } from 'src/app/projet/model/probleme';

@Injectable({
  providedIn: 'root',
})
export class ProblemeService {
  private static URL = 'http://localhost:8080/projet-echecs/api/probleme';
  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<Probleme[]> {
    return this.httpClient.get<Probleme[]>(ProblemeService.URL);
  }

  public getById(id: number): Observable<Probleme> {
    return this.httpClient.get<Probleme>(ProblemeService.URL + '/' + id);
  }

  public delete(id: number): Observable<void> {
    return this.httpClient.delete<void>(ProblemeService.URL + '/' + id);
  }

  public create(probleme: Probleme): Observable<Probleme> {
    return this.httpClient.post<Probleme>(
      ProblemeService.URL,
      this.problemeToJson(probleme)
    );
  }

  public problemeToJson(probleme: Probleme): any {
    let obj = {
      id: probleme.id,
      fenDepart: probleme.fenDepart,
      listeDeplacements: probleme.listeDeplacements,
      traitAuBlanc: probleme.traitAuBlanc,
      difficulte: probleme.difficulte,
    };
    return obj;
  }
}
