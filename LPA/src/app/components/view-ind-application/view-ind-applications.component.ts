import { CommonModule } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import {
  FormGroup,
  FormBuilder,
  NgModel,
  FormsModule,
  ReactiveFormsModule,
  FormControl,
} from "@angular/forms";
import { ActivatedRoute, Router, RouterLink } from "@angular/router";
import { User } from "../../models/user";
import { UserService } from "../../services/user.service";
@Component({
  standalone: true,
  imports: [RouterLink, FormsModule, ReactiveFormsModule, CommonModule],
  selector: "app-view-applications",
  templateUrl: "./view-ind-applications.component.html",
  styleUrl: "./view-ind-applications.component.scss",
})
export class ViewSingleApplicationComponent implements OnInit {
  userId: number | any;
  applicationStatus: string | any;
  score: number | any;
  declineReason: string | any;
  user: User | any;

  formData = new FormGroup<any>({
    firstName: new FormControl("this.user.firstName"),
    middleName: new FormControl(""),
    lastName: new FormControl(""),
    dateOfBirth: new FormControl(""),
    maritalStatus: new FormControl(""),
    ssnNumber: new FormControl(""),
    loanAmount: new FormControl(""),
    loanPurpose: new FormControl(""),
    description: new FormControl(""),
    addressLine1: new FormControl(""),
    addressLine2: new FormControl(""),
    city: new FormControl(""),
    state: new FormControl(""),
    postalCode: new FormControl(""),
    homePhone: new FormControl(""),
    officePhone: new FormControl(""),
    mobile: new FormControl(""),
    email: new FormControl(""),
    employerName: new FormControl(""),
    annualSalary: new FormControl(""),
    workExperienceYears: new FormControl(""),
    workExperienceMonths: new FormControl(""),
    designation: new FormControl(""),
    employerAddress: new FormControl(""),
    employerCity: new FormControl(""),
    employerState: new FormControl(""),
    employerPostalCode: new FormControl(""),
    score: new FormControl(""),
    submittedDate: new FormControl(""),
    declineReason: new FormControl(""),
    applicationStatus: new FormControl(""),
  });

  /*********/
  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private userService: UserService // public formData: FormGroup,
  ) {}

  /************/
  ngOnInit(): void {
    this.applicationStatus = "";
    this.declineReason = "";
    this.activatedRoute.paramMap.subscribe((params) => {
      this.userId = params.get("id");
    });

    /*********/
    this.userService.getSingleApplication(this.userId).subscribe(
      (response) => {
        console.log("response-method", response);
        this.user = response;
        console.log("user1", this.user); // this is getting printed properly ✅
        if (this.user != null) {
          this.applicationStatus = this.user.applicationStatus;
          this.declineReason = this.user.declineReason;
          this.score = this.user.score;

          this.formData.patchValue(this.user);
          this.formData.disable();
        } else {
          alert("No such user exists");
          // this.router.navigate(["/"]);
        }
      },
      (error) => {
        console.log(error);
        alert("Fetching User Data failed");
      }
    );
    /*************/

    console.log("user-out", this.user); // this doesn't gets printed properly 😢
  }
  
  onBack(): void {
    this.router.navigateByUrl("/view-applied-applications"); // Or any other route to navigate back
  }

  populateForm(): void {
    this.formData = new FormGroup({
      firstName: new FormControl(this.user.firstName),
      middleName: new FormControl(this.user.middleName),
      lastName: new FormControl(this.user.lastName),
      dateOfBirth: new FormControl(this.user.dateOfBirth),
      maritalStatus: new FormControl(this.user.maritalStatus),
      ssnNumber: new FormControl(this.user.ssnNumber),
      loanAmount: new FormControl(this.user.loanAmount),
      loanPurpose: new FormControl(this.user.loanPurpose),
      description: new FormControl(this.user.description),
      addressLine1: new FormControl(this.user.addressLine1),
      addressLine2: new FormControl(this.user.addressLine2),
      city: new FormControl(this.user.city),
      state: new FormControl(this.user.state),
      postalCode: new FormControl(this.user.postalCode),
      homePhone: new FormControl(this.user.homePhone),
      officePhone: new FormControl(this.user.officePhone),
      mobile: new FormControl(this.user.mobile),
      email: new FormControl(this.user.email),
      employerName: new FormControl(this.user.employerName),
      annualSalary: new FormControl(this.user.annualSalary),
      workExperienceYears: new FormControl(this.user.workExperienceYears),
      workExperienceMonths: new FormControl(this.user.workExperienceMonths),
      designation: new FormControl(this.user.designation),
      employerAddress: new FormControl(this.user.employerAddress),
      employerCity: new FormControl(this.user.employerCity),
      employerState: new FormControl(this.user.employerState),
      employerPostalCode: new FormControl(this.user.employerPostalCode),
    });
  }
}