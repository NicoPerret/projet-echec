import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-tableau1a8',
  templateUrl: './tableau1a8.component.html',
  styleUrls: ['./tableau1a8.component.css'],
})
export class Tableau1a8Component implements OnInit {
  @Input()
  couleur!: string;

  constructor() {}

  ngOnInit(): void {}
}
