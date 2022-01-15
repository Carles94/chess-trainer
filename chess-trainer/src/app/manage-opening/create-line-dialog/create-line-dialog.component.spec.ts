import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateLineDialogComponent } from './create-line-dialog.component';

describe('CreateLineDialogComponent', () => {
  let component: CreateLineDialogComponent;
  let fixture: ComponentFixture<CreateLineDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateLineDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateLineDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
