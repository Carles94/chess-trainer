import { MoveEvent } from '../model/interface/move-event';
import { MovePipe } from './move.pipe';

describe('MovePipe', () => {
  let pipe: MovePipe;
  beforeEach(() => {
    pipe = new MovePipe();
  });
  it('create an instance', () => {
    expect(pipe).toBeTruthy();
  });

  it('should transform a white pawn move', () => {
    // Arrange
    let moveEvent: MoveEvent = {
      capture: false,
      check: false,
      checkmate: false,
      color: 'white',
      fen: 'rnbqkbnr/pppppppp/8/8/3P4/8/PPP1PPPP/RNBQKBNR b KQkq d3 0 1',
      move: 'd2d4',
      piece: 'Pawn',
      stalemate: false,
    };
    // Act
    const result: string = pipe.transform(moveEvent);
    // Assert
    expect(result).toBe('1.d4');
  });

  it('should transform a black pawn move', () => {
    // Arrange
    let moveEvent: MoveEvent = {
      capture: false,
      check: false,
      checkmate: false,
      color: 'black',
      fen: 'rnbqkbnr/ppp1pppp/8/3p4/3P4/8/PPP1PPPP/RNBQKBNR w KQkq d6 0 2',
      move: 'd7d5',
      piece: 'Pawn',
      stalemate: false,
    };
    // Act
    const result: string = pipe.transform(moveEvent);
    // Assert
    expect(result).toBe('1...d5');
  });

  it('should transform a white knight move', () => {
    // Arrange
    let moveEvent: MoveEvent = {
      capture: false,
      check: false,
      checkmate: false,
      color: 'white',
      fen: 'rnbqkbnr/ppp1pppp/8/3p4/3P4/2N5/PPP1PPPP/R1BQKBNR b KQkq - 0 2',
      move: 'b1c3',
      piece: 'Knight',
      stalemate: false,
    };
    // Act
    const result: string = pipe.transform(moveEvent);
    // Assert
    expect(result).toBe('2.Nc3');
  });

  it('should transform a black bishop move', () => {
    // Arrange
    let moveEvent: MoveEvent = {
      capture: false,
      check: false,
      checkmate: false,
      color: 'black',
      fen: 'rn1qkbnr/ppp1pppp/8/3p1b2/3P4/2N5/PPP1PPPP/R1BQKBNR w KQkq - 0 3',
      move: 'c8f5',
      piece: 'Bishop',
      stalemate: false,
    };
    // Act
    const result: string = pipe.transform(moveEvent);
    // Assert
    expect(result).toBe('2...Bf5');
  });
  it('should transform a white knight capture move', () => {
    // Arrange
    let moveEvent: MoveEvent = {
      capture: true,
      check: false,
      checkmate: false,
      color: 'white',
      fen: 'rn1qkbnr/ppp1pppp/8/3N1b2/3P4/8/PPP1PPPP/R1BQKBNR b KQkq - 0 3',
      move: 'c3d5',
      piece: 'Knight',
      stalemate: false,
    };
    // Act
    const result: string = pipe.transform(moveEvent);
    // Assert
    expect(result).toBe('3.Nxd5');
  });

  it('should transform a black queen capture move', () => {
    // Arrange
    let moveEvent: MoveEvent = {
      capture: true,
      check: false,
      checkmate: false,
      color: 'black',
      fen: 'rn2kbnr/ppp1pppp/8/3q1b2/3P4/8/PPP1PPPP/R1BQKBNR w KQkq - 0 4',
      move: 'd8d5',
      piece: 'Queen',
      stalemate: false,
    };
    // Act
    const result: string = pipe.transform(moveEvent);
    // Assert
    expect(result).toBe('3...Qxd5');
  });

  it('should transform a bishop check move', () => {
    // Arrange
    let moveEvent: MoveEvent = {
      capture: false,
      check: true,
      checkmate: false,
      color: 'white',
      fen: 'rnbqk1nr/pppp1Bpp/5p2/2b1p3/4P3/5Q2/PPPP1PPP/RNB1K1NR b KQkq - 0 4',
      move: 'c4f7',
      piece: 'Bishop',
      stalemate: false,
    };
    // Act
    const result: string = pipe.transform(moveEvent);
    // Assert
    expect(result).toBe('4.Bf7+');
  });
  it('should transform a queen capture check mate move', () => {
    // Arrange
    let moveEvent: MoveEvent = {
      capture: true,
      check: true,
      checkmate: true,
      color: 'white',
      fen: 'r1bqk1nr/pppp1Qpp/2n5/2b1p3/2B1P3/8/PPPP1PPP/RNB1K1NR b KQkq - 0 4',
      move: 'f3f7',
      piece: 'Queen',
      stalemate: false,
    };
    // Act
    const result: string = pipe.transform(moveEvent);
    // Assert
    expect(result).toBe('4.Qxf7#');
  });
});
