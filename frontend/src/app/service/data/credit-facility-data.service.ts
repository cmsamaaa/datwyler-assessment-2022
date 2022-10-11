import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CreditFacilityDataService {

  constructor(
    private http: HttpClient
  ) { }

  executeCreateCreditFacilityService(applicantId: Number) {
    return this.http.post('http://localhost:8080/create/creditFacility', {
      applicantId: applicantId
    });
  }

  executeListCreditFacilityService() {
    return this.http.get('http://localhost:8080/get/credit-facility/all');
  }
}
