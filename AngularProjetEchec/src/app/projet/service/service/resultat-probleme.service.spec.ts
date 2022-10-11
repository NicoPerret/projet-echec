import { TestBed } from '@angular/core/testing';

import { ResultatProblemeService } from './resultat-probleme.service';

describe('ResultatProblemeService', () => {
  let service: ResultatProblemeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ResultatProblemeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
