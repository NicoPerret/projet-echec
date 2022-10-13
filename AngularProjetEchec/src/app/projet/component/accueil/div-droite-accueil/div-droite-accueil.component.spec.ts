import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DivDroiteAccueilComponent } from './div-droite-accueil.component';

describe('DivDroiteAccueilComponent', () => {
  let component: DivDroiteAccueilComponent;
  let fixture: ComponentFixture<DivDroiteAccueilComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DivDroiteAccueilComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DivDroiteAccueilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
