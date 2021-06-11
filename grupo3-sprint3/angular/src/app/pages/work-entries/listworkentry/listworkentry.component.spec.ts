import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListworkentryComponent } from './listworkentry.component';

describe('ListworkentryComponent', () => {
  let component: ListworkentryComponent;
  let fixture: ComponentFixture<ListworkentryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListworkentryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListworkentryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
