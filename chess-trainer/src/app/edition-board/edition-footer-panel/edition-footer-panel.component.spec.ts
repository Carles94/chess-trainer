import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatCardModule } from '@angular/material/card';

import { EditionFooterPanelComponent } from './edition-footer-panel.component';

describe('EditionFooterPanelComponent', () => {
  let component: EditionFooterPanelComponent;
  let fixture: ComponentFixture<EditionFooterPanelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EditionFooterPanelComponent],
      imports: [MatCardModule],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditionFooterPanelComponent);
    component = fixture.componentInstance;
    jest.spyOn(component.undoEmitter, 'emit');
    jest.spyOn(component.resetEmitter, 'emit');
    jest.spyOn(component.reverseEmitter, 'emit');
    jest.spyOn(component.undoUntilAlternativeEmitter, 'emit');
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should emit undo', () => {
    component.handleUndo();
    expect(component.undoEmitter.emit).toHaveBeenCalled();
  });

  it('should emit reset', () => {
    component.handleReset();
    expect(component.resetEmitter.emit).toHaveBeenCalled();
  });

  it('should emit reverse', () => {
    component.handleReverse();
    expect(component.reverseEmitter.emit).toHaveBeenCalled();
  });
  it('should undo until alternative reverse', () => {
    component.handleUndoUntilAlternative();
    expect(component.undoUntilAlternativeEmitter.emit).toHaveBeenCalled();
  });
});
