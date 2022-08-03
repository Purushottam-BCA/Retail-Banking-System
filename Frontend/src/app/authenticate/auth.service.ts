import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { map } from 'rxjs/operators'

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isAuthenticated = new Subject<boolean>();
  clearTimer;

  constructor(private httpClient: HttpClient,
    private router:Router) { 
  }


  public loginuser(user){
    // this.autoLogout(360000);
    return this.httpClient.post('http://localhost:8084/auth-ms/login',user);
  }


  logout() {
    localStorage.removeItem('currentUser');
    localStorage.removeItem('token');
    if(this.clearTimer){
      clearTimeout(this.clearTimer);
      this.router.navigate(['/']);
    }
    this.isAuthenticated.next(false);
  }

  autoLogout(expirationDate: number){
    console.log(expirationDate);
    this.clearTimer=setTimeout(()=>{
      this.logout();
    },expirationDate);
  }

}
