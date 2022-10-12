import { WebSocketChat } from '../../component/jeu/colonne-hist-chat/chat/chat.component.model';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class WebSocketService {
  websocket!: WebSocket;
  websocketMessage: WebSocketChat[] = [];
  constructor() {}

  openWebSocketConnection() {
    this.websocket = new WebSocket('ws://localhost:4201/websocket');

    this.websocket.onopen = (e) => {
      console.log(e);
    };

    this.websocket.onmessage = (e) => {
      console.log(e);
    };

    this.websocket.onclose = (e) => {
      console.log(e);
    };
  }
  sendWebSocketMessage(chatMsg: WebSocketChat) {
    this.websocket.send(JSON.stringify(chatMsg));
  }

  closeWebSocketConnection() {
    this.websocket.close();
  }
}
