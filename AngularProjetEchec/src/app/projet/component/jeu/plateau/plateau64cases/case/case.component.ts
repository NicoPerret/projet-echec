import { DragDrop } from '@angular/cdk/drag-drop';
import { outputAst } from '@angular/compiler';
import {
  Component,
  EventEmitter,
  HostListener,
  Input,
  OnInit,
  Output,
} from '@angular/core';
import { Stomp } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
declare function dragDrop(coupspossibles: string[]): any;
@Component({
  selector: '[app-case]',
  templateUrl: './case.component.html',
  styleUrls: ['./case.component.css'],
})
export class CaseComponent implements OnInit {
  private stompClient: any;
  @Input()
  name!: string;

  @Output()
  coordonneesDepart = new EventEmitter<string>();

  coupsPossibles!: string[];

  private caseDepart: string = '';

  @HostListener('mousedown') onClick() {
    this.coordonneesDepart.emit(this.name);
  }

  constructor() {}

  ngOnInit(): void {}

  coupPossible(coord: string) {
    this.stompClient.send(
      '/gkz/coup-possible',
      {},
      JSON.stringify({ coup: coord })
    );
  }
}
