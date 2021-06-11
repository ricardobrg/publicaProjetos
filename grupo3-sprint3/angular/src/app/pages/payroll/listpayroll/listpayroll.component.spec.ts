import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListpayrollComponent } from './listpayroll.component';

describe('ListpayrollComponent', () => {
  let component: ListpayrollComponent;
  let fixture: ComponentFixture<ListpayrollComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListpayrollComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListpayrollComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
