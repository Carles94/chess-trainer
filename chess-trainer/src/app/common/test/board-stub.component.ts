import { Component } from '@angular/core';
import { IBoard } from '../model/interface/board';

@Component({
  selector: 'app-board',
  template: '',
})
export class BoardStubComponent implements IBoard {
  public pieceMoved(event: any): void {}
  public move(move: string): void {}
  public undo(): void {}
  public reverse(): void {}
  public reset(): void {}
}
