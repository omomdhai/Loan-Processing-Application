import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { HttpClientModule, provideHttpClient } from "@angular/common/http";
import { AppComponent } from "./app.component";
import { MatTableModule } from "@angular/material/table";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { AppRoutingModule } from "./app.routes";
import { SubmitLoanComponent } from "./submit-loan/submit-loan.component";
import { HomeComponent } from "./components/home/home.component";
import { ViewAllApplicationsComponent } from "./components/view-applications/view-applications.component";
import { ViewSingleApplicationComponent } from "./view-ind-application/view-ind-applications.component";
import { SuccessPageComponent } from "./components/success-page/success-page.component";
import { RouterLink } from "@angular/router";
import { CommonModule } from "@angular/common";

@NgModule({
  declarations: [],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatTableModule,
    ReactiveFormsModule,
    FormsModule,
    RouterLink,
    CommonModule,
  ],
  providers: [provideHttpClient()],
  // bootstrap: [AppComponent]
})
export class AppModule {}
