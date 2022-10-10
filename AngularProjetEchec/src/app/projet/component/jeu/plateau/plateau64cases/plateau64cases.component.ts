import { verifyHostBindings } from '@angular/compiler';
import {
  AfterViewInit,
  Component,
  HostListener,
  Input,
  OnInit,
} from '@angular/core';
declare function dragDrop(): any;
@Component({
  selector: 'app-plateau64cases',
  templateUrl: './plateau64cases.component.html',
  styleUrls: ['./plateau64cases.component.css'],
})
export class Plateau64casesComponent implements OnInit, AfterViewInit {
  @Input()
  couleur!: string;

  public lettres = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'];
  public nombres = [8, 7, 6, 5, 4, 3, 2, 1];

  constructor() {}
  ngAfterViewInit(): void {
    dragDrop();
  }

  ngOnInit(): void {}

  @HostListener('mouseDown') onClick() {}
}
