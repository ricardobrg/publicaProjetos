import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Provider } from 'src/app/models/provider/provider';
import { ProviderService } from 'src/app/services/provider/provider.service';

@Component({
  selector: 'app-registerprovider',
  templateUrl: './registerprovider.component.html',
  styleUrls: ['./registerprovider.component.css']
})
export class RegisterproviderComponent implements OnInit {

  providerForm!: FormGroup
  provider: Provider

  constructor(private formBuilder: FormBuilder, 
    private providerService: ProviderService,
    ) { 
      
    }

  ngOnInit(): void {
    const contact = this.formBuilder.group({
      email: '',
      telephone: ''
    })
    const endereco = this.formBuilder.group({
      cep: ''
    })
    const role = this.formBuilder.group({
      id: 2
    })

    this.providerForm = this.formBuilder.group({
      contact: contact,
      endereco: endereco,
      name: '',
      cnpj: '',
      socialReason: '',
      description: '',
    })

  }

  onSubmit() {
    this.providerService.post(this.providerForm.value)
  }
}
