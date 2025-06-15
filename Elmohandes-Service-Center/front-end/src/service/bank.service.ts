import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Bank} from "../model/bank";
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class BankService {

  private apiUrl = 'http://localhost:1050/getFields';
  private baseUrl = 'http://localhost:1050';

  constructor(private http: HttpClient) {}

  getBanks(): Observable<Bank[]> {
    return this.http.get<Bank[]>(this.apiUrl);
  }

  addField(bank: Bank): Observable<Bank> {
    return this.http.post<Bank>(`${this.baseUrl}/addField`, bank);
  }

  updateField(bank: Bank): Observable<Bank> {
    return this.http.post<Bank>(`${this.baseUrl}/updateField`, bank);
  }

  deleteField(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/deleteField/${id}`);
  }

  getBankById(id: number): Observable<Bank> {
    return this.getBanks().pipe(
      map(banks => banks.find(bank => bank.id === id)!)
    );
  }
}
