import { Component, HostListener, Input, OnInit } from '@angular/core';

@Component({
  selector: '[app-case]',
  templateUrl: './case.component.html',
  styleUrls: ['./case.component.css'],
})
export class CaseComponent implements OnInit {
  @Input()
  name!: string;

  @HostListener('mousedown') onClick() {
    console.log(this.name);
  }

  constructor() {}

  ngOnInit(): void {}
}
