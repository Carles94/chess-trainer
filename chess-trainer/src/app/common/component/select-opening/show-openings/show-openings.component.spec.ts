import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowOpeningsComponent } from './show-openings.component';

describe('ShowOpeningsComponent', () => {
  let component: ShowOpeningsComponent;
  let fixture: ComponentFixture<ShowOpeningsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowOpeningsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowOpeningsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
