import { INITIAL_FEN } from '../constant/constant';
import { Position } from '../interface/position';
import { Line } from './line';

describe('Line', () => {
  let line: Line;

  beforeEach(() => {
    line = new Line('name', []);
  });

  it('should get initial position by FEN', () => {
    // Arrange
    line.positionList.push(
      {
        positionFEN: 'rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1',
        previousFENPosition: '',
        moveList: [],
      },
      {
        positionFEN: INITIAL_FEN,
        previousFENPosition: '',
        moveList: [],
      }
    );
    // Act
    const result: Position = line.getPositionByFEN(INITIAL_FEN);
    // Assert
    expect(result).toBe(line.positionList[1]);
  });

  it('should get other position by FEN', () => {
    // Arrange
    line.positionList.push(
      {
        positionFEN: 'rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1',
        previousFENPosition: '',
        moveList: [],
      },
      {
        positionFEN: INITIAL_FEN,
        previousFENPosition: '',
        moveList: [],
      }
    );
    // Act
    const result: Position = line.getPositionByFEN('rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1');
    // Assert
    expect(result).toBe(line.positionList[0]);
  });

  it("should throw exception when position don't exists by FEN", () => {
    // Arrange
    line.positionList.push(
      {
        positionFEN: 'rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1',
        previousFENPosition: '',
        moveList: [],
      },
      {
        positionFEN: INITIAL_FEN,
        previousFENPosition: '',
        moveList: [],
      }
    );
    // Act + Assert
    expect(() => line.getPositionByFEN('rnbqkbnr/pppppppp/8/8/3P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1')).toThrowError();
  });

  it('should get transposition in position by FEN', () => {
    // Arrange
    line.positionList.push(
      {
        positionFEN: 'rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1',
        previousFENPosition: '',
        moveList: [],
      },
      {
        positionFEN: INITIAL_FEN,
        previousFENPosition: '',
        moveList: [],
      }
    );
    // Act
    const result: Position = line.getPositionByFEN('rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 2 3');
    // Assert
    expect(result).toBe(line.positionList[0]);
  });

  it('should detect initial position by FEN', () => {
    // Arrange
    line.positionList.push(
      {
        positionFEN: 'rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1',
        previousFENPosition: '',
        moveList: [],
      },
      {
        positionFEN: INITIAL_FEN,
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
        positionFEN: 'rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1',
        previousFENPosition: '',
        moveList: [],
      },
      {
        positionFEN: INITIAL_FEN,
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
        positionFEN: 'rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1',
        previousFENPosition: '',
        moveList: [],
      },
      {
        positionFEN: INITIAL_FEN,
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
        positionFEN: 'rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1',
        previousFENPosition: '',
        moveList: [],
      },
      {
        positionFEN: INITIAL_FEN,
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
        positionFEN: 'rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1',
        previousFENPosition: '',
        moveList: [],
      },
      {
        positionFEN: INITIAL_FEN,
        previousFENPosition: '',
        moveList: [],
      }
    );
    // Act
    const result: boolean = line.existsPosition('rnbqkbnr/pppppppp/8/8/3P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1');
    // Assert
    expect(result).toBe(false);
  });
});
