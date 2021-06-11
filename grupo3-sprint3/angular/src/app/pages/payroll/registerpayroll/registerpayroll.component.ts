import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormArray } from '@angular/forms';
import { Payroll } from 'src/app/models/payroll/payroll';
import { PayrollService } from 'src/app/services/payroll/payroll.service';

@Component({
  selector: 'app-registerpayroll',
  templateUrl: './registerpayroll.component.html',
  styleUrls: ['./registerpayroll.component.css']
})

export class RegisterpayrollComponent implements OnInit {

  payrollForm!: FormGroup;

  constructor(private formBuilder: FormBuilder, private payrollService: PayrollService) { }

  payroll: Payroll

  ngOnInit(): void {
    const collaborator = this.formBuilder.group({
      cpf: ''
    })

    this.payrollForm = this.formBuilder.group({
      collaborator: collaborator,
      initDate: '',
      finalDate: '',
      extra: '',
      discounts: this.formBuilder.array([])
    })
    
  }

  get discounts(): FormArray {
    return this.payrollForm?.get('discounts') as FormArray;
  }

  addDiscount(): void {
    const discount = this.formBuilder.group({
      name: '',
      value: '',
      type: ''
    })

    this.discounts.push(discount);
  }

  onSubmit() {
    //console.log(this.payrollForm.value)
    this.payrollService.post(this.payrollForm.value)
  }

  removeDiscount(i: number): void {
    this.discounts.removeAt(i);
  }

}
