import { BLACK, INITIAL_FEN, WHITE } from '../constant/constant';
import { Position } from '../interface/position';
import { Line } from './line';

describe('Line', () => {
  let line: Line;

  beforeEach(() => {
    line = new Line('name', WHITE, []);
  });

  it('should detect initial position by FEN', () => {
    // Arrange
    line.positionList.push(
      {
        FENPosition: 'rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1',
        previousFENPosition: '',
        moveList: [],
      },
      {
        FENPosition: INITIAL_FEN,
        previousFENPosition: '',
        moveList: [],
      }
    );
    // Act
    const result: boolean = line.existsPosition(INITIAL_FEN);
    // Assert
    expect(result).toBe(true);
  });

  it('should detect other position by FEN', () => {
    // Arrange
    line.positionList.push(
      {
        FENPosition: 'rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1',
        previousFENPosition: '',
        moveList: [],
      },
      {
        FENPosition: INITIAL_FEN,
        previousFENPosition: '',
        moveList: [],
      }
    );
    // Act
    const result: boolean = line.existsPosition('rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1');
    // Assert
    expect(result).toBe(true);
  });

  it('should detect transposition in position by FEN', () => {
    // Arrange
    line.positionList.push(
      {
        FENPosition: 'rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1',
        previousFENPosition: '',
        moveList: [],
      },
      {
        FENPosition: INITIAL_FEN,
        previousFENPosition: '',
        moveList: [],
      }
    );
    // Act
    const result: boolean = line.existsPosition('rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 2 3');
    // Assert
    expect(result).toBe(true);
  });
  it('should not detect other position by FEN', () => {
    // Arrange
    line.positionList.push(
      {
        FENPosition: 'rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1',
        previousFENPosition: '',
        moveList: [],
      },
      {
        FENPosition: INITIAL_FEN,
        previousFENPosition: '',
        moveList: [],
      }
    );
    // Act
    const result: boolean = line.existsPosition('rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR n KQkq e3 0 1');
    // Assert
    expect(result).toBe(false);
  });

  it('should not detect other position by FEN 2', () => {
    // Arrange
    line.positionList.push(
      {
        FENPosition: 'rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1',
        previousFENPosition: '',
        moveList: [],
      },
      {
        FENPosition: INITIAL_FEN,
        previousFENPosition: '',
        moveList: [],
      }
    );
    // Act
    const result: boolean = line.existsPosition('rnbqkbnr/pppppppp/8/8/3P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1');
    // Assert
    expect(result).toBe(false);
  });

  it('should delete position by FEN', () => {
    // Arrange
    line.positionList.push(
      {
        FENPosition: 'rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1',
        previousFENPosition: '',
        moveList: [],
      },
      {
        FENPosition: INITIAL_FEN,
        previousFENPosition: '',
        moveList: [],
      }
    );
    // Act
    line.deletePosition('rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1');
    // Assert
    expect(line.positionList.length).toBe(1);
    expect(line.positionList[0].FENPosition).toBe(INITIAL_FEN);
  });

  it('can add move to a line  when the moveList is empty', () => {
    // Arrange
    line.positionList.push({
      FENPosition: INITIAL_FEN,
      previousFENPosition: '',
      moveList: [],
    });
    // Act
    const result: boolean = line.canAddMove(WHITE, INITIAL_FEN);
    // Assert
    expect(result).toBe(true);
  });

  it('should not can add move to a line when the move list is not empty', () => {
    // Arrange
    line.positionList.push(
      {
        FENPosition: 'rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1',
        previousFENPosition: '',
        moveList: [],
      },
      {
        FENPosition: INITIAL_FEN,
        previousFENPosition: '',
        moveList: [
          {
            moveToSend: 'e2e4',
            moveToShow: '',
            positionFENAfter: '',
          },
        ],
      }
    );
    // Act
    const result: boolean = line.canAddMove(WHITE, INITIAL_FEN);
    // Assert
    expect(result).toBe(false);
  });

  it('should can add move to a line when the move list is not empty but is not the color of the line', () => {
    // Arrange
    line.positionList.push(
      {
        FENPosition: 'rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1',
        previousFENPosition: '',
        moveList: [
          {
            moveToSend: 'e7e5',
            moveToShow: '',
            positionFENAfter: '',
          },
        ],
      },
      {
        FENPosition: INITIAL_FEN,
        previousFENPosition: '',
        moveList: [
          {
            moveToSend: 'e2e4',
            moveToShow: '',
            positionFENAfter: '',
          },
        ],
      }
    );
    // Act
    const result: boolean = line.canAddMove(BLACK, INITIAL_FEN);
    // Assert
    expect(result).toBe(true);
  });
});
