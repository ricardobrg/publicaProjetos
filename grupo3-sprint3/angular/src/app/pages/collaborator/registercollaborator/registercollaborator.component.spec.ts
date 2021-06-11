import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistercollaboratorComponent } from './registercollaborator.component';

describe('RegistercollaboratorComponent', () => {
  let component: RegistercollaboratorComponent;
  let fixture: ComponentFixture<RegistercollaboratorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistercollaboratorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistercollaboratorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
