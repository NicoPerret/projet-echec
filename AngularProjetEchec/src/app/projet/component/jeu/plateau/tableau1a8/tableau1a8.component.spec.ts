import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Tableau1a8Component } from './tableau1a8.component';

describe('Tableau1a8Component', () => {
  let component: Tableau1a8Component;
  let fixture: ComponentFixture<Tableau1a8Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Tableau1a8Component ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Tableau1a8Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
