import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';



import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AppComponent} from "./app.component";
import { HeaderComponent } from './componants/header/header.component';
import { FooterComponent } from './componants/footer/footer.component';
import { SignUpComponent } from './componants/sign-up/sign-up.component';
import { LoginComponent } from './componants/login/login.component';
import {RouterModule, Routes} from "@angular/router";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { MainPageComponent } from './componants/main-page/main-page.component';
import {ScreenActivator} from "../service/Activator/screen-activator";
import {LoginActivator} from "../service/Activator/login-activator";
import {APP_BASE_HREF} from "@angular/common";
import {AuthInterceptor} from "../service/interceptors/auth-interceptor";
import { TeamComponent } from './componants/team/team.component';
import { TeamActionsComponent } from './componants/team-actions/team-actions.component';
import { ClientsComponent } from './componants/clients/clients.component';
import { ClientsActionsComponent } from './componants/clients-actions/clients-actions.component';
import { BankComponent } from './componants/bank/bank.component';
import { BankActionsComponent } from './componants/bank-actions/bank-actions.component';
import { CreatingBillComponent } from './componants/creating-bill/creating-bill.component';
import { BillsComponent } from './componants/bills/bills.component';

const routes: Routes = [
  {path: 'mainpage', component: MainPageComponent,canActivate: [ScreenActivator]},
  {path: 'login', component: LoginComponent, canActivate: [LoginActivator]},
  {path: 'signup', component: SignUpComponent, canActivate: [LoginActivator]},
  { path: 'team', component: TeamComponent ,canActivate: [ScreenActivator]},
  { path: 'team-action/:key/:teamId', component: TeamActionsComponent ,canActivate: [ScreenActivator]},
  {path: 'clients',component: ClientsComponent,canActivate: [ScreenActivator]  },
  {path: 'clientsactions/:key',component: ClientsActionsComponent,canActivate: [ScreenActivator]  },
  { path: 'clientsactions/addcar/:customerId', component: ClientsActionsComponent ,canActivate: [ScreenActivator]},
  {path: 'clientsactions/updatecustomer/:id', component: ClientsActionsComponent, canActivate: [ScreenActivator]},
  {path: 'clientsactions/updatecar/:id', component: ClientsActionsComponent, canActivate: [ScreenActivator], data: { customerId: true }  // Add this to indicate we need customer ID
  },
  { path: 'bank', component: BankComponent ,canActivate: [ScreenActivator]},
  { path: 'bank-actions', component: BankActionsComponent ,canActivate: [ScreenActivator]},
  { path: 'create-bill', component: CreatingBillComponent ,canActivate: [ScreenActivator]},
  { path: 'bills', component: BillsComponent ,canActivate: [ScreenActivator]},
  { path: 'bills/:customerId', component: BillsComponent ,canActivate: [ScreenActivator]},
  { path: '', redirectTo: '/mainpage', pathMatch: 'full' },
  { path: '**', redirectTo: '/mainpage', pathMatch: 'full' }
];




@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    SignUpComponent,
    LoginComponent,
    MainPageComponent,
    TeamComponent,
    TeamActionsComponent,
    ClientsComponent,
    ClientsActionsComponent,
    BankComponent,
    BankActionsComponent,
    CreatingBillComponent,
    BillsComponent,
  ],
    imports: [
      RouterModule.forRoot(routes),
      BrowserModule,
      HttpClientModule,
      //NgbPaginationModule,
      FormsModule,
      ReactiveFormsModule
    ],
  providers: [
    { provide: APP_BASE_HREF, useValue: '/' }
    , {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true},
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
