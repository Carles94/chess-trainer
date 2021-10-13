import { NO_ERRORS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatCardModule } from '@angular/material/card';
import { MatGridListModule } from '@angular/material/grid-list';
import { INITIAL_FEN } from '../common/model/constant/constant';
import { MoveEvent } from '../common/model/interface/move-event';
import { BoardStubComponent } from '../common/test/board-stub.component';

import { EditionBoardComponent } from './edition-board.component';

describe('EditionBoardComponent', () => {
  let component: EditionBoardComponent;
  let fixture: ComponentFixture<EditionBoardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      providers: [MatCardModule, MatGridListModule],
      declarations: [EditionBoardComponent, BoardStubComponent],
      schemas: [NO_ERRORS_SCHEMA],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditionBoardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should handle undo', () => {
    // Arrange
    component.board = new BoardStubComponent();
    let boardSpy = jest.spyOn(component.board, 'undo');
    // Act
    component.handleUndo();
    // Assert
    expect(boardSpy).toHaveBeenCalled();
  });

  it('should handle reset', () => {
    // Arrange
    component.board = new BoardStubComponent();
    let boardSpy = jest.spyOn(component.board, 'reset');
    // Act
    component.handleReset();
    // Assert
    expect(boardSpy).toHaveBeenCalled();
  });

  it('should handle reverse', () => {
    // Arrange
    component.board = new BoardStubComponent();
    let boardSpy = jest.spyOn(component.board, 'reverse');
    // Act
    component.handleReverse();
    // Assert
    expect(boardSpy).toHaveBeenCalled();
  });

  it('should handle piece moved', () => {
    // Arrange
    let moveEvent: MoveEvent = {
      capture: false,
      check: false,
      checkmate: false,
      color: 'white',
      fen: 'rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1',
      move: 'e2e4',
      piece: 'Pawn',
      stalemate: false,
    };
    // Act
    component.handlePieceMoved(moveEvent);
    // Assert
    expect(component['currentPosition'].currentPositionFEN).toBe(moveEvent.fen);
    expect(component['currentPosition'].moveList.length).toBeFalsy();

    expect(component['line'].positionList.length).toBe(2);
    expect(component['line'].positionList[0].currentPositionFEN).toBe(INITIAL_FEN);
    expect(component['line'].positionList[0].moveList[0].moveToSend).toBe(moveEvent.move);
    expect(component['line'].positionList[0].moveList[0].positionAfter).toBe(moveEvent.fen);
    expect(component['line'].positionList[1].currentPositionFEN).toBe(moveEvent.fen);
    expect(component['line'].positionList[1].moveList.length).toBeFalsy();
  });
});
