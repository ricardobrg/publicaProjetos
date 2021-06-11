import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CollabListComponent } from './collab-list.component';

describe('CollabListComponent', () => {
  let component: CollabListComponent;
  let fixture: ComponentFixture<CollabListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CollabListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CollabListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
