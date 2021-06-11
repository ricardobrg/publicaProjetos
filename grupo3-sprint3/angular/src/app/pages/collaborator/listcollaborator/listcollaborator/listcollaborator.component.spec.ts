import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListcollaboratorComponent } from './listcollaborator.component';

describe('ListcollaboratorComponent', () => {
  let component: ListcollaboratorComponent;
  let fixture: ComponentFixture<ListcollaboratorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListcollaboratorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListcollaboratorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
