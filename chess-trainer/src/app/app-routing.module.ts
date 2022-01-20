import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EditionBoardComponent } from './edition-board/edition-board.component';
import { MainUserComponent } from './main-user/main-user.component';
import { ManageOpeningComponent } from './manage-opening/manage-opening.component';
import { PracticeOpeningComponent } from './practice-opening/practice-opening.component';

const routes: Routes = [
  { path: 'edition-board', component: EditionBoardComponent },
  { path: 'manage-opening', component: ManageOpeningComponent },
  { path: 'main-user', component: MainUserComponent },
  { path: 'practice-opening', component: PracticeOpeningComponent },
  { path: '', redirectTo: '/main-user', pathMatch: 'full' }, // default path
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
