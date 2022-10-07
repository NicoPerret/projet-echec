import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ColonneBoutonsComponent } from './colonne-boutons.component';

describe('ColonneBoutonsComponent', () => {
  let component: ColonneBoutonsComponent;
  let fixture: ComponentFixture<ColonneBoutonsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ColonneBoutonsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ColonneBoutonsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
