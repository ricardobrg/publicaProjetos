import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ProviderService } from '../sprovider.service';

@Component({
  selector: 'app-sprovider-edit',
  templateUrl: './sprovider-edit.component.html',
  styleUrls: ['./sprovider-edit.component.css']
})
export class SproviderEditComponent implements OnInit {

  id: any;

  /**
   * Define fields that will be used for adding on edit Service Provider
   */
  sproviderForm = new FormGroup({
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

    description: new FormControl(),
    price: new FormControl(),
  });
  
  constructor(private providersService: ProviderService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    this.providersService.getProvider(this.id).subscribe((response) =>{
      this.sproviderForm.patchValue({
        contact: {
          email: response.contact.email,
          phone: response.contact.phone,
        },

        description: response.description,
        price: response.price,

        address: {
          cep: response.address.cep,
          uf: response.address.uf,
          locality: response.address.locality,
          district: response.address.district,
          street: response.address.street,
          complement: response.address.complement,
        },
      });
    });
  }

  saveEditForm() {
    this.providersService.updateProvider(this.id, this.sproviderForm.value).subscribe(res => {
      window.location.href = "/sproviders-list";
    });
  }
}
