import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterroleComponent } from './registerrole.component';

describe('RegisterroleComponent', () => {
  let component: RegisterroleComponent;
  let fixture: ComponentFixture<RegisterroleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterroleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterroleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
