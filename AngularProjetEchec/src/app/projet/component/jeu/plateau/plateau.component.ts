import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-plateau',
  templateUrl: './plateau.component.html',
  styleUrls: ['./plateau.component.css'],
})
export class PlateauComponent implements OnInit {
  @Input()
  couleurJoueur!: string;

  private coord!: string;

  constructor() {}

  ngOnInit(): void {}
}
