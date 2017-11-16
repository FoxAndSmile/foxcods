import { TestBed, inject } from '@angular/core/testing';

import { ShareDataServiceService } from './share-data-service.service';

describe('ShareDataServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ShareDataServiceService]
    });
  });

  it('should be created', inject([ShareDataServiceService], (service: ShareDataServiceService) => {
    expect(service).toBeTruthy();
  }));
});
