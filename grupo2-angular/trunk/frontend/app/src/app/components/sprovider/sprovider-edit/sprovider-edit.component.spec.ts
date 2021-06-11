import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SproviderEditComponent } from './sprovider-edit.component';

describe('SproviderEditComponent', () => {
  let component: SproviderEditComponent;
  let fixture: ComponentFixture<SproviderEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SproviderEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SproviderEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
