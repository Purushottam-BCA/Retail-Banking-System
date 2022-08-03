import { Component, HostListener, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../authenticate/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  private login: boolean=false;
  private value = "Login";

  constructor(private authService: AuthService,
    private router: Router) {
      this.authService.isAuthenticated.subscribe(val => {
        if(val==true){
          this.value="LogOut";
          this.login=true;
        }
        console.log(this.login);
      })
  }
  
  ngOnInit() {
  }  
}
