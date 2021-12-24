import { Component, EventEmitter, OnInit, Output, QueryList, ViewChildren } from '@angular/core';
import { BLACK, WHITE } from '../../model/constant/constant';
import { Line } from '../../model/interface/line';
import { Opening } from '../../model/interface/opening';
import { ShowOpeningsComponent } from './show-openings/show-openings.component';

@Component({
  selector: 'app-select-opening',
  templateUrl: './select-opening.component.html',
  styleUrls: ['./select-opening.component.scss'],
})
export class SelectOpeningComponent implements OnInit {
  @ViewChildren('showWhiteOpenings')
  public showWhiteOpeningsQueryList!: QueryList<ShowOpeningsComponent>;
  @ViewChildren('showBlackOpenings')
  public showBlackOpeningsQueryList!: QueryList<ShowOpeningsComponent>;
  @Output('selectLine')
  public selectLineEmitter: EventEmitter<Line> = new EventEmitter();
  public WHITE = WHITE;
  public BLACK = BLACK;
  public openingList: Opening[] = [];
  public whiteOpeningList: Opening[] = [];
  public blackOpeningList: Opening[] = [];
  public selectedColor = '';
  private showWhiteOpenings!: ShowOpeningsComponent;
  private showBlackOpenings!: ShowOpeningsComponent;

  constructor() {
    this.openingList.push(
      {
        uuid: 'uuidWhite',
        name: 'opening',
        lineList: [
          {
            uuid: 'uuidWhite',
            name: 'line',
            positionList: [],
            color: WHITE,
          },
        ],
        color: WHITE,
      },
      {
        uuid: 'uuidBlack',
        name: 'openingBlack',
        lineList: [
          {
            uuid: 'uuidBlack',
            name: 'lineBlack',
            positionList: [],
            color: BLACK,
          },
        ],
        color: BLACK,
      }
    );
  }

  ngOnInit(): void {
    this.whiteOpeningList = this.openingList.filter((opening) => opening.color === WHITE);
    this.blackOpeningList = this.openingList.filter((opening) => opening.color === BLACK);
  }

  public ngAfterViewInit(): void {
    this.showWhiteOpeningsQueryList.changes.subscribe((comps: QueryList<ShowOpeningsComponent>) => {
      this.showWhiteOpenings = comps.first;
    });
    this.showBlackOpeningsQueryList.changes.subscribe((comps: QueryList<ShowOpeningsComponent>) => {
      this.showBlackOpenings = comps.first;
    });
  }

  handleSelectColor(event: any): void {
    this.selectedColor = event.option.value;
    if (this.showBlackOpenings) {
      this.showBlackOpenings.resetSelection();
    }
    if (this.showWhiteOpenings) {
      this.showWhiteOpenings.resetSelection();
    }
  }

  handleSelectLine(line: Line): void {
    this.selectLineEmitter.emit(line);
  }
}
