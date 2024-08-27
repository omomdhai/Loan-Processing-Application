import { Component, NgModule, OnInit } from "@angular/core";
import { Router, RouterLink } from "@angular/router";
import {
  FormControl,
  FormsModule,
  FormGroup,
  FormBuilder,
  Validators,
} from "@angular/forms";
import { ReactiveFormsModule } from "@angular/forms";
// import { NgForm } from "@angular/forms";
// import { UserService } from "../services/user.service";
import { User } from "../../models/user";
import { CommonModule } from "@angular/common";
import { UserService } from "../../services/user.service";

@Component({
  selector: "app-submit-loan",
  standalone: true,
  imports: [RouterLink, FormsModule, ReactiveFormsModule, CommonModule],
  templateUrl: "./submit-loan.component.html",
  styleUrls: ["./submit-loan.component.scss"],
})
export class SubmitLoanComponent {
  formData = new FormGroup({
    firstName: new FormControl("", [
      Validators.required,
      Validators.maxLength(255),
    ]),

    middleName: new FormControl("", Validators.maxLength(255)),

    lastName: new FormControl("", [
      Validators.required,
      Validators.maxLength(255),
    ]),

    dateOfBirth: new FormControl("", [Validators.required]),

    maritalStatus: new FormControl("", [Validators.required]),

    ssnNumber: new FormControl("", [Validators.required]),

    loanAmount: new FormControl("", [
      Validators.required,
      Validators.pattern(/^\d+(\.\d{1,2})?$/),
      Validators.max(1e9),
    ]),

    loanPurpose: new FormControl("", [Validators.required]),

    description: new FormControl(""),

    addressLine1: new FormControl("", [
      Validators.required,
      Validators.maxLength(255),
    ]),

    addressLine2: new FormControl("", Validators.maxLength(255)),

    city: new FormControl("", [Validators.required, Validators.maxLength(255)]),

    state: new FormControl("", [
      Validators.required,
      Validators.maxLength(255),
    ]),

    postalCode: new FormControl("", [
      Validators.required,
      Validators.pattern(/^[0-9]{5}$/),
    ]),

    homePhone: new FormControl("", [
      Validators.required,
      Validators.pattern(/^[0-9]{10}$/),
      // Validators.maxLength(10),
    ]),

    officePhone: new FormControl("", [
      Validators.pattern(/^[0-9]{10}$/),
      // Validators.maxLength(10),
    ]),

    mobile: new FormControl("", [
      Validators.required,
      Validators.pattern(/^[0-9]{10}$/),
      // Validators.maxLength(10),
    ]),

    email: new FormControl("", [Validators.required, Validators.email]),

    employerName: new FormControl("", [
      Validators.required,
      Validators.maxLength(255),
    ]),

    annualSalary: new FormControl("", [
      Validators.required,
      Validators.pattern(/^\d+(\.\d{1,2})?$/),
    ]),

    designation: new FormControl(""),

    workExperienceYears: new FormControl("", [
      Validators.required,
      Validators.pattern(/^[0-9]{0,2}$/),
    ]),

    workExperienceMonths: new FormControl("", [
      Validators.required,
      Validators.pattern(/^(0?[0-9]|1[0-2])$/),
    ]),

    employerAddress: new FormControl("", [
      Validators.required,
      Validators.maxLength(255),
    ]),
    employerState: new FormControl("", [
      Validators.required,
      Validators.maxLength(255),
    ]),
    employerCity: new FormControl("", [
      Validators.required,
      Validators.maxLength(255),
    ]),
    employerPostalCode: new FormControl("", [
      Validators.required,
      Validators.pattern(/^[0-9]{5}$/),
      // Validators.maxLength(5),
    ]),
  });
  ngOnInit() {}

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService
  ) {}

  formDataToUser(formData: FormGroup): User {
    const user: User = {
      score: formData.value.score,
      declineReason: formData.value.declineReason,
      applicationStatus: formData.value.applicationStatus,
      firstName: formData.value.firstName,
      middleName: formData.value.middleName,
      lastName: formData.value.lastName,
      dateOfBirth: new Date(formData.value.dateOfBirth),
      maritalStatus: formData.value.maritalStatus,
      ssnNumber: formData.value.ssnNumber,
      loanAmount: formData.value.loanAmount,
      loanPurpose: formData.value.loanPurpose,
      description: formData.value.description,
      addressLine1: formData.value.addressLine1,
      addressLine2: formData.value.addressLine2,
      city: formData.value.city,
      state: formData.value.state,
      postalCode: Number(formData.value.postalCode),
      homePhone: Number(formData.value.homePhone),
      officePhone: Number(formData.value.officePhone),
      mobile: Number(formData.value.mobile),
      email: formData.value.email,
      employerName: formData.value.employerName,
      annualSalary: Number(formData.value.annualSalary),
      designation: formData.value.designation,
      workExperienceYears: Number(formData.value.workExperienceYears),
      workExperienceMonths: Number(formData.value.workExperienceMonths),
      employerAddress: formData.value.employerAddress,
      employerState: formData.value.employerState,
      employerCity: formData.value.employerCity,
      employerPostalCode: Number(formData.value.employerPostalCode),
      submittedDate: new Date(Date.now()),
    };
    return user;
  }
  onBack(): void {
    this.router.navigateByUrl("/"); // Or any other route to navigate back
  }
  onSubmit(): void {
    if (this.formData.valid) {
      let user = this.formDataToUser(this.formData);
      const dateOfBirth = new Date(user.dateOfBirth);
      const annualSalary = parseFloat(user.annualSalary.toString());
      const workExperienceYears = parseFloat(
        user.workExperienceYears.toString()
      );
      const workExperienceMonths = parseFloat(
        user.workExperienceMonths.toString()
      );
      /* age calculation logic */
      const today = new Date();
      let age = today.getFullYear() - dateOfBirth.getFullYear();
      const monthDifference = today.getMonth() - dateOfBirth.getMonth();
      if (
        monthDifference < 0 ||
        (monthDifference === 0 && today.getDate() < dateOfBirth.getDate())
      ) {
        age--;
      }
      /* experience calculation logic */
      const totalWorkExperienceMonths =
        workExperienceYears * 12 + workExperienceMonths;

      /* valid frontend checks */
      if (age < 18 || age > 65) {
        alert(
          "Application declined: Applicant’s age must be between 18 and 65."
        );
        this.router.navigateByUrl("/apply-loan");
        return;
      }

      if (totalWorkExperienceMonths < 6) {
        alert(
          "Application declined: Applicant’s work experience must be at least 6 months."
        );
        this.router.navigateByUrl("/apply-loan");

        return;
      }

      if (annualSalary < 10000) {
        alert(
          "Application declined: Applicant’s annual salary must be at least $10,000."
        );
        this.router.navigateByUrl("/apply-loan");
        return;
      }
      /* post request to submit form */
      this.userService.addUser(user).subscribe(
        (response) => {
          console.log(response);
          alert("Form submitted successfully");

          this.router.navigateByUrl("/application-submitted");
        },
        (error) => {
          console.log(error);
          alert("Form submission failed");
        }
      );
    } else {
      alert(
        "Please fill valid details in all required fields before submitting."
      );
    }
  }
}
