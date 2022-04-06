import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {Page} from "../../model/page";

@Component({
  selector: 'app-product-pagination',
  templateUrl: './product-pagination.component.html',
  styleUrls: ['./product-pagination.component.scss']
})
export class ProductPaginationComponent implements OnInit, OnChanges {

  @Input() page?: Page;

  numbers: number[] = [];

  pageNumber: number = 1;

  @Output() goToPageEvent = new EventEmitter<number>();

  constructor() {
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (this.page != null) {
      this.numbers = Array.from(Array(this.page.totalPages).keys());
      this.pageNumber = this.page.number + 1;
    }
  }

  ngOnInit(): void {
  }

  goToPage(page: number) {
    this.goToPageEvent.emit(page);
  }

}
