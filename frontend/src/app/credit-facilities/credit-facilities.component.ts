import { Component, OnInit } from '@angular/core';
import {CreditFacilityDataService} from "../service/data/credit-facility-data.service";
import {Router} from "@angular/router";
import {AuthenticationService} from "../service/authentication.service";

@Component({
  selector: 'app-credit-facilities',
  templateUrl: './credit-facilities.component.html',
  styleUrls: ['./credit-facilities.component.css']
})
export class CreditFacilitiesComponent implements OnInit {
  creditFacilities: any = {};
  isCreated: boolean = false;
  isCreatedError: boolean = false;

  constructor(
    private router: Router,
    public creditFacilityDataService: CreditFacilityDataService,
    public authenticationService: AuthenticationService
  ) { }

  ngOnInit(): void {
    if (!this.authenticationService.isUserLoggedIn())
      this.router.navigate(['login']);

    this.creditFacilityDataService.executeListCreditFacilityService().subscribe(response => {
      this.creditFacilities = response;
    });
  }

}
