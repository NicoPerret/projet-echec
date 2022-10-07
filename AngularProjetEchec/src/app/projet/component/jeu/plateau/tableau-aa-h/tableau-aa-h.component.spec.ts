import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableauAaHComponent } from './tableau-aa-h.component';

describe('TableauAaHComponent', () => {
  let component: TableauAaHComponent;
  let fixture: ComponentFixture<TableauAaHComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TableauAaHComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TableauAaHComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
