import { WebSocketService } from '../../../../service/service/chat.service';
import { Component, OnInit } from '@angular/core';
import { WebSocketChat } from './chat.component.model';
import { NgForm } from '@angular/forms';
import * as SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css'],
})
export class ChatComponent implements OnInit {
  srvWebSocket: WebSocketService = new WebSocketService();
  private stompClient: any;
  greetings!: string[];
  disabled = true;

  constructor() {}

  ngOnInit(): void {
    this.connect();
  }

  // ngOnDestroy() {
  //   this.srvWebSocket.closeWebSocketConnection();
  // }

  connect() {
    const socket = new SockJS(
      'http://localhost:8080/projet-echecs/gkz-stomp-endpoint'
    );
    this.stompClient = Stomp.over(socket);

    const _this = this;
    this.stompClient.connect({}, function (frame: any) {
      _this.setConnected(true);
      console.log('Connected: ' + frame);

      _this.stompClient.subscribe('/topic/chat', function (hello: any) {
        console.log(hello);

        _this.greetings.push(JSON.parse(hello.body).greeting);
      });
    });
  }

  setConnected(connected: boolean) {
    this.disabled = !connected;

    if (connected) {
      this.greetings = [];
    }
  }

  sendMessage(wsMessageForm: NgForm) {
    if (
      wsMessageForm.value.message != null &&
      wsMessageForm.value.message != ''
    ) {
      let compte = JSON.parse(sessionStorage.getItem('compte')!);
      let msg: string = compte.pseudo + ' : ' + wsMessageForm.value.message;
      this.stompClient.send('/gkz/hello', {}, JSON.stringify({ message: msg }));
      wsMessageForm.controls['message'].reset();
    }
  }
}
