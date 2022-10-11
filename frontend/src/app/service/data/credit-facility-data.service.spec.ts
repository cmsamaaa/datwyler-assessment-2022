import { TestBed } from '@angular/core/testing';

import { CreditFacilityDataService } from './credit-facility-data.service';

describe('CreditFacilityDataService', () => {
  let service: CreditFacilityDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreditFacilityDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
