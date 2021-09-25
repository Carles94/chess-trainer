import { Component, OnInit, ViewChild } from '@angular/core';
import { NgxChessBoardService, NgxChessBoardView } from 'ngx-chess-board';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.scss'],
})
export class BoardComponent implements OnInit {
  @ViewChild('board', { static: false })
  board!: NgxChessBoardView;

  constructor(private ngxChessBoardService: NgxChessBoardService) {}

  ngOnInit(): void {}
}
