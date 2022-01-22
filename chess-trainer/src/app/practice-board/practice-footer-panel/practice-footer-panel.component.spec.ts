import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PracticeFooterPanelComponent } from './practice-footer-panel.component';

describe('PracticeFooterPanelComponent', () => {
  let component: PracticeFooterPanelComponent;
  let fixture: ComponentFixture<PracticeFooterPanelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PracticeFooterPanelComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PracticeFooterPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
