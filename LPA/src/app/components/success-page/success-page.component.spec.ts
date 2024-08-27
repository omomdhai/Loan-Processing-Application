import { ComponentFixture, TestBed } from '@angular/core/testing';
import { SuccessPageComponent } from './success-page.component';

// import { SuccessPageComponent } from './submission-view.component';

describe('SuccessPageComponent', () => {
  let component: SuccessPageComponent;
  let fixture: ComponentFixture<SuccessPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SuccessPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SuccessPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
