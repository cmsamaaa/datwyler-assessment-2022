import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {LoanDataService} from "../service/data/loan-data.service";
import {FormGroup} from "@angular/forms";
import {AuthenticationService} from "../service/authentication.service";

@Component({
  selector: 'app-loan-form',
  templateUrl: './loan-form.component.html',
  styleUrls: ['./loan-form.component.css']
})
export class LoanFormComponent implements OnInit {
  applicantId: number;
  creditId: number;
  amount: number | any;
  type: string = 'Home';
  invalidAmount: boolean = false;
  isError: boolean = false;

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
      this.applicantId = params['applicantId'];
      this.creditId = params['creditId'];
    });
  }

  handleOpenLoan(): void {
    if (this.countDecimals(this.amount) <= 2 || this.amount === 0) {
      this.loanDataService.executeOpenLoanService(this.amount, this.type, this.creditId).subscribe(response => {
        if (response)
          this.router.navigate(['loans', this.applicantId]);
        else {
          this.isError = true;
          this.invalidAmount = false;
        }
      });
    }
    else {
      this.invalidAmount = true;
    }
  }

  countDecimals(value: number): number {
    if ((value % 1) != 0)
      return value.toString().split(".")[1].length;
    return 0;
  };

}
