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
import { HttpClientModule } from '@angular/common/http';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { SelectOpeningComponent } from './common/component/select-opening/select-opening.component';
import { ManageOpeningComponent } from './manage-opening/manage-opening.component';
import { ShowOpeningsComponent } from './common/component/select-opening/show-openings/show-openings.component';
import { CreateLineDialogComponent } from './manage-opening/create-line-dialog/create-line-dialog.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatRadioModule } from '@angular/material/radio';
import { MatInputModule } from '@angular/material/input';
import { MainUserComponent } from './main-user/main-user.component';
import { PracticeOpeningComponent } from './practice-opening/practice-opening.component';
import { PracticeBoardComponent } from './practice-board/practice-board.component';
import { CardsBoardComponent } from './cards-board/cards-board.component';
import { PracticeFooterPanelComponent } from './practice-board/practice-footer-panel/practice-footer-panel.component';
import { PracticeShowAnswerDialogComponent } from './practice-board/practice-show-answer-dialog/practice-show-answer-dialog.component';
import { MatButtonModule } from '@angular/material/button';
import { NavigationBarComponent } from './common/component/navigation-bar/navigation-bar.component';
import { MatToolbarModule } from '@angular/material/toolbar';

@NgModule({
  declarations: [
    AppComponent,
    BoardComponent,
    EditionBoardComponent,
    EditionVerticalPanelComponent,
    EditionFooterPanelComponent,
    MovePipe,
    ReplaceMoveConfirmationDialogComponent,
    SelectOpeningComponent,
    ManageOpeningComponent,
    ShowOpeningsComponent,
    CreateLineDialogComponent,
    MainUserComponent,
    PracticeOpeningComponent,
    PracticeBoardComponent,
    CardsBoardComponent,
    PracticeFooterPanelComponent,
    PracticeShowAnswerDialogComponent,
    NavigationBarComponent,
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
    HttpClientModule,
    MatButtonToggleModule,
    MatFormFieldModule,
    FormsModule,
    ReactiveFormsModule,
    MatRadioModule,
    MatInputModule,
    MatButtonModule,
    MatToolbarModule,
  ],
  providers: [MovePipe],
  bootstrap: [AppComponent],
})
export class AppModule {}
