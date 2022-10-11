import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResultatProbleme } from 'src/app/projet/model/resultat-probleme';

@Injectable({
  providedIn: 'root'
})
export class ResultatProblemeService {
  private static URL = 'http://localhost:8080/projet-echecs/api/resultat-probleme';
  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<ResultatProbleme[]> {
    return this.httpClient.get<ResultatProbleme[]>(ResultatProblemeService.URL);
  }

  public getById(id: number): Observable<ResultatProbleme> {
    return this.httpClient.get<ResultatProbleme>(ResultatProblemeService.URL + '/' + id);
  }

  public getByIdAndUser(idProbleme: number, idUtilisateur: number): Observable<Boolean> {
    return this.httpClient.get<Boolean>(ResultatProblemeService.URL + '/' + idProbleme + '&' + idUtilisateur);
  }

  public delete(id: number): Observable<void> {
    return this.httpClient.delete<void>(ResultatProblemeService.URL + '/' + id);
  }

  public create(resultatProbleme: ResultatProbleme): Observable<ResultatProbleme> {
    return this.httpClient.post<ResultatProbleme>(
      ResultatProblemeService.URL,
      this.resultatProblemeToJson(resultatProbleme)
    );
  }

  public resultatProblemeToJson(resultatProbleme: ResultatProbleme): any {
    let obj = {
      id: resultatProbleme.id,
      utilisateur: resultatProbleme.utilisateur,
      probleme: resultatProbleme.probleme,
      date: resultatProbleme.date,
    };
    return obj;
  }
}
