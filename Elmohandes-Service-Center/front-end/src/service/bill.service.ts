import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AddBillRequest} from "../model/add-bill-request";
import {FinalBillVm} from "../model/final-bill-vm";


@Injectable({
  providedIn: 'root'
})
export class BillService {
  private baseUrl = 'http://localhost:1050';
  private apiUrl = 'http://localhost:1050/getAllBills';
  constructor(private http: HttpClient) {}

  addBill(request: AddBillRequest): Observable<FinalBillVm> {
    return this.http.post<FinalBillVm>(`${this.baseUrl}/addBill`, request);
  }
  getAllBills(): Observable<FinalBillVm[]> {
    return this.http.get<FinalBillVm[]>(this.apiUrl);
  }
  getBillsByCustomerId(customerId: number) {
    return this.http.get<FinalBillVm[]>(`http://localhost:1050/getBillsByCustomerId/${customerId}`);
  }
  deleteBill(billId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/deleteBillById/${billId}`);
  }
}
