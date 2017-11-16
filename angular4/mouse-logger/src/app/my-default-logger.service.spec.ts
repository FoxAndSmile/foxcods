import { TestBed, inject } from '@angular/core/testing';

import { MyDefaultLoggerService } from './my-default-logger.service';

describe('MyDefaultLoggerService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MyDefaultLoggerService]
    });
  });

  it('should be created', inject([MyDefaultLoggerService], (service: MyDefaultLoggerService) => {
    expect(service).toBeTruthy();
  }));
});
