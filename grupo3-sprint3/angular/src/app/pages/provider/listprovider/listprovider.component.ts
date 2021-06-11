import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { Provider } from 'src/app/models/provider/provider';
import { ProviderService } from 'src/app/services/provider/provider.service';

@Component({
  selector: 'app-listprovider',
  templateUrl: './listprovider.component.html',
  styleUrls: ['./listprovider.component.css']
})
export class ListproviderComponent implements OnInit, OnDestroy {

  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();

  entries: Provider[];

  selectedProvider: Provider;

  constructor(private providerService: ProviderService) { }

  ngOnInit(): void {
    this.providerService.get().subscribe(response => {
      this.entries = response
      console.log(response)
      this.dtTrigger.next();
    })
  }

  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }

  edit(id){
    window.location.href="http://localhost:4200/provider/edit?id="+id
  }

  delete(){
    this.providerService.delete(this.selectedProvider.id)
  }

  selectId(provider){
    this.selectedProvider = provider;
  }

}
