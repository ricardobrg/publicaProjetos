import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { WorkEntry } from 'src/app/models/work-entry/work-entry';

@Component({
  selector: 'app-listworkentry',
  templateUrl: './listworkentry.component.html',
  styleUrls: ['./listworkentry.component.css']
})
export class ListworkentryComponent implements OnInit, OnDestroy {

  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();

  entries: WorkEntry[] = [];

  constructor() { }

  ngOnInit(): void {
    this.entries = JSON.parse(sessionStorage.getItem('entries'))
    this.dtTrigger.next();
  }

  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe()
  }

}
