import { TestBed } from '@angular/core/testing';

import { CoachGuardService } from './coach-guard.service';

describe('CoachGuardService', () => {
  let service: CoachGuardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CoachGuardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
