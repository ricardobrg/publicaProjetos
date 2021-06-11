import { Component, OnInit, ViewChild } from '@angular/core';
import { DataTableDirective } from 'angular-datatables';
import { Subject } from 'rxjs';
import { Payroll } from '../../../models/payroll/payroll';
import { PayrollService } from '../../../services/payroll/payroll.service';

@Component({
  selector: 'app-listpayroll',
  templateUrl: './listpayroll.component.html',
  styleUrls: ['./listpayroll.component.css']
})
export class ListpayrollComponent implements OnInit {

  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();
  entries: Payroll[] = [];
  
  @ViewChild(DataTableDirective)
  dtElement: DataTableDirective;
  dtOptionsDiscounts: DataTables.Settings = {};
  dtTriggerDiscounts: Subject<any> = new Subject();
  discounts = []

  viewDiscounts(id): void {
    this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
      dtInstance.destroy;

      this.discounts = [];
      let selectedEntry: Payroll;
      selectedEntry = this.entries.find(entry => entry.id == id);
      selectedEntry.discounts.map(el => {
        this.discounts.push({
          'name': el.name,
          'value': el.value,
          'type': el.type == 1 ? 'Percentual' : 'Fixo'
        });
      });

      this.dtTriggerDiscounts.next();
    })

  }

  constructor(private payrollService: PayrollService) { }

  ngOnInit(): void {
    let cpf = sessionStorage.getItem('payroll-cpf')
    this.payrollService.search(cpf).subscribe(res => {
      this.entries = res;
      this.dtTrigger.next();
    })

  }

  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
    this.dtTriggerDiscounts.unsubscribe();
  }

}
