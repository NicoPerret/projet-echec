import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ColonneHistChatComponent } from './colonne-hist-chat.component';

describe('ColonneHistChatComponent', () => {
  let component: ColonneHistChatComponent;
  let fixture: ComponentFixture<ColonneHistChatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ColonneHistChatComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ColonneHistChatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
