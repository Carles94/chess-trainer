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
    const result: Position = line.getPositionByFEN(INITIAL_FEN);
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
    const result: Position = line.getPositionByFEN('rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1');
    expect(result).toBe(line.positionList[0]);
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
    const result: Position = line.getPositionByFEN('rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 2 3');
    expect(result).toBe(line.positionList[0]);
  });
});
