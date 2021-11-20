import { MoveEvent } from './move-event';
import { Position } from './position';

export interface PostMoveBody {
  moveEvent: MoveEvent;
  currentPosition: Position;
  lineUuid: string;
}
