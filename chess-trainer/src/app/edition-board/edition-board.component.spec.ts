import { NO_ERRORS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatCardModule } from '@angular/material/card';
import { MatGridListModule } from '@angular/material/grid-list';
import { MoveChange } from 'ngx-chess-board';
import { INITIAL_FEN } from '../common/model/constant/constant';
import { Move } from '../common/model/interface/move';
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

  it('should handle play next move', () => {
    // Arrange
    component.board = new BoardStubComponent();
    const move: Move = {
      moveToSend: 'd2d4',
      moveToShow: '',
      positionFENAfter: 'rnbqkbnr/pppppppp/8/8/3P4/8/PPP1PPPP/RNBQKBNR b KQkq d3 0 1',
    };
    let boardSpy = jest.spyOn(component.board, 'move');
    // Act
    component.handleNextMove(move);
    // Assert
    expect(boardSpy).toHaveBeenCalledWith('d2d4');
  });

  it("should handle piece moved when the position and the move don't exists in line by storing the move and the position", () => {
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
    expect(component['currentPosition'].positionFEN).toBe(moveEvent.fen);
    expect(component['currentPosition'].moveList.length).toBeFalsy();
    expect(component['currentPosition'].previousFENPosition).toBe(INITIAL_FEN);

    expect(component['line'].positionList.length).toBe(2);
    expect(component['line'].positionList[0].positionFEN).toBe(INITIAL_FEN);
    expect(component['line'].positionList[0].moveList.length).toBe(1);
    expect(component['line'].positionList[0].moveList[0].moveToSend).toBe(moveEvent.move);
    expect(component['line'].positionList[0].moveList[0].positionFENAfter).toBe(moveEvent.fen);
    expect(component['line'].positionList[1].positionFEN).toBe(moveEvent.fen);
    expect(component['line'].positionList[1].moveList.length).toBeFalsy();
  });

  it('should handle piece moved after undo with different move', () => {
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
    let moveEvent2: MoveEvent = {
      capture: false,
      check: false,
      checkmate: false,
      color: 'white',
      fen: 'rnbqkbnr/pppppppp/8/8/8/4P3/PPPP1PPP/RNBQKBNR b KQkq - 0 1',
      move: 'e2e3',
      piece: 'Pawn',
      stalemate: false,
    };
    component.board = new BoardStubComponent();
    // Act
    component.handlePieceMoved(moveEvent);
    component.handleUndo();
    component.handlePieceMoved(moveEvent2);
    // Assert
    expect(component['currentPosition'].positionFEN).toBe(moveEvent2.fen);
    expect(component['currentPosition'].moveList.length).toBeFalsy();
    expect(component['currentPosition'].previousFENPosition).toBe(INITIAL_FEN);

    expect(component['line'].positionList.length).toBe(3);
    expect(component['line'].positionList[0].positionFEN).toBe(INITIAL_FEN);
    expect(component['line'].positionList[0].moveList.length).toBe(2);
    expect(component['line'].positionList[0].moveList[0].moveToSend).toBe(moveEvent.move);
    expect(component['line'].positionList[0].moveList[0].positionFENAfter).toBe(moveEvent.fen);
    expect(component['line'].positionList[0].moveList[1].moveToSend).toBe(moveEvent2.move);
    expect(component['line'].positionList[0].moveList[1].positionFENAfter).toBe(moveEvent2.fen);
    expect(component['line'].positionList[1].positionFEN).toBe(moveEvent.fen);
    expect(component['line'].positionList[1].moveList.length).toBeFalsy();
    expect(component['line'].positionList[2].positionFEN).toBe(moveEvent2.fen);
    expect(component['line'].positionList[2].moveList.length).toBeFalsy();
  });

  it('should handle piece moved after undo with same move', () => {
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
    component.board = new BoardStubComponent();
    // Act
    component.handlePieceMoved(moveEvent);
    component.handleUndo();
    component.handlePieceMoved(moveEvent);
    // Assert
    expect(component['currentPosition'].positionFEN).toBe(moveEvent.fen);
    expect(component['currentPosition'].moveList.length).toBeFalsy();
    expect(component['currentPosition'].previousFENPosition).toBe(INITIAL_FEN);

    expect(component['line'].positionList.length).toBe(2);
    expect(component['line'].positionList[0].positionFEN).toBe(INITIAL_FEN);
    expect(component['line'].positionList[0].moveList.length).toBe(1);
    expect(component['line'].positionList[0].moveList[0].moveToSend).toBe(moveEvent.move);
    expect(component['line'].positionList[0].moveList[0].positionFENAfter).toBe(moveEvent.fen);
    expect(component['line'].positionList[1].positionFEN).toBe(moveEvent.fen);
    expect(component['line'].positionList[1].moveList.length).toBeFalsy();
  });

  it('should handle undo after piece moved', () => {
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
    component.board = new BoardStubComponent();
    // Act
    component.handlePieceMoved(moveEvent);
    component.handleUndo();
    // Assert
    expect(component['currentPosition'].positionFEN).toBe(INITIAL_FEN);
    expect(component['currentPosition'].moveList.length).toBe(1);
    expect(component['currentPosition'].moveList[0].moveToSend).toBe(moveEvent.move);
    expect(component['currentPosition'].moveList[0].positionFENAfter).toBe(moveEvent.fen);

    expect(component['line'].positionList.length).toBe(2);
  });

  it('should handle two undos after two pieces moved', () => {
    // Arrange
    let moveEvent1: MoveEvent = {
      capture: false,
      check: false,
      checkmate: false,
      color: 'white',
      fen: 'rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1',
      move: 'e2e4',
      piece: 'Pawn',
      stalemate: false,
    };
    let moveEvent2: MoveEvent = {
      capture: false,
      check: false,
      checkmate: false,
      color: 'black',
      fen: 'rnbqkbnr/pppp1ppp/8/4p3/4P3/8/PPPP1PPP/RNBQKBNR w KQkq e6 0 2',
      move: 'e7e5',
      piece: 'Pawn',
      stalemate: false,
    };
    component.board = new BoardStubComponent();
    // Act
    component.handlePieceMoved(moveEvent1);
    component.handlePieceMoved(moveEvent2);
    component.handleUndo();
    component.handleUndo();
    // Assert
    expect(component['currentPosition'].positionFEN).toBe(INITIAL_FEN);
    expect(component['currentPosition'].moveList.length).toBe(1);
    expect(component['currentPosition'].moveList[0].moveToSend).toBe(moveEvent1.move);
    expect(component['currentPosition'].moveList[0].positionFENAfter).toBe(moveEvent1.fen);

    expect(component['line'].positionList.length).toBe(3);
  });

  it('should handle reset after two pieces moved', () => {
    // Arrange
    let moveEvent1: MoveEvent = {
      capture: false,
      check: false,
      checkmate: false,
      color: 'white',
      fen: 'rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1',
      move: 'e2e4',
      piece: 'Pawn',
      stalemate: false,
    };
    let moveEvent2: MoveEvent = {
      capture: false,
      check: false,
      checkmate: false,
      color: 'black',
      fen: 'rnbqkbnr/pppp1ppp/8/4p3/4P3/8/PPPP1PPP/RNBQKBNR w KQkq e6 0 2',
      move: 'e7e5',
      piece: 'Pawn',
      stalemate: false,
    };
    component.board = new BoardStubComponent();
    // Act
    component.handlePieceMoved(moveEvent1);
    component.handlePieceMoved(moveEvent2);
    component.handleReset();
    // Assert
    expect(component['currentPosition'].positionFEN).toBe(INITIAL_FEN);
    expect(component['currentPosition'].moveList.length).toBe(1);
    expect(component['currentPosition'].moveList[0].moveToSend).toBe(moveEvent1.move);
    expect(component['currentPosition'].moveList[0].positionFENAfter).toBe(moveEvent1.fen);

    expect(component['line'].positionList.length).toBe(3);
  });
});
