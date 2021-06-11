import { ComponentFixture, TestBed } from '@angular/core/testing';

import { vacationComponent } from './vacation.component';

describe('vacationComponent', () => {
  let component: vacationComponent;
  let fixture: ComponentFixture<vacationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ vacationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(vacationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
