import { CommonModule } from "@angular/common";
import { Component, Inject } from "@angular/core";
import { Router, RouterLink } from "@angular/router";
import { UserService } from "../../services/user.service";
import { Application } from "../../models/application";
import { HttpErrorResponse } from "@angular/common/http";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

@Component({
  selector: "app-view-applications",
  standalone: true,
  imports: [CommonModule, RouterLink, FormsModule, ReactiveFormsModule],
  templateUrl: "./view-applications.component.html",
  styleUrl: "./view-applications.component.scss",
})
export class ViewAllApplicationsComponent {
  constructor(private router: Router, private userService: UserService) {}
  public applications: Application[] | any;
  ngOnInit() {
    this.getApplications();
  }

  public getApplications(): void {
    this.userService.getAllAplications().subscribe(
      (response: Application[]) => {
        response.forEach((app) => {
          const dateFormat = new Date(app.submittedDate);
          app.submittedDate =
            dateFormat.getDate() +
            "/" +
            (dateFormat.getMonth() + 1) +
            "/" +
            dateFormat.getFullYear();
        });
        console.log(response);
        this.applications = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  goBack() {
    this.router.navigate(["/"]);
  }
  navigateToApplication(id: number) {
    this.router.navigate(["/view-application", id]);
  }
}
