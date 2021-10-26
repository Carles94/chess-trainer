import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReplaceMoveConfirmationDialogComponent } from './replace-move-confirmation-dialog.component';

describe('ReplaceMoveConfirmationDialogComponent', () => {
  let component: ReplaceMoveConfirmationDialogComponent;
  let fixture: ComponentFixture<ReplaceMoveConfirmationDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReplaceMoveConfirmationDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReplaceMoveConfirmationDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
