import { Injectable } from '@angular/core';
import {Customer} from "../model/customer";
import {Observable, throwError} from "rxjs";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {catchError} from "rxjs/operators";
import {Car} from "../model/car";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private apiUrl = 'http://localhost:1050/addCustomer'; // Adjust base URL if needed
  private baseUrl = 'http://localhost:1050';
  constructor(private http: HttpClient) {}

  getCustomerById(id: number): Observable<Customer> {
    return this.http.get<Customer>(`${this.baseUrl}/getCustomerById/${id}`);
  }

  addCustomer(customer: Customer): Observable<Customer> {
    const { id, ...customerPayload } = customer;

    return this.http.post<Customer>(this.apiUrl, customerPayload).pipe(
      catchError((error: HttpErrorResponse) => {
        const result = {
          messageEn: 'An unexpected error occurred.',
          messageAr: 'حدث خطأ غير متوقع.'
        };

        if (error.status === 400 && typeof error.error === 'object') {
          // Validation errors from DTO
          const messages = Object.values(error.error);
          const combined = messages.join(' | ');
          result.messageEn = combined;
          result.messageAr = combined;
        } else if (error.status === 406 && error.error?.bundleMessage) {
          result.messageEn = error.error.bundleMessage.messageEn;
          result.messageAr = error.error.bundleMessage.messageAr;
        } else if (error.error?.messageEn && error.error?.messageAr) {
          result.messageEn = error.error.messageEn;
          result.messageAr = error.error.messageAr;
        }

        return throwError(() => result);
      })
    );
  }
  deleteCustomer(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/deleteCustomer/${id}`);
  }
  deleteCar(id: number) {
    return this.http.delete<void>(`${this.baseUrl}/deleteCar/${id}`);
  }
  getCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>(`${this.baseUrl}/getCustomers`);
  }
  updateCustomer(customer: Customer): Observable<any> {
    return this.http.post(`${this.baseUrl}/updateCustomer`, customer);
  }
  getCarsByCustomerId(id: number): Observable<Car[]> {
    return this.http.get<Car[]>(`${this.baseUrl}/findCarByIdOfCustomer/${id}`);
  }

  searchCustomersByName(name: string) {
    return this.http.get<Customer[]>(`http://localhost:1050/search?name=${name}`);
  }

}
