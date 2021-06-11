import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditcollaboratorComponent } from './editcollaborator.component';

describe('EditcollaboratorComponent', () => {
  let component: EditcollaboratorComponent;
  let fixture: ComponentFixture<EditcollaboratorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditcollaboratorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditcollaboratorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
