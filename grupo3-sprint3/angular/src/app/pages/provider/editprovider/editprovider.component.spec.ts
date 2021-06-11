import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditproviderComponent } from './editprovider.component';

describe('EditproviderComponent', () => {
  let component: EditproviderComponent;
  let fixture: ComponentFixture<EditproviderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditproviderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditproviderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
