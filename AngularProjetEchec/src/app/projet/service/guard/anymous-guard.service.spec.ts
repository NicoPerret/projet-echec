import { TestBed } from '@angular/core/testing';

import { AnymousGuardService } from './anymous-guard.service';

describe('AnymousGuardService', () => {
  let service: AnymousGuardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AnymousGuardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
