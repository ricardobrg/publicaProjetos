import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PointAddComponent } from './point-add.component';

describe('PointAddComponent', () => {
  let component: PointAddComponent;
  let fixture: ComponentFixture<PointAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PointAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PointAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
