import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SproviderListComponent } from './sprovider-list.component';

describe('SproviderListComponent', () => {
  let component: SproviderListComponent;
  let fixture: ComponentFixture<SproviderListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SproviderListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SproviderListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
