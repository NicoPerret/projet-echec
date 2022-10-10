import { outputAst } from '@angular/compiler';
import { Component, HostListener, Input, OnInit } from '@angular/core';

@Component({
  selector: '[app-case]',
  templateUrl: './case.component.html',
  styleUrls: ['./case.component.css'],
})
export class CaseComponent implements OnInit {
  @Input()
  name!: string;

  private caseDepart: string = '';

  @HostListener('mousedown') onClick() {
    this.caseDepart = this.name;
    console.log(this.name);
  }

  constructor() {}

  ngOnInit(): void {}
}
