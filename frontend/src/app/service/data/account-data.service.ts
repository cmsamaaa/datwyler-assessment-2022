import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AccountDataService {

  constructor() { }

  executeLoginService(username: string, password: string) {
    if (username && password) {
      sessionStorage.setItem('authenticatedUser', username);
      return true;
    }

    return false;
  }
}
