import { Component, OnInit } from '@angular/core';
import { WorkEntryService } from '../../services/work-entry/work-entry.service';
import { FormGroup, FormBuilder } from '@angular/forms';
import { VacationService } from 'src/app/services/vacation/vacation.service';
import { VacationData } from '../../models/vacation/vacation-data';
import { VerificarPermissoes } from 'src/app/services/auth/verificarPermissoes';

@Component({
  selector: 'app-menuhome',
  templateUrl: './menuhome.component.html',
  styleUrls: ['./menuhome.component.css']
})
export class MenuhomeComponent implements OnInit {

  payrollForm: FormGroup;

  entry: string;
  pointSearchForm: FormGroup

  vacationData: VacationData
  vacationCheckForm: FormGroup
  vacationMessage: string
  vacationConfirmForm: FormGroup

  constructor(
    private formBuilder: FormBuilder,
    private workEntryService: WorkEntryService,
    private vacationService: VacationService
  ) { }

  ngOnInit(): void {
    this.payrollForm = this.formBuilder.group({
      cpf: ''
    })

    this.pointSearchForm = this.formBuilder.group({
      cpf: '',
      initDate: '',
      finalDate: ''
    })

    this.vacationCheckForm = this.formBuilder.group({
      cpf: '',
      date: ''
    })

    this.vacationConfirmForm = this.formBuilder.group({
      cpf: '',
      exitDate: ''
    })

  }

  searchPayrolls(): void {
    window.sessionStorage.setItem('payroll-cpf', this.payrollForm.get('cpf').value)
    window.location.href = "http://localhost:4200/payroll/list"
  }

  makeEntry() {
    let cpf = {'cpf': sessionStorage.getItem('cpf')}
    this.workEntryService.post(cpf).subscribe(res => this.entry = res['success'].split('T')[1]);
  }

  dismissEntry() {
    this.entry = null;
  }

  searchPoints(): void {
    this.workEntryService.search(this.pointSearchForm.value).subscribe(res => {
      let array = []
      res['entries'].forEach(entry => {
        let ent = entry.split('T')
        array.push({
          'date': ent[0],
          'time': ent[1]
        })
      })
      window.sessionStorage.setItem('entries', JSON.stringify(array))
      window.location.href = "http://localhost:4200/work-entries/list"
    });
  }

  vacationCheck(): void {
    this.vacationService.check(this.vacationCheckForm.value).subscribe(res => {
      this.vacationData = {
        'name': res['name'],
        'cpf': res['cpf'],
        'vacationDays': res['vacationDays'],
        'vacationPayment': res['vacationPayment'],
        'canVacation': res['canVacation'] == 1 ? 'Sim' : 'Não'
      }
    })
  }

  confirmVacation(): void {
    this.vacationService.confirm(this.vacationConfirmForm.value).subscribe(res => {
    
        this.vacationMessage = `Férias Confirmadas. Data máxima de retorno: ${res['returnDate']}`
    })
  }

  public verificarRole(rolesFuncionalidade: number): boolean{
    const rolesUsuario =JSON.parse(sessionStorage.getItem('accessLevel'))
    return VerificarPermissoes.temPermissao(rolesFuncionalidade, rolesUsuario)
  }

}
