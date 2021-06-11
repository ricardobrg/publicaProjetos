import { TestBed } from '@angular/core/testing';

import { WorkEntryService } from './work-entry.service';

describe('WorkEntryService', () => {
  let service: WorkEntryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WorkEntryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
