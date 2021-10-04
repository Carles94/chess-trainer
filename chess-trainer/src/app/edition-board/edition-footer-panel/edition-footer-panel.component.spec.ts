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
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should emit undo', () => {
    component.handleUndo();
    expect(component.undoEmitter.emit).toHaveBeenCalled();
  });
});
