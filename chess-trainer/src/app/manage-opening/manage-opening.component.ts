import { HttpClient } from '@angular/common/http';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { WHITE } from '../common/model/constant/constant';
import { CreateLineBody } from '../common/model/interface/create-line-body';
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
  public lineUuid: string = '';
  public openingList: Opening[] = [];
  constructor(private http: HttpClient, private dialog: MatDialog) {
    HttpUtils.getOpenings(this.http).subscribe((returnedOpenings) => {
      this.openingList = [...returnedOpenings];
    });
  }

  ngOnInit(): void {}

  handleRemove(): void {
    console.log('Remove opening');
    HttpUtils.deleteLine(this.lineUuid, this.http).subscribe((returnedOpenings) => {
      this.openingList = [...returnedOpenings];
    });
    this.lineUuid = '';
  }

  handleAdd(): void {
    const dialogRef = this.dialog.open(CreateLineDialogComponent);

    dialogRef.afterClosed().subscribe((result) => {
      console.log('The dialog was closed');
      console.log(result);
      let body: CreateLineBody = {
        lineColor: WHITE,
        lineName: 'lineName',
        openingName: 'openingName',
      };
      HttpUtils.postCreateLine(body, this.http).subscribe((line) =>
        HttpUtils.getOpenings(this.http).subscribe((returnedOpenings) => {
          this.openingList = [...returnedOpenings];
        })
      );
    });
  }

  handleRename(): void {
    console.log('Rename opening');
  }

  handleCopy(): void {
    console.log('Copy opening');
  }

  handleSelectLine(line: Line) {
    this.lineUuid = line.uuid;
  }
}
