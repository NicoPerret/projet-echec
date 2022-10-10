import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DemoWebsocketComponent } from './demo-websocket.component';

describe('DemoWebsocketComponent', () => {
  let component: DemoWebsocketComponent;
  let fixture: ComponentFixture<DemoWebsocketComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DemoWebsocketComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DemoWebsocketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
