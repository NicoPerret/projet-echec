import { DragDrop } from '@angular/cdk/drag-drop';
import { outputAst } from '@angular/compiler';
import { Component, HostListener, Input, OnInit } from '@angular/core';
declare function dragDrop(): any;
@Component({
  selector: '[app-case]',
  templateUrl: './case.component.html',
  styleUrls: ['./case.component.css'],
})
export class CaseComponent implements OnInit {
  @Input()
  name!: string;

  @Input()
  plateau!: any;

  private caseDepart: string = '';

  @HostListener('dragstart') onClick() {
    dragDrop();
    console.log(this.name);
  }

  constructor() {}

  ngOnInit(): void {}
}
