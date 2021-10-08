import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatCardModule } from '@angular/material/card';

import { EditionVerticalPanelComponent } from './edition-vertical-panel.component';

describe('EditionVerticalPanelComponent', () => {
  let component: EditionVerticalPanelComponent;
  let fixture: ComponentFixture<EditionVerticalPanelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EditionVerticalPanelComponent],
      imports: [MatCardModule],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditionVerticalPanelComponent);
    component = fixture.componentInstance;
    jest.spyOn(component.deleteMoveEmitter, 'emit');
    jest.spyOn(component.nextMoveEmitter, 'emit');

    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should emit delete move', () => {
    component.handleDeleteMove();
    expect(component.deleteMoveEmitter.emit).toHaveBeenCalled();
  });

  it('should emit next move', () => {
    component.handleNextMove();
    expect(component.nextMoveEmitter.emit).toHaveBeenCalled();
  });
});
