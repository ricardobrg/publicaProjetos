import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListroleComponent } from './listrole.component';

describe('ListroleComponent', () => {
  let component: ListroleComponent;
  let fixture: ComponentFixture<ListroleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListroleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListroleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
