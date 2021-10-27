import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgxChessBoardModule } from 'ngx-chess-board';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BoardComponent } from './common/component/board/board.component';
import { EditionBoardComponent } from './edition-board/edition-board.component';
import { MatCardModule } from '@angular/material/card';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatDividerModule } from '@angular/material/divider';
import { MatDialogModule } from '@angular/material/dialog';
import { MatListModule } from '@angular/material/list';
import { EditionVerticalPanelComponent } from './edition-board/edition-vertical-panel/edition-vertical-panel.component';
import { EditionFooterPanelComponent } from './edition-board/edition-footer-panel/edition-footer-panel.component';
import { MovePipe } from './common/pipe/move.pipe';
import { ReplaceMoveConfirmationDialogComponent } from './common/component/replace-move-confirmation-dialog/replace-move-confirmation-dialog.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    BoardComponent,
    EditionBoardComponent,
    EditionVerticalPanelComponent,
    EditionFooterPanelComponent,
    MovePipe,
    ReplaceMoveConfirmationDialogComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgxChessBoardModule.forRoot(),
    MatCardModule,
    MatGridListModule,
    MatDividerModule,
    MatListModule,
    MatDialogModule,
    BrowserAnimationsModule,
  ],
  providers: [MovePipe],
  bootstrap: [AppComponent],
})
export class AppModule {}
