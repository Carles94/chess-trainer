import { ThrowStmt } from '@angular/compiler';
import { Component, OnInit, ViewChild } from '@angular/core';
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
  @ViewChild('showWhiteOpenings', { static: false })
  public showWhiteOpenings!: ShowOpeningsComponent;
  @ViewChild('showWhiteOpenings', { static: false })
  public showBlackOpenings!: ShowOpeningsComponent;
  public WHITE = WHITE;
  public BLACK = BLACK;
  public openingList: Opening[] = [];
  public selectedColor = '';

  constructor() {
    this.openingList.push({
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
    });
  }

  ngOnInit(): void {}

  handleSelectColor(event: any): void {
    console.log(event);
    this.selectedColor = event.option.value;
    this.showBlackOpenings.resetSelection();
    this.showWhiteOpenings.resetSelection();
  }
}
