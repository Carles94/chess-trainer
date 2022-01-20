import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PracticeOpeningComponent } from './practice-opening.component';

describe('PracticeOpeningComponent', () => {
  let component: PracticeOpeningComponent;
  let fixture: ComponentFixture<PracticeOpeningComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PracticeOpeningComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PracticeOpeningComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
