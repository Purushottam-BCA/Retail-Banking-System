import { Component, ElementRef, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'BankingSystem';
  private href="/";
  private value=false;

  constructor(private router: Router,
    private elementRef: ElementRef){}
  
  ngOnInit() {
  }

  

}
