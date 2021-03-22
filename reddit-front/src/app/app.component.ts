import { Component, Inject, OnInit } from '@angular/core';
import { NzIconService } from 'ng-zorro-antd/icon';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
    constructor(
        private readonly nzIconService: NzIconService,
    ) {}

    ngOnInit(): void {
        this.nzIconService.changeAssetsSource('assets/@ant-design');
    }
}
