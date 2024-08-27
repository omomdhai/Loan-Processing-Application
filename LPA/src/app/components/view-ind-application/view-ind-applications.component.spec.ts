import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ViewSingleApplicationComponent } from './view-ind-applications.component';

describe('ViewSingleApplicationComponent', () => {
  let component: ViewSingleApplicationComponent;
  let fixture: ComponentFixture<ViewSingleApplicationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewSingleApplicationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewSingleApplicationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
