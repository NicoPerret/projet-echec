import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Plateau64casesComponent } from './plateau64cases.component';

describe('Plateau64casesComponent', () => {
  let component: Plateau64casesComponent;
  let fixture: ComponentFixture<Plateau64casesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Plateau64casesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Plateau64casesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
