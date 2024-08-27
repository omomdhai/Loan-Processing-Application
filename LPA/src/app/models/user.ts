export interface User {
  firstName: string;

  middleName?: string;

  lastName: string;

  dateOfBirth: Date;

  maritalStatus: string;

  ssnNumber: number;

  loanAmount: number;

  loanPurpose: string;

  description?: string;

  addressLine1: string;

  addressLine2?: string;

  city: string;

  state: string;

  postalCode: number;

  homePhone: number;

  officePhone?: number;

  mobile: number;

  email: string;

  employerName: string;

  annualSalary: number;

  workExperienceYears: number;

  workExperienceMonths: number;

  designation?: string;

  employerAddress: string;

  employerCity: string;

  employerState: string;

  employerPostalCode: number;

  submittedDate: Date;

  score: number;

  declineReason: String;

  applicationStatus: String;
}
