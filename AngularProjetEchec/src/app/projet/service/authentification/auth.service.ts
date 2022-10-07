import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService implements CanActivate {
  constructor(private httpClient: HttpClient, private router: Router) {}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | boolean
    | UrlTree
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree> {
    if (sessionStorage.getItem('token')) {
      return true;
    }
    return this.router.navigateByUrl('/login');
  }

  public authentification(login: string, password: string): Observable<any> {
    let header = new HttpHeaders({
      Authorization: 'Basic ' + btoa(`${login}:${password}`),
    });
    return this.httpClient.get('http://localhost:8080/projet-echecs/api/auth', {
      headers: header,
    });
  }
}
