import { BLACK, WHITE } from '../model/constant/constant';
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
      color: WHITE,
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
      color: BLACK,
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
      color: WHITE,
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
      color: BLACK,
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
      color: WHITE,
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
      color: BLACK,
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
      color: WHITE,
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
      color: WHITE,
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

  it('should transform a short castle white move', () => {
    // Arrange
    let moveEvent: MoveEvent = {
      capture: false,
      check: false,
      checkmate: false,
      color: WHITE,
      fen: 'r1bqkb1r/pppp1ppp/2n2n2/1B2p3/4P3/5N2/PPPP1PPP/RNBQ1RK1 b kq - 0 4',
      move: 'e1g1',
      piece: 'King',
      stalemate: false,
    };
    // Act
    const result: string = pipe.transform(moveEvent);
    // Assert
    expect(result).toBe('4.O-O');
  });

  it('should transform a long castle black move', () => {
    // Arrange
    let moveEvent: MoveEvent = {
      capture: false,
      check: false,
      checkmate: false,
      color: BLACK,
      fen: '2kr1bnr/ppp1pppp/2n5/3q4/3P2b1/5N2/PPP1BPPP/RNBQK2R w KQ - 0 6',
      move: 'e8c8',
      piece: 'King',
      stalemate: false,
    };
    // Act
    const result: string = pipe.transform(moveEvent);
    // Assert
    expect(result).toBe('5...O-O-O');
  });

  it('should transform a pawn capture move', () => {
    // Arrange
    let moveEvent: MoveEvent = {
      capture: true,
      check: false,
      checkmate: false,
      color: WHITE,
      fen: 'rnbqkbnr/ppp1pppp/8/3P4/8/8/PPPP1PPP/RNBQKBNR b KQkq - 0 2',
      move: 'e4d5',
      piece: 'Pawn',
      stalemate: false,
    };
    // Act
    const result: string = pipe.transform(moveEvent);
    // Assert
    expect(result).toBe('2.exd5');
  });
  it('should transform a king move', () => {
    // Arrange
    let moveEvent: MoveEvent = {
      capture: false,
      check: false,
      checkmate: false,
      color: WHITE,
      fen: 'rnbqkbnr/pppp1ppp/8/4p3/4P3/8/PPPPKPPP/RNBQ1BNR b kq - 0 2',
      move: 'e1e2',
      piece: 'King',
      stalemate: false,
    };
    // Act
    const result: string = pipe.transform(moveEvent);
    // Assert
    expect(result).toBe('2.Ke2');
  });

  it('should transform a rook move', () => {
    // Arrange
    let moveEvent: MoveEvent = {
      capture: false,
      check: false,
      checkmate: false,
      color: WHITE,
      fen: 'rnbqkbnr/ppppp1pp/8/5p2/8/5N2/PPPPPPPP/RNBQKBR1 b Qkq - 0 2',
      move: 'h1g1',
      piece: 'Rook',
      stalemate: false,
    };
    // Act
    const result: string = pipe.transform(moveEvent);
    // Assert
    expect(result).toBe('2.Rg1');
  });
});
