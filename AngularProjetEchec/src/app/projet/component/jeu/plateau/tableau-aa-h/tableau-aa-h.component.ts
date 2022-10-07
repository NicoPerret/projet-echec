import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-tableauAaH',
  templateUrl: './tableau-aa-h.component.html',
  styleUrls: ['./tableau-aa-h.component.css'],
})
export class TableauAaHComponent implements OnInit {
  @Input()
  couleur!: string;

  constructor() {}

  ngOnInit(): void {}
}
