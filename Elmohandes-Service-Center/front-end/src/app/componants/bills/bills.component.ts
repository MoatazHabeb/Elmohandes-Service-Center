import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BillService } from "../../../service/bill.service";
import { FinalBillVm } from "../../../model/final-bill-vm";
import {CustomerRefreshService} from "../../../service/customer-refresh.service";

@Component({
  selector: 'app-bills',
  templateUrl: './bills.component.html',
  styleUrls: ['./bills.component.css']
})
export class BillsComponent implements OnInit {
  bills: FinalBillVm[] = [];

  constructor(
    private billService: BillService,
    private route: ActivatedRoute
    ,private cu: CustomerRefreshService
  ) {}

  ngOnInit(): void {
    const customerIdParam = this.route.snapshot.paramMap.get('customerId');

    if (customerIdParam) {
      const customerId = +customerIdParam;
      this.billService.getBillsByCustomerId(customerId).subscribe((data) => {
        this.bills = data;
      });
    } else {
      this.billService.getAllBills().subscribe((data) => {
        this.bills = data;
      });
    }
  }

  deleteBill(billId: number): void {
    if (confirm('هل أنت متأكد من حذف هذه الفاتورة؟')) {
      this.billService.deleteBill(billId).subscribe(() => {
        this.bills = this.bills.filter(bill => bill.billDto.id !== billId);
        this.cu.triggerRefresh();
      });
    }
  }
  calculateTotal(bill: FinalBillVm): number {
    return bill.billFieldsDtos.reduce((total, field) => {
      return total + (field.quantity * field.price);
    }, 0);
  }
}
