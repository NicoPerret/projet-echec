import { Component, OnInit } from '@angular/core';
import { Stomp } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';

@Component({
  selector: 'app-demo-websocket',
  templateUrl: './demo-websocket.component.html',
  styleUrls: ['./demo-websocket.component.css'],
})
export class DemoWebsocketComponent implements OnInit {
  ngOnInit(): void {}
  title = 'grokonez';
  description = 'Angular-WebSocket Demo';

  greetings: string[] = [];
  disabled = true;
  name!: string;
  private stompClient: any;

  constructor() {}

  setConnected(connected: boolean) {
    this.disabled = !connected;

    if (connected) {
      this.greetings = [];
    }
  }

  connect() {
    const socket = new SockJS(
      'http://localhost:8080/projet-echecs/gkz-stomp-endpoint'
    );
    this.stompClient = Stomp.over(socket);

    const _this = this;
    this.stompClient.connect({}, function (frame: any) {
      _this.setConnected(true);
      console.log('Connected: ' + frame);

      _this.stompClient.subscribe('/topic/hi', function (hello: any) {
        console.log(hello);

        _this.showGreeting(JSON.parse(hello.body).greeting);
      });
    });
  }

  disconnect() {
    if (this.stompClient != null) {
      this.stompClient.disconnect();
    }

    this.setConnected(false);
    console.log('Disconnected!');
  }

  sendName() {
    this.stompClient.send(
      '/gkz/hello',
      {},
      JSON.stringify({ name: this.name })
    );
  }
  init() {
    this.stompClient.send('/gkz/initialisation', {});
  }

  deplacement() {
    this.stompClient.send('/gkz/jouer-coup', {}),
      JSON.stringify({ coup: 'E4' });
  }

  coupPossible() {
    this.stompClient.send(
      '/gkz/coup-possible',
      {},

      JSON.stringify({ coup: 'E2' })
    );
  }

  showGreeting(message: any) {
    this.greetings.push(message);
  }
}
