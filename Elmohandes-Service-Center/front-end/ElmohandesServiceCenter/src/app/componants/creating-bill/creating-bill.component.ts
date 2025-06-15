import { Component, OnInit } from '@angular/core';
import { Customer } from "../../../model/customer";
import { Car } from "../../../model/car";
import { CustomerService } from "../../../service/customer.service";
import { BillService } from "../../../service/bill.service";
import { BankService } from "../../../service/bank.service";
import { Router } from "@angular/router";
import { BillFields } from "../../../model/bill-fields";
import { Bill } from "../../../model/bill";
import { AddBillRequest } from "../../../model/add-bill-request";
import { Bank } from "../../../model/bank";
import {CustomerRefreshService} from "../../../service/customer-refresh.service";

@Component({
  selector: 'app-creating-bill',
  templateUrl: './creating-bill.component.html',
  styleUrls: ['./creating-bill.component.css']
})
export class CreatingBillComponent implements OnInit {
  messageAr: String = '';
  // tslint:disable-next-line:ban-types
  messageEn: String = '';
  managers: Customer[] = [];
  cars: Car[] = [];
  banks: Bank[] = [];
  billFields: BillFields[] = [];

  useBankItem: boolean = true;

  bill: Bill = {
    dateOfNow: new Date().toISOString().substring(0, 10),
    receptionDate: '',
    deliveryDate: '',
    customerRepresentative: '',
    lastServiceDate: '',
    factory: 0,
    spareParts: 0,
    externalWorks: 0,
    taxes: 0,
    factoryDiscount: 0,
    sparePartsDiscount: 0,
    specialDiscount: 0,
    netBill: 0,
    kilometers: '',
    customer_id: 0,
    car_id: 0
  };

  newField: BillFields = { field: '', quantity: 1, price: 0, bill_id: 0 };

  constructor(
    private customerService: CustomerService,
    private billService: BillService,
    private bankService: BankService,
    private router: Router
    ,private cu: CustomerRefreshService
  ) {}

  ngOnInit(): void {
    this.customerService.getCustomers().subscribe({
      next: (data) => this.managers = data,
      error: (err) => console.error('Failed to fetch customers', err)
    });

    this.bankService.getBanks().subscribe({
      next: (data) => this.banks = data,
      error: (err) => console.error('Failed to fetch banks', err)
    });
  }

  onManagerSelected(event: any): void {
    const selectedCustomerId = +event.target.value;
    this.bill.customer_id = selectedCustomerId;

    this.customerService.getCarsByCustomerId(selectedCustomerId).subscribe({
      next: (cars) => this.cars = cars,
      error: (err) => console.error('Failed to fetch cars', err)
    });
  }

  onCarSelected(event: any): void {
    const selectedCarId = +event.target.value;
    this.bill.car_id = selectedCarId;
  }

  onBankSelected(event: any): void {
    const bankId = +event.target.value;
    const selectedBank = this.banks.find(bank => bank.id === bankId);
    if (selectedBank) {
      this.newField.field = selectedBank.name;
      this.newField.price = selectedBank.price;
    }
  }

  addBillField(): void {
    if (!this.newField.field || this.newField.quantity < 1 || this.newField.price < 0) {
      alert("Please enter a valid field. Quantity must be at least 1 and price must be 0 or more.");
      return;
    }

    this.billFields.push({ ...this.newField });
    this.newField = { field: '', quantity: 1, price: 0, bill_id: 0 };  // default quantity = 1
  }

  removeField(index: number): void {
    this.billFields.splice(index, 1);
  }
  private extracted() {
    setTimeout(() => {
      this.messageAr = '';
      this.messageEn = '';
    }, 3000);
  }
  submitBill(): void {
    const request: AddBillRequest = {
      billDto: this.bill,
      billFieldsDtos: this.billFields
    };
    this.messageAr = '';
    this.messageEn = '';
    this.billService.addBill(request).subscribe({
      next: (response) => {
        // @ts-ignore
        if (response && response.status === 'NOT_ACCEPTABLE') {
          // @ts-ignore
          this.messageAr = response.bundleMessage?.message_ar || 'حدث خطأ';
          // @ts-ignore
          this.messageEn = response.bundleMessage?.message_en || 'An error occurred';
          this.extracted(); // Call your custom handler
        } else {
          alert('Bill created successfully');
          this.router.navigate(['/bills']);
        }
      },
      error: (err) => {
        if (err.status === 400 && typeof err.error === 'string') {
          alert(`Error: ${err.error}`); // e.g., "Not enough quantity for Oil"
        } else {
          alert('An unexpected error occurred. Please try again.');
        }
        console.error('Bill creation failed', err);
      }
    });
  }


}
