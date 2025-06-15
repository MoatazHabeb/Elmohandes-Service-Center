import { TestBed } from '@angular/core/testing';

import { CustomerRefreshService } from './customer-refresh.service';

describe('CustomerRefreshService', () => {
  let service: CustomerRefreshService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CustomerRefreshService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
