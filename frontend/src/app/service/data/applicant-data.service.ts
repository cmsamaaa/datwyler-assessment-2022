import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ApplicantDataService {

  constructor(
    private http: HttpClient
  ) { }

  executeListApplicantService() {
    return this.http.get('http://localhost:8080/get/applicant/all');
  }
}
