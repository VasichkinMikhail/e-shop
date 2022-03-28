import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ProductFilter} from "../../model/productFilter";

@Component({
  selector: 'app-product-filter',
  templateUrl: './product-filter.component.html',
  styleUrls: ['./product-filter.component.scss']
})
export class ProductFilterComponent implements OnInit {

  @Input() nameFilter?: ProductFilter;

  @Output() nameFilterApplied = new EventEmitter<ProductFilter>();

  constructor() { }

  applyNameFilter() {
    this.nameFilterApplied.emit(this.nameFilter);
  }

  ngOnInit(): void {
  }

}
