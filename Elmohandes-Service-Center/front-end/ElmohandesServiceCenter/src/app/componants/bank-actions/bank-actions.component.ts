import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BankService } from '../../../service/bank.service';
import { Bank } from '../../../model/bank';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-bank-actions',
  templateUrl: './bank-actions.component.html',
  styleUrls: ['./bank-actions.component.css']
})
export class BankActionsComponent implements OnInit {
  isAddMode = true;
  bankForm!: FormGroup;
  id!: number;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder,
    private bankService: BankService
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.isAddMode = params['mode'] === 'add';
      this.id = +params['id'] || 0;

      this.bankForm = this.fb.group({
        name: ['', Validators.required],
        quantity: [0, Validators.required],
        price: [0, Validators.required]
      });

      if (!this.isAddMode && this.id) {
        this.bankService.getBankById(this.id).subscribe(bank => {
          this.bankForm.patchValue(bank);
        });
      }
    });
  }

  onSubmit(): void {
    if (this.bankForm.invalid) return;

    if (this.isAddMode) {
      const newBank: Bank = this.bankForm.value; // No id included
      this.bankService.addField(newBank).subscribe(() => {
        this.router.navigate(['/bank']);
      });
    } else {
      const updatedBank: Bank = { id: this.id, ...this.bankForm.value };
      this.bankService.updateField(updatedBank).subscribe(() => {
        this.router.navigate(['/bank']);
      });
    }
  }

}
