import { Component, OnInit } from '@angular/core';

@Component({
  selector: '[app-colonne-hist-chat]',
  templateUrl: './colonne-hist-chat.component.html',
  styleUrls: ['./colonne-hist-chat.component.css'],
  styles: [
    `
      :host {
        height: 100%;
      }
    `,
  ],
})
export class ColonneHistChatComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {}
}
