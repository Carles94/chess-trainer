import { Move } from './move';
import { MoveEvent } from './move-event';
import { Position } from './position';

export interface DeleteMoveBody {
  move: Move;
  currentPosition: Position;
  lineUuid: string;
}
