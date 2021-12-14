import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageOpeningComponent } from './manage-opening.component';

describe('ManageOpeningComponent', () => {
  let component: ManageOpeningComponent;
  let fixture: ComponentFixture<ManageOpeningComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageOpeningComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageOpeningComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
