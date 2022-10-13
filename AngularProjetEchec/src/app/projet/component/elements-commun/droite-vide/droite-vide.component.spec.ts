import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DroiteVideComponent } from './droite-vide.component';

describe('DroiteVideComponent', () => {
  let component: DroiteVideComponent;
  let fixture: ComponentFixture<DroiteVideComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DroiteVideComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DroiteVideComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
