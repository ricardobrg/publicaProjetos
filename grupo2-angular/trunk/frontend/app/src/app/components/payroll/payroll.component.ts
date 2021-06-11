import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { PayrollService } from './payroll.service';
import { ResponsePayrolls } from './payroll.model';

@Component({
  selector: 'app-payroll',
  templateUrl: './payroll.component.html',
  styleUrls: ['./payroll.component.css']
})
export class PayrollComponent implements OnInit, OnDestroy {

  responsePayrolls: ResponsePayrolls[];

  dtTrigger: Subject<any> = new Subject<any>();

  constructor(private PayrollService : PayrollService) { }
  
  ngOnInit(): void {
    this.dtTrigger.next();
    this.PayrollService.getPayrolls()
      .subscribe((res) => {
        this.responsePayrolls = res
        
        /**
         * The .next() method is used for rendering the table 
         * right after getting all collaborators from database
         */
        this.dtTrigger.next();
      });
    }
  
  delete(id:number) {
    if (confirm("Tem certeza que deseja excluir o elemento?")) {
      this.PayrollService.deletePayroll(id).subscribe((response) => {
        window.location.reload();
      });
    }
  }

  /**
   * This method removes the event emmiter 
   * from datatables when the page is destroyed
   */
  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }
}