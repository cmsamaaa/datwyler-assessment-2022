import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {ApplicantDataService} from "../service/data/applicant-data.service";
import {CreditFacilityDataService} from "../service/data/credit-facility-data.service";
import {AuthenticationService} from "../service/authentication.service";

@Component({
  selector: 'app-applicants',
  templateUrl: './applicants.component.html',
  styleUrls: ['./applicants.component.css']
})
export class ApplicantsComponent implements OnInit {
  isCreated: boolean = false;
  isCreatedError: boolean = false;
  applicantId: Number = 0;
  applicants: any = {};

  constructor(
    private router: Router,
    public applicantDataService: ApplicantDataService,
    public creditFacilityDataService: CreditFacilityDataService,
    public authenticationService: AuthenticationService
  ) { }

  ngOnInit(): void {
    if (!this.authenticationService.isUserLoggedIn())
      this.router.navigate(['login']);

    this.applicantDataService.executeListApplicantService().subscribe(response => {
      this.applicants = response;
    });
  }

  handleOpenNewCreditFacility(applicantId: Number) {
    this.creditFacilityDataService.executeCreateCreditFacilityService(applicantId).subscribe(response => {
      if (response) {
        this.applicantId = applicantId;
        this.isCreated = true;
      }
      else
        this.isCreatedError = true;
    });
  }

}
