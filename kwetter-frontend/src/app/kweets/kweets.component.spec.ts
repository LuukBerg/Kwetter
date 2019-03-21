import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KweetsComponent } from './kweets.component';

describe('KweetsComponent', () => {
  let component: KweetsComponent;
  let fixture: ComponentFixture<KweetsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KweetsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KweetsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
