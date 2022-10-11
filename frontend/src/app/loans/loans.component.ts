import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {CreditFacilityDataService} from "../service/data/credit-facility-data.service";
import {LoanDataService} from "../service/data/loan-data.service";
import {AuthenticationService} from "../service/authentication.service";

@Component({
  selector: 'app-loans',
  templateUrl: './loans.component.html',
  styleUrls: ['./loans.component.css']
})
export class LoansComponent implements OnInit {

  isEmpty: boolean = false;
  loans: any = {};

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    public loanDataService: LoanDataService,
    public authenticationService: AuthenticationService
  ) { }

  ngOnInit(): void {
    if (!this.authenticationService.isUserLoggedIn())
      this.router.navigate(['login']);

    this.activatedRoute.params.subscribe(params => {
      console.log(params["applicantId"]);
      this.loanDataService.executeListLoansService(params["applicantId"]).subscribe(response => {
        if (response)
          this.loans = response;
        else
          this.isEmpty = true;
      });
    });

  }

}
