import { TestBed } from '@angular/core/testing';

import { HttpWrapService } from './http-wrap.service';

describe('HttpWrapService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: HttpWrapService = TestBed.get(HttpWrapService);
    expect(service).toBeTruthy();
  });
});
