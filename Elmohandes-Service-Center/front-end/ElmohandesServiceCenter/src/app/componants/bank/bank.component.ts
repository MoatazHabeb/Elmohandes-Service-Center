import { Component, OnInit } from '@angular/core';
import {BankService} from "../../../service/bank.service";
import {Bank} from "../../../model/bank";
import {Router} from "@angular/router";

@Component({
  selector: 'app-bank',
  templateUrl: './bank.component.html',
  styleUrls: ['./bank.component.css']
})
export class BankComponent implements OnInit {
  banks: Bank[] = [];

  constructor(private bankService: BankService, private router: Router) {}

  ngOnInit(): void {
    this.bankService.getBanks().subscribe(data => {
      this.banks = data;
    });
  }

  deleteBank(id: number): void {
    this.bankService.deleteField(id).subscribe(() => {
      this.banks = this.banks.filter(bank => bank.id !== id);
    });
  }

  goToAdd(): void {
    this.router.navigate(['/bank-actions'], { queryParams: { mode: 'add' } });
  }

  goToUpdate(id: number): void {
    this.router.navigate(['/bank-actions'], { queryParams: { mode: 'update', id } });
  }
}
