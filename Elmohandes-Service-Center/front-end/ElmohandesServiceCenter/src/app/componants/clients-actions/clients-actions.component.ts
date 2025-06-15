// clients-actions.component.ts
import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { CustomerService } from "../../../service/customer.service";
import { CarService } from "../../../service/car.service";
import { Customer } from "../../../model/customer";
import { Car } from "../../../model/car";

@Component({
  selector: 'app-clients-actions',
  templateUrl: './clients-actions.component.html',
  styleUrls: ['./clients-actions.component.css']
})
export class ClientsActionsComponent implements OnInit {
  isAddClientMode = false;
  isAddCarMode = false;
  isUpdateCustomerMode = false;
  isUpdateCarMode = false;
  messageAr = '';
  messageEn = '';
  customer: Customer = {
    id: undefined,
    name: '',
    phoneNumber: ''
  };
  car: Car = {
    chassisNumber: '',
    boardNumber: '',
    model: '',
    engineNumber: '',
    customer_id: 0
  };
  serverMessage: string | null = null;
  messageType: 'success' | 'error' | null = null;



  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private customerService: CustomerService,
    private carService: CarService
  ) {}

  ngOnInit(): void {
    this.checkRoute();

    if (this.isUpdateCustomerMode) {
      this.loadCustomerForUpdate();
    } else if (this.isUpdateCarMode) {
      this.loadCarForUpdate();
    } else if (this.isAddCarMode) {
      this.getCustomerIdFromRoute();
    }
  }

  private checkRoute(): void {
    const key = this.activatedRoute.snapshot.paramMap.get('key');
    const customerId = this.activatedRoute.snapshot.paramMap.get('customerId');
    const updateId = this.activatedRoute.snapshot.paramMap.get('id');
    const path = this.activatedRoute.snapshot.routeConfig?.path;

    this.isAddClientMode = key === 'addclient';
    this.isAddCarMode = !!customerId;
    this.isUpdateCustomerMode = path?.includes('updatecustomer');
    this.isUpdateCarMode = path?.includes('updatecar');

    // Get customer ID from state if available
    if (history.state?.customerId) {
      this.car.customer_id = history.state.customerId;
    }
  }

  private loadCarForUpdate(): void {
    const carId = this.activatedRoute.snapshot.paramMap.get('id');

    if (carId && !isNaN(+carId)) {
      this.carService.getCarById(+carId).subscribe({
        next: (car) => {
          if (car) {
            this.car = car;
            console.log('Car loaded:', this.car);
          } else {
            this.showError('Car not found', 'المركبة غير موجودة');
          }
        },
        error: (err) => {
          console.error('Error loading car:', err);
          this.showError('Failed to load car data', 'فشل تحميل بيانات المركبة');
        }
      });
    } else {
      this.showError('Invalid car ID', 'معرف المركبة غير صالح');
    }
  }

  onSubmit(): void {
    if (this.isAddClientMode) {
      this.addCustomer();
    } else if (this.isAddCarMode) {
      this.addCar();
    } else if (this.isUpdateCustomerMode) {
      this.updateCustomer();
    } else if (this.isUpdateCarMode) {
      this.updateCar();
    }
  }

  private updateCar(): void {
    console.log('Updating car:', this.car);

    if (!this.car.id) {
      this.showError('Car ID is missing', 'معرف المركبة مفقود');
      return;
    }

    if (!this.car.customer_id) {
      this.showError('Customer ID is missing', 'معرف العميل مفقود');
      return;
    }

    this.carService.updateCar(this.car).subscribe({
      next: (response) => {
        console.log('Update successful:', response);
        this.showSuccess('Car updated successfully', 'تم تحديث المركبة بنجاح');
      },
      error: (error) => {
        console.error('Update failed:', error);
        this.showError(
          error?.messageEn || 'Failed to update car',
          error?.messageAr || 'فشل تحديث بيانات المركبة'
        );
      }
    });
  }

  // Navigation method to be called from the car list
  updateCarNavigation(carId: number, customerId: number) {
    this.router.navigate(['/clientsactions/updatecar', carId], {
      state: { customerId: customerId }
    });
  }
  private loadCustomerForUpdate(): void {
    const customerId = this.activatedRoute.snapshot.paramMap.get('id');

    if (customerId && !isNaN(+customerId)) {
      this.customerService.getCustomerById(+customerId).subscribe({
        next: (customer) => {
          if (customer) {
            this.customer = customer;
          } else {
            this.showError('Customer not found', 'العميل غير موجود');
          }
        },
        error: (err) => {
          console.error('Error loading customer:', err);
          this.showError('Failed to load customer data', 'فشل تحميل بيانات العميل');
        }
      });
    } else {
      this.showError('Invalid customer ID', 'معرف العميل غير صالح');
    }
  }





  private getCustomerIdFromRoute(): void {
    if (this.isAddCarMode) {
      const customerId = this.activatedRoute.snapshot.paramMap.get('customerId');
      if (customerId) {
        this.car.customer_id = +customerId;
      }
    }
  }

  private loadCustomerIfUpdateMode(): void {
    if (this.isUpdateCustomerMode) {
      const customerId = this.activatedRoute.snapshot.paramMap.get('id');
      if (customerId) {
        this.customerService.getCustomerById(+customerId).subscribe({
          next: (customer) => {
            this.customer = customer;
          },
          error: (error) => {
            this.messageType = 'error';
            this.messageEn = 'Failed to load customer data';
            this.messageAr = 'فشل تحميل بيانات العميل';
          }
        });
      }
    }
  }



  private addCustomer(): void {
    const phoneNumberPattern = /^\d+$/;
    if (!phoneNumberPattern.test(this.customer.phoneNumber)) {
      this.messageAr = 'يجب أن يحتوي رقم الهاتف على أرقام فقط بدون رموز أو حروف.';
      this.messageEn = 'Phone number must contain digits only without symbols or letters.';
      this.messageType = 'error';
      this.serverMessage = null;
      setTimeout(() => this.clearMessages(), 4000);
      return;
    }

    this.customerService.addCustomer(this.customer).subscribe({
      next: (response: any) => {
        if (response?.status === 'NOT_ACCEPTABLE' && response?.bundleMessage) {
          this.messageAr = response.bundleMessage.message_ar || '';
          this.messageEn = response.bundleMessage.message_en || '';
          this.serverMessage = null;
          this.messageType = 'error';
        } else {
          this.serverMessage = 'Customer added successfully.';
          this.messageType = 'success';
          this.messageAr = '';
          this.messageEn = '';
          this.customer = { id: undefined, name: '', phoneNumber: '' };
        }
        setTimeout(() => this.clearMessages(), 4000);
      },
      error: (error) => {
        this.serverMessage = null;
        this.messageType = 'error';
        this.messageAr = error?.messageAr || 'حدث خطأ غير متوقع.';
        this.messageEn = error?.messageEn || 'An unexpected error occurred.';
        setTimeout(() => this.clearMessages(), 4000);
      }
    });
  }
  private debugCustomerData(): void {
    console.log('Current customer data:', {
      id: this.customer.id,
      name: this.customer.name,
      phoneNumber: this.customer.phoneNumber
    });
  }
  private updateCustomer(): void {
    // Debug before sending
    this.debugCustomerData();

    // Validate phone number
    const phoneNumberPattern = /^\d+$/;
    if (!phoneNumberPattern.test(this.customer.phoneNumber)) {
      this.showError('Phone number must contain digits only', 'يجب أن يحتوي رقم الهاتف على أرقام فقط');
      return;
    }

    // Ensure customer ID exists
    if (!this.customer.id) {
      this.showError('Customer ID is missing', 'معرف العميل مفقود');
      return;
    }

    this.customerService.updateCustomer(this.customer).subscribe({
      next: (response) => {
        console.log('Update successful:', response);
        this.showSuccess('Customer updated successfully', 'تم تحديث العميل بنجاح');

      },
      error: (error) => {
        console.error('Update failed:', error);
        this.showError(
          error?.messageEn || 'Failed to update customer',
          error?.messageAr || 'فشل تحديث بيانات العميل'
        );
      }
    });
  }

  private showSuccess(enMsg: string, arMsg: string): void {
    this.serverMessage = enMsg;
    this.messageEn = enMsg;
    this.messageAr = arMsg;
    this.messageType = 'success';
    setTimeout(() => this.clearMessages(), 4000);
  }

  private showError(enMsg: string, arMsg: string): void {
    this.serverMessage = enMsg;
    this.messageEn = enMsg;
    this.messageAr = arMsg;
    this.messageType = 'error';
    setTimeout(() => this.clearMessages(), 4000);
  }

  private addCar(): void {
    this.carService.addCar(this.car).subscribe({
      next: (response: any) => {
        this.serverMessage = 'Vehicle added successfully.';
        this.messageType = 'success';
        this.messageAr = '';
        this.messageEn = '';
        this.resetCarForm();
        setTimeout(() => this.clearMessages(), 4000);
      },
      error: (error) => {
        this.serverMessage = null;
        this.messageType = 'error';
        this.messageAr = error?.messageAr || 'حدث خطأ أثناء إضافة المركبة.';
        this.messageEn = error?.messageEn || 'An error occurred while adding the vehicle.';
        setTimeout(() => this.clearMessages(), 4000);
      }
    });
  }

  private resetCarForm(): void {
    this.car = {
      chassisNumber: '',
      boardNumber: '',
      model: '',
      engineNumber: '',
      customer_id: this.car.customer_id
    };
  }

  private clearMessages(): void {
    this.serverMessage = null;
    this.messageAr = '';
    this.messageEn = '';
    this.messageType = null;
  }
}
