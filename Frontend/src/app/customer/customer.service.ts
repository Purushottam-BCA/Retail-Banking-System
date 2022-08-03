import { HttpClient, HttpHeaders } from '@angular/common/http';
import { HostListener, Injectable } from '@angular/core';
import { AuthService } from '../authenticate/auth.service';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  
  constructor(private httpClient: HttpClient,
    private authService: AuthService) { }

  @HostListener('window:popstate', ['$event'])
  onPopState(event) {
    this.authService.isAuthenticated.next(false);
    this.authService.logout();
  }

  customerDetails(id:string){
    let header = new HttpHeaders().set(
      "Authorization",
      localStorage.getItem("token")
      );
      return this.httpClient.get("http://localhost:8085/customer/getCustomerDetails/"+id,{headers:header});
    }
    
    withdraw(withdrawdetails){
      let header = new HttpHeaders().set(
        "Authorization",
        localStorage.getItem("token")
    );
    console.log(withdrawdetails);
    return this.httpClient.post("http://localhost:8086/account-ms/withdraw",withdrawdetails,{headers:header});
  }
  
  deposit(transferdetails){
    let header = new HttpHeaders().set(
      "Authorization",
      localStorage.getItem("token")
      );
      return this.httpClient.post("http://localhost:8086/account-ms/deposit",transferdetails,{headers:header});
    }
    
  getAccount(accountId) {
    let header = new HttpHeaders().set(
      "Authorization",
      localStorage.getItem("token")
      );
    return this.httpClient.get("http://localhost:8087/transaction-ms/getAllTransByAccId/"+accountId,{headers:header}); 
  }

  transfer(out){
    let header = new HttpHeaders().set(
      "Authorization",
      localStorage.getItem("token")
      );
    return this.httpClient.post("http://localhost:8086/account-ms/transaction",out,{headers:header});
  }
}
  