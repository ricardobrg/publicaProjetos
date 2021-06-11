import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

import { RoleService } from '../../role/role.service';
import { CollaboratorService } from '../collaborator.service';


@Component({
  selector: 'app-collab-add',
  templateUrl: './collab-add.component.html',
  styleUrls: ['./collab-add.component.css']
})
export class CollabAddComponent implements OnInit {

  /**
   * Roles that will be used for fiiling select
   */
  roles: any[];

  /**
   * Define fields that will be used for adding collaborator
   */
  personalForm = new FormGroup({
    
    name: new FormControl(),
    cpf: new FormControl(),
    admissionDate: new FormControl(),
    role: new FormControl(0),
    password: new FormControl(),
    pis: new FormControl(), 

    contact: new FormGroup({
      email : new FormControl(),
      phone : new FormControl(),
    }),

    address: new FormGroup({
      cep: new FormControl(),
      uf: new FormControl(),
      city: new FormControl(),
      district: new FormControl(),
      street: new FormControl(),
      number: new FormControl(),
      complement: new FormControl(),
    }),

    
  });
  
  constructor(
    private http : HttpClient,
    private roleService: RoleService,
    private collabService: CollaboratorService
  ) { }

  ngOnInit(): void {
    if (!localStorage.token) {
      window.location.href = '/';
    }
    this.roleService.getRoles().subscribe((data) => {
      this.roles = data;
    });
  }

  /**
   * This method is used for parsing values that
   * comes from frame with wrong types e.g. "role"
   * 
   * In here we define which fields should be parsed
   * and how should it be setted;
   * 
   * @param data The object that came from screen
   */
  formatData(data:any) :void {
    data.role===0 ? data.role = null : data.role={id:parseInt(data.role)};

    if (data.admissionDate) {
      let splittedDate = data.admissionDate.split('-');
      data.admissionDate = `${splittedDate[2]}/${splittedDate[1]}/${splittedDate[0]}`
    }
  }

  saveForm() {
    this.formatData(this.personalForm.value);
    this.collabService.insertCollab(this.personalForm.value).subscribe(data=>{
      console.log(data);
    });
  }

  findAddresWithCep() {
    let cep = this.personalForm.controls.address.value.cep;
    this.http.get(`https://viacep.com.br/ws/${cep}/json/`).subscribe((res:any) => {
      console.log(res);
      let form = this.personalForm.controls.address.setValue({
        cep: cep,
        city: res.localidade,
        complement: null,
        district: res.bairro,
        number: null,
        street: res.logradouro,
        uf: res.uf
      });
    });

  }
}
