import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class LoanDataService {

  constructor(
    private http: HttpClient
  ) { }

  executeOpenLoanService(amount: Number, type: string, creditId: Number) {
    return this.http.post('http://localhost:8080/create/loan', {
      amount: amount,
      type: type,
      creditId: creditId
    });
  }

  executeListLoansService(applicantId: Number) {
    return this.http.get('http://localhost:8080/get/loan/all/' + applicantId);
  }
}
