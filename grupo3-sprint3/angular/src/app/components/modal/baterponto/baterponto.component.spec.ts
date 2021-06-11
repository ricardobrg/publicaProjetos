import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BaterpontoComponent } from './baterponto.component';

describe('BaterpontoComponent', () => {
  let component: BaterpontoComponent;
  let fixture: ComponentFixture<BaterpontoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BaterpontoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BaterpontoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
