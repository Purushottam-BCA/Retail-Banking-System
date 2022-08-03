import { Component, ElementRef, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from './customer.service';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {
  customerValue=null;
  transfermsg:string;
  withdrawmsg:string;
  userid:string;
  display=false;
  selectedValue:string;
  transactions;

  constructor(
    private customerservice:CustomerService,
    private router:Router
    ) { }

    ngOnInit() {
      this.userid = window.localStorage.getItem('userid');
      console.log(this.userid);
      this.getCustomerDetails();
    }

    onSelectedChange(val){
      console.log(val);
      this.selectedValue = val;
    }

    onDisplay(){
      this.customerservice.getAccount(this.selectedValue).subscribe((val)=>{
        this.transactions = val;
        console.log(this .transactions);
        this.display=true;
      },error=>{
        console.log("Error Occured");
      })
    }


  getCustomerDetails(){
    this.customerservice.customerDetails(this.userid).subscribe((val)=>{
      console.log(val);
      this.customerValue=val;
      
    })
  }

  onTransfer(out){
    console.log(out.value);
    let transactionInput={
      "sourceAccount": {
      "accountId": out.value.accountid,
      "amount" : out.value.amount },
      "targetAccount":{
      "accountId":out.value.targetAccount,
      "amount" : out.value.amount },
      "amount" : out.value.amount
  };
  console.log(transactionInput);
    this.customerservice.transfer(transactionInput).subscribe((val)=>{
      console.log(val);
      this.getCustomerDetails();
      this.transfermsg="Successfull Transaction...";
      location.reload();
    },error=>{
      this.transfermsg="Transaction Failed..";
      location.reload();
    })
    this.getCustomerDetails();
  }

  onWithdraw(details){
    console.log(details.value);
    let value={
      accountId:details.value.aId,
      amount:details.value.amount
    };
    this.customerservice.withdraw(value).subscribe((out)=>{
      this.withdrawmsg="Amount Successfully Withdrawn from Account";
      this.router.navigate(['/customerdashboard']);
      location.reload();
    },error=>{
      this.withdrawmsg="Please maintain minimum balance..";
    });
  }

}
