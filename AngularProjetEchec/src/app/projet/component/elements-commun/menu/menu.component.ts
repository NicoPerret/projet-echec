import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],
})
export class MenuComponent implements OnInit {
  connecte: boolean = false;
  constructor() {}

  ngOnInit(): void {
    if (sessionStorage.getItem('token')) {
      this.connecte = true;
    }
  }
}
