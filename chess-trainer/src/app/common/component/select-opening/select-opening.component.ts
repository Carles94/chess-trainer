import { Component, OnInit, ViewChild } from '@angular/core';
import { BLACK, WHITE } from '../../model/constant/constant';
import { Opening } from '../../model/interface/opening';
import { ShowOpeningsComponent } from './show-openings/show-openings.component';

@Component({
  selector: 'app-select-opening',
  templateUrl: './select-opening.component.html',
  styleUrls: ['./select-opening.component.scss'],
})
export class SelectOpeningComponent implements OnInit {
  @ViewChild('showWhiteOpenings', { static: false })
  public showWhiteOpenings!: ShowOpeningsComponent;
  @ViewChild('showWhiteOpenings', { static: false })
  public showBlackOpenings!: ShowOpeningsComponent;
  public WHITE = WHITE;
  public BLACK = BLACK;
  public openingList: Opening[] = [];
  public whiteOpeningList: Opening[] = [];
  public blackOpeningList: Opening[] = [];
  public selectedColor = '';

  constructor() {
    this.openingList.push(
      {
        uuid: '',
        name: 'opening',
        lineList: [
          {
            name: 'line',
            positionList: [],
            color: WHITE,
          },
        ],
        color: WHITE,
      },
      {
        uuid: '',
        name: 'openingBlack',
        lineList: [
          {
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

  handleSelectColor(event: any): void {
    this.selectedColor = event.option.value;
    this.showBlackOpenings.resetSelection();
    this.showWhiteOpenings.resetSelection();
  }
}
