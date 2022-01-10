import { HttpClient } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output, QueryList, ViewChildren } from '@angular/core';
import { BLACK, WHITE } from '../../model/constant/constant';
import { Line } from '../../model/interface/line';
import { Opening } from '../../model/interface/opening';
import { HttpUtils } from '../../utils/http-utils';
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
  @Input('openings')
  public openingList: Opening[] = [];
  public WHITE = WHITE;
  public BLACK = BLACK;
  public whiteOpeningList: Opening[] = [];
  public blackOpeningList: Opening[] = [];
  public selectedColor = '';
  private showWhiteOpenings!: ShowOpeningsComponent;
  private showBlackOpenings!: ShowOpeningsComponent;

  constructor() {}

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
