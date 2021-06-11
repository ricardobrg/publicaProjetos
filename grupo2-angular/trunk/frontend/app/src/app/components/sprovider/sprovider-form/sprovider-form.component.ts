import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ProviderService } from '../sprovider.service';

@Component({
  selector: 'app-sprovider-form',
  templateUrl: './sprovider-form.component.html',
  styleUrls: ['./sprovider-form.component.css']
})
export class SproviderFormComponent implements OnInit {

  id: any;

  /**
   * Define fields that will be used for adding on edit Service Provider
   */
  sproviderForm = new FormGroup({
    name: new FormControl(),

    contact: new FormGroup({
      email: new FormControl(),
      phone: new FormControl(),
    }),

    address: new FormGroup({
      cep: new FormControl(),
      uf: new FormControl(),
      locality: new FormControl(),
      district: new FormControl(),
      street: new FormControl(),
      number: new FormControl(),
      complement: new FormControl(),
    }),

    cnpj: new FormControl(),
    socialReason: new FormControl(),
    description: new FormControl(),
    price: new FormControl(),
  });
  
  constructor(private providersService: ProviderService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    
  }

  saveData() {
    this.providersService.saveProvider(this.sproviderForm.value).subscribe(res => {
      console.log(res);
      window.location.href = "/sproviders-list";
    });
  }
}
