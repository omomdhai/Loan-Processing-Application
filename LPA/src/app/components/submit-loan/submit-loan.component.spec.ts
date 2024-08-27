import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubmitLoanComponent } from './submit-loan.component';

describe('SubmitLoanComponent', () => {
  let component: SubmitLoanComponent;
  let fixture: ComponentFixture<SubmitLoanComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SubmitLoanComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SubmitLoanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
