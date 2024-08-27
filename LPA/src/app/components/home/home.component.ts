import { Component, OnInit } from "@angular/core";
import { RouterLink } from "@angular/router";

@Component({
  selector: "app-home",
  standalone: true,
  imports: [RouterLink],
  templateUrl: "./home.component.html",
  styleUrl: "./home.component.scss",
})
export class HomeComponent implements OnInit {
  // fullImagePath: string | any;
  // public logoPath = require("./assets/logo-color.svg");
  constructor() {
    // this.fullImagePath = "../../assets/images/logo-color.svg";
  }
  ngOnInit(): void {}
}
