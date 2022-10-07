import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../service/authentification/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  login: string = '';
  password: string = '';
  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {}
  send() {
    this.authService.authentification(this.login, this.password).subscribe({
      next: (data) => {
        JSON.stringify(data);
        sessionStorage.setItem('compte', JSON.stringify(data));
        sessionStorage.setItem(
          'token',
          'Basic ' + btoa(this.login + ':' + this.password)
        );
        this.router.navigateByUrl('/accueil');
      },
      error: (err) => {
        console.log(err);
        this.router.navigateByUrl('/connexion?connect=false');
      },
    });
  }
}
