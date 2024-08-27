export class Applicant {
  constructor(
    public firstName: string,
    public middleName: string,
    public lastName: string,
    public dateOfBirth: string,
    public maritalStatus: string,
    public ssnNumber: number,
    public loanAmount: number,
    public loanPurpose: string,
    public description: string,
    public address: string,
    public state: string,
    public city: string,
    public zipCode: number,
    public phoneNumber: number,
    public email: string,
    public employerName: string,
    public salary: number,
    public workExperience: number,
    public designation: string,
    public submittedDate: string
  ) {}
}
