import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditionBoardComponent } from './edition-board.component';

describe('EditionBoardComponent', () => {
  let component: EditionBoardComponent;
  let fixture: ComponentFixture<EditionBoardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditionBoardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditionBoardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
