import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CollabAddComponent } from './collab-add.component';

describe('CollabAddComponent', () => {
  let component: CollabAddComponent;
  let fixture: ComponentFixture<CollabAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CollabAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CollabAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
