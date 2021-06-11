import { Component, OnDestroy, OnInit } from '@angular/core';
import { ProviderService } from '../sprovider.service';
import { ResponseProviders } from '../sprovider.model';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-sprovider-list',
  templateUrl: './sprovider-list.component.html',
  styleUrls: ['./sprovider-list.component.css']
})
export class SproviderListComponent implements OnInit, OnDestroy {

  responseProviders: ResponseProviders[]; //qqr coisa- mudar para any

  /**
   * Adding an event emmiter for rendering the
   * DataTable everytime a a request is made
   */
  dtTrigger: Subject<any> = new Subject<any>();

  constructor(private providersService: ProviderService) { }

  ngOnInit(): void {
    if (!localStorage.token) {
      window.location.href= "/login";
    }

    this.dtTrigger.next();
    this.providersService.getProviders()
      .subscribe((res) => {
        console.log(res);
        this.responseProviders = res;

        /**
        * The .next() method is used for rendering the table 
        * right after getting all collaborators from database
        */
        this.dtTrigger.next();
      });
  }

  delete(id:number) {
    if (confirm("Tem certeza que deseja excluir o elemento?")) {
      this.providersService.deleteProvider(id).subscribe((response) => {
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