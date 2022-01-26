import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Line } from '../common/model/interface/line';
import { Opening } from '../common/model/interface/opening';
import { HttpUtils } from '../common/utils/http-utils';

@Component({
  selector: 'app-practice-opening',
  templateUrl: './practice-opening.component.html',
  styleUrls: ['./practice-opening.component.scss'],
})
export class PracticeOpeningComponent implements OnInit {
  public openingList: Opening[] = [];
  public selectedLineUuid: string = '';
  private selectedOpeningName: string = '';
  public selectedLineColor: string = '';

  constructor(private http: HttpClient, private dialog: MatDialog) {
    HttpUtils.getOpenings(this.http).subscribe((returnedOpenings) => {
      this.openingList = [...returnedOpenings];
    });
  }

  ngOnInit(): void {}

  handleSelectLine(line: Line) {
    this.selectedLineUuid = line.uuid;
    this.selectedLineColor = line.color;
  }

  handleSelectOpening(opening: Opening) {
    this.selectedOpeningName = opening.name;
  }
}
