import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PracticeShowAnswerDialogComponent } from './practice-show-answer-dialog.component';

describe('PracticeShowAnswerDialogComponent', () => {
  let component: PracticeShowAnswerDialogComponent;
  let fixture: ComponentFixture<PracticeShowAnswerDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PracticeShowAnswerDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PracticeShowAnswerDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
