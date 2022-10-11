import {Injectable} from '@angular/core';
import {AccountDataService} from "./data/account-data.service";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor() { }

  isUserLoggedIn() {
    let user = sessionStorage.getItem('authenticatedUser');
    return !(user === null)
  }

  destroySession() {
    sessionStorage.clear();
  }

}
