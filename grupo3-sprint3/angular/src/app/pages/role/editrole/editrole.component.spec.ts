import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditroleComponent } from './editrole.component';

describe('EditroleComponent', () => {
  let component: EditroleComponent;
  let fixture: ComponentFixture<EditroleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditroleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditroleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
