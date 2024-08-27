import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllApplicationsComponent } from './view-applications.component';

describe('ViewAllApplicationsComponent', () => {
  let component: ViewAllApplicationsComponent;
  let fixture: ComponentFixture<ViewAllApplicationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewAllApplicationsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewAllApplicationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
