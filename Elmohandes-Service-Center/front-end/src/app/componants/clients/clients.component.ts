import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import { forkJoin } from 'rxjs';
import { CustomerService } from "../../../service/customer.service";
import { Customer } from "../../../model/customer";
import { Car } from "../../../model/car";
import { map } from "rxjs/operators";
import {ActivatedRoute, Router} from "@angular/router";

import {CustomerRefreshService} from "../../../service/customer-refresh.service";
import {CarService} from "../../../service/car.service";

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css']
})
export class ClientsComponent implements OnInit {
  customersWithCars: { customer: Customer, cars: Car[] }[] = [];
  noResultsMessage: string = '';
  constructor(private carService: CarService,private clientService: CustomerService ,private cdr: ChangeDetectorRef,private activatedRoute: ActivatedRoute , private router: Router,private cu: CustomerRefreshService) { }

  ngOnInit(): void {

    this.activatedRoute.paramMap.subscribe(() => this.loadCustomersWithCars());

    this.cu.refresh$.subscribe(() => {
      this.loadCustomersWithCars();
    });
  }

  searchType: string = 'name';
  searchQuery: string = '';
  isSearching: boolean = false;

  searchCustomers(): void {
    if (!this.searchQuery.trim()) {
      this.loadCustomersWithCars(); // fallback
      return;
    }

    this.isSearching = true;

    if (this.searchType === 'name') {
      this.clientService.searchCustomersByName(this.searchQuery.trim()).subscribe(customers => {
        if (!customers.length) {
          this.customersWithCars = [];
          this.noResultsMessage = 'No customers match your search.';
          this.isSearching = false;
          return;
        }

        const requests = customers.map(customer =>
          this.clientService.getCarsByCustomerId(customer.id).pipe(
            map(cars => ({ customer, cars }))
          )
        );

        forkJoin(requests).subscribe({
          next: result => {
            this.customersWithCars = result;
            this.noResultsMessage = '';
            this.isSearching = false;
            this.cdr.detectChanges();
          },
          error: err => {
            console.error('Error during search:', err);
            this.noResultsMessage = 'Search failed due to an error.';
            this.isSearching = false;
          }
        });
      });
    }

    // 🔽 NEW: Search by chassis number
    else if (this.searchType === 'chassis') {
      this.carService.getCustomerByCar(this.searchQuery.trim()).subscribe({
        next: (customers) => {
          if (!customers || customers.length === 0) {
            this.customersWithCars = [];
            this.noResultsMessage = 'No customer found for this chassis number.';
            this.isSearching = false;
            return;
          }

          const requests = customers.map(customer =>
            this.clientService.getCarsByCustomerId(customer.id).pipe(
              map(cars => ({ customer, cars }))
            )
          );

          forkJoin(requests).subscribe({
            next: result => {
              this.customersWithCars = result;
              this.noResultsMessage = '';
              this.isSearching = false;
              this.cdr.detectChanges();
            },
            error: (err) => {
              console.error('Error fetching cars for customers:', err);
              this.customersWithCars = [];
              this.noResultsMessage = 'Failed to get cars for the customers.';
              this.isSearching = false;
            }
          });
        },
        error: (err) => {
          console.error('Chassis search error:', err);
          this.customersWithCars = [];
          this.noResultsMessage = 'Search failed due to a server error.';
          this.isSearching = false;
        }
      });
    }
  }



  loadCustomersWithCars(): void {
    this.clientService.getCustomers().subscribe(customers => {
      if (!customers.length) {
        this.customersWithCars = [];
        this.noResultsMessage = 'No customers found.';
        return;
      }

      const requests = customers.map(customer =>
        this.clientService.getCarsByCustomerId(customer.id).pipe(
          map(cars => ({ customer, cars }))
        )
      );

      forkJoin(requests).subscribe({
        next: result => {
          this.customersWithCars = result;
          this.noResultsMessage = '';
          this.cdr.detectChanges();
        },
        error: err => {
          console.error('Error loading customers with cars:', err);
          this.noResultsMessage = 'Error loading data.';
        }
      });
    });
  }


  deleteCustomer(id: number): void {
    if (confirm('Are you sure you want to delete this customer?')) {
      this.clientService.deleteCustomer(id).subscribe(() => {


            this.cu.triggerRefresh()




      });
    }
  }
  deleteCar(carId: number) {
    if (confirm('Are you sure you want to delete this car?')) {
      this.clientService.deleteCar(carId).subscribe(() => {


        this.cu.triggerRefresh()




      });
    }
  }



  updateCustomer(id: number) {this.router.navigate(['/clientsactions/updatecustomer', id]); }


  updateCar(carId: number,customerId: number) { this.router.navigate(['/clientsactions/updatecar', carId]); }

  viewBills(customerId: number) {this.router.navigate(['/bills', customerId]); }
}
