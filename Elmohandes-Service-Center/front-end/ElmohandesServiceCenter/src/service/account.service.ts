import { Injectable } from '@angular/core';
import {AccountDto} from "../model/account-dto";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private baseUrl = 'http://localhost:1050';

  constructor(private http: HttpClient) {}

  createBalance(account: AccountDto): Observable<AccountDto> {
    return this.http.post<AccountDto>(`${this.baseUrl}/createbalance`, account);
  }

  getAccount(teamId: number): Observable<AccountDto[]> {
    return this.http.get<AccountDto[]>(`${this.baseUrl}/getAccount/${teamId}`);
  }
}
