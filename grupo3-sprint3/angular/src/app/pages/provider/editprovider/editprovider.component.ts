import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Provider } from 'src/app/models/provider/provider';
import { ProviderService } from 'src/app/services/provider/provider.service';

@Component({
  selector: 'app-editprovider',
  templateUrl: './editprovider.component.html',
  styleUrls: ['./editprovider.component.css']
})
export class EditproviderComponent implements OnInit {

  providerForm!: FormGroup
  provider: Provider

  constructor(private formBuilder: FormBuilder,
    private collabService: ProviderService
  ) { }

  ngOnInit(): void {

    this.collabService.getOne(window.location.search.replace("?id=","")).subscribe(response => {
      this.provider = response
      const contact = this.formBuilder.group({
        email: this.provider.contact.email,
        telephone: this.provider.contact.telephone,
        id: this.provider.contact.id
      })
      const endereco = this.formBuilder.group({
        cep: this.provider.endereco.cep,
        id: this.provider.endereco.id
      })
      this.providerForm = this.formBuilder.group({
        contact: contact,
        endereco: endereco,
        name: this.provider.name,
        cnpj: this.provider.cnpj,
        socialReason: this.provider.socialReason,
        description: this.provider.description,
      })
    })

  }

  onSubmit() {
    this.collabService.put(this.providerForm.value, window.location.search.replace("?id=",""))
  }
}
