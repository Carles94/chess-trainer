import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DeleteLineBody } from '../common/model/interface/delete-line-body';
import { Line } from '../common/model/interface/line';
import { Opening } from '../common/model/interface/opening';
import { HttpUtils } from '../common/utils/http-utils';
import { CreateLineDialogComponent } from './create-line-dialog/create-line-dialog.component';

@Component({
  selector: 'app-manage-opening',
  templateUrl: './manage-opening.component.html',
  styleUrls: ['./manage-opening.component.scss'],
})
export class ManageOpeningComponent implements OnInit {
  public openingList: Opening[] = [];
  public selectedLineUuid: string = '';
  private selectedOpeningName: string = '';

  constructor(private http: HttpClient, private dialog: MatDialog) {
    HttpUtils.getOpenings(this.http).subscribe((returnedOpenings) => {
      this.openingList = [...returnedOpenings];
    });
  }

  ngOnInit(): void {}

  handleDelete(): void {
    let body: DeleteLineBody = {
      lineUuid: this.selectedLineUuid,
      openingName: this.selectedOpeningName,
    };
    HttpUtils.deleteLine(body, this.http).subscribe((returnedOpenings) => {
      this.openingList = [...returnedOpenings];
    });
    this.selectedLineUuid = '';
  }

  handleAdd(): void {
    // TODO add a check in order to not add  2 lines with the same name
    let lineNames: string[] = [];
    this.openingList.forEach((opening) => (lineNames = lineNames.concat(opening.lineList.map((line) => line.name))));
    const dialogRef = this.dialog.open(CreateLineDialogComponent, {
      data: {
        lineNames: lineNames,
      },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        HttpUtils.postCreateLine(result, this.http).subscribe((line) =>
          HttpUtils.getOpenings(this.http).subscribe((returnedOpenings) => {
            this.openingList = [...returnedOpenings];
          })
        );
      }
    });
  }

  handleSelectLine(line: Line) {
    this.selectedLineUuid = line.uuid;
  }

  handleSelectOpening(opening: Opening) {
    this.selectedOpeningName = opening.name;
  }
}
