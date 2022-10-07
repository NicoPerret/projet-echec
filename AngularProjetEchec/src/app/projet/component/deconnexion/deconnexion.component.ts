import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-deconnexion',
  templateUrl: './deconnexion.component.html',
  styleUrls: ['./deconnexion.component.css'],
})
export class DeconnexionComponent implements OnInit {
  constructor(private router: Router) {}

  ngOnInit(): void {}
  disconnect() {
    sessionStorage.clear();
    this.router.navigateByUrl('/connexion');
  }
}
