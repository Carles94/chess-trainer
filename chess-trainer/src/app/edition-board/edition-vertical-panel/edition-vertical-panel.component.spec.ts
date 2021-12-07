import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatListModule, MatSelectionList, MatSelectionListChange } from '@angular/material/list';
import { Move } from 'src/app/common/model/interface/move';

import { EditionVerticalPanelComponent } from './edition-vertical-panel.component';

describe('EditionVerticalPanelComponent', () => {
  let component: EditionVerticalPanelComponent;
  let fixture: ComponentFixture<EditionVerticalPanelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EditionVerticalPanelComponent],
      imports: [MatCardModule, MatDividerModule, MatListModule],
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
    // Arrange
    const move: Move = {
      moveToSend: 'd2d4',
      moveToShow: '',
      positionFenAfter: 'rnbqkbnr/pppppppp/8/8/3P4/8/PPP1PPPP/RNBQKBNR b KQkq d3 0 1',
    };
    component['selectedMove'] = move;
    // Act
    component.handleDeleteMove();
    expect(component.deleteMoveEmitter.emit).toHaveBeenCalledWith(move);
    expect(component['selectedMove']).toBe(undefined);
  });

  it('should not emit delete move when move is undefined', () => {
    // Arrange
    component['selectedMove'] = undefined;
    // Act
    component.handleDeleteMove();
    // Assert
    expect(component.deleteMoveEmitter.emit).not.toHaveBeenCalled();
    expect(component['selectedMove']).toBe(undefined);
  });

  it('should emit next move', () => {
    // Arrange
    const move: Move = {
      moveToSend: 'd2d4',
      moveToShow: '',
      positionFenAfter: 'rnbqkbnr/pppppppp/8/8/3P4/8/PPP1PPPP/RNBQKBNR b KQkq d3 0 1',
    };
    component['selectedMove'] = move;
    // Act
    component.handleNextMove();
    // Assert
    expect(component.nextMoveEmitter.emit).toHaveBeenCalledWith(move);
    expect(component['selectedMove']).toBe(undefined);
  });

  it('should not emit next move when move is undefined', () => {
    // Arrange
    component['selectedMove'] = undefined;
    // Act
    component.handleNextMove();
    // Assert
    expect(component.nextMoveEmitter.emit).not.toHaveBeenCalled();
    expect(component['selectedMove']).toBe(undefined);
  });

  it('should select the move', () => {
    //Arrange
    const move: Move = {
      moveToSend: 'd2d4',
      moveToShow: '',
      positionFenAfter: 'rnbqkbnr/pppppppp/8/8/3P4/8/PPP1PPPP/RNBQKBNR b KQkq d3 0 1',
    };
    const event = {
      options: [{ value: move }],
    };
    // Act
    component.handleSelectMove(event);
    // Assert
    expect(component['selectedMove']).toBe(move);
  });
});
