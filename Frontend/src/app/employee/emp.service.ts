import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmpService {
  
  customerdetails 
  
  constructor(private http: HttpClient) { }
  
  getDetails(val: any) {
    let header = new HttpHeaders().set(
      "Authorization",
      localStorage.getItem("token")
      );
    return this.http.get("http://localhost:8085/customer/getCustomerDetails/"+val,{headers:header});
  }

  createCustomer(cust){
    let header = new HttpHeaders().set(
      "Authorization",
      localStorage.getItem("token")
      );
    return this.http.post("http://localhost:8085/customer/createCustomer",cust,{headers: header});
  }

  viewCustomer(id){
    let header = new HttpHeaders().set(
      "Authorization",
      localStorage.getItem("token")
      );
    return this.http.get("http://localhost:8085/customer/getCustomerDetails/"+id,{headers: header});
  }

  createAccount(value,id){
    let header = new HttpHeaders().set(
      "Authorization",
      localStorage.getItem("token")
      );
    return this.http.post("http://localhost:8086/account-ms/createAccount/"+id,value,{headers:header});
  }

  onDeposit(val){
    let header = new HttpHeaders().set(
      "Authorization",
      localStorage.getItem("token")
    );
    return this.http.post("http://localhost:8086/account-ms/deposit",val,{headers:header});
  }

  onDelete(id){
    let header = new HttpHeaders().set(
      "Authorization",localStorage.getItem("token")
    );
    return this.http.delete("http://localhost:8085/customer/deleteCustomer/"+id,{headers:header});
  }

  onServiceCharge(){
    let header = new HttpHeaders().set(
      "Authorization",
      localStorage.getItem("token")
    );
    return this.http.post("http://localhost:8090/rules/serviceCharges",null,{headers:header});
  }

}
