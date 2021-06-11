import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfilePointComponent } from './profile-point.component';

describe('ProfilePointComponent', () => {
  let component: ProfilePointComponent;
  let fixture: ComponentFixture<ProfilePointComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfilePointComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfilePointComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
