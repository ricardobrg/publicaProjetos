import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterpayrollComponent } from './registerpayroll.component';

describe('RegisterpayrollComponent', () => {
  let component: RegisterpayrollComponent;
  let fixture: ComponentFixture<RegisterpayrollComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterpayrollComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterpayrollComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
