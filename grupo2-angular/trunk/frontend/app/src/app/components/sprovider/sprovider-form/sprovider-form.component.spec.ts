import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SproviderFormComponent } from './sprovider-form.component';

describe('SproviderFormComponent', () => {
  let component: SproviderFormComponent;
  let fixture: ComponentFixture<SproviderFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SproviderFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SproviderFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
