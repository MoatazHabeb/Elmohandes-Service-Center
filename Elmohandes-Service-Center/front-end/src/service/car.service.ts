import { Injectable } from '@angular/core';
import {environment} from "../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Car} from "../model/car";
import {Observable} from "rxjs";
import {Customer} from "../model/customer";

@Injectable({
  providedIn: 'root'
})
export class CarService {

  private apiUrl = 'http://localhost:1050';

  constructor(private http: HttpClient) {}

  addCar(car: Car): Observable<any> {
    return this.http.post(`${this.apiUrl}/addCar`, car);
  }
  getCarById(id: number): Observable<Car> {
    return this.http.get<Car>(`${this.apiUrl}/getCarById/${id}`);
  }

  updateCar(car: Car): Observable<any> {
    return this.http.post(`${this.apiUrl}/updateCar`, {
      id: car.id,
      chassisNumber: car.chassisNumber,
      boardNumber: car.boardNumber,
      model: car.model,
      engineNumber: car.engineNumber,
      customer_id: car.customer_id
    });
  }

  searchCarsByChassisNumber(chassisNumber: string): Observable<Car[]> {
    return this.http.get<Car[]>(`http://localhost:1050/searchCar?chassisNumber=${chassisNumber}`);
  }

  getCustomerByCar(chassisNumber: string): Observable<Customer[]> {
    return this.http.get<Customer[]>(`http://localhost:1050/getCustomerByCar`, {
      params: { chassisNumber }
    });
  }
}
