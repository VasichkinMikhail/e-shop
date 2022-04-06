import { Component, OnInit } from '@angular/core';
import {Product} from "../../model/product";
import {ProductService} from "../../service/product.service";
import {Page} from "../../model/page";
import {ProductFilter} from "../../model/productFilter";

@Component({
  selector: 'app-product-gallery-page',
  templateUrl: './product-gallery-page.component.html',
  styleUrls: ['./product-gallery-page.component.scss']
})
export class ProductGalleryPageComponent implements OnInit {

  products: Product[] = [];

  page?: Page;

  nameFilter?: ProductFilter;

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.productService.findAll().subscribe(res => {
      console.log("Loading products..");
      this.page = res;
      this.products = res.content;
    }, error => {
      console.log(`Error loading products ${error}`);
    });
  }

  goToPage(page: number) {
    this.productService.findAll(page, this.nameFilter).subscribe(res => {
      console.log("Loading products..");
      this.page = res;
      this.products = res.content;
    }, error => {
      console.log(`Error loading products ${error}`);
    });
  }

  applyNameFilter(nameFilter: ProductFilter) {
    this.productService.findAll(1, nameFilter).subscribe(res => {
      console.log("Loading products..");
      this.page = res;
      this.products = res.content;
    }, error => {
      console.log(`Error loading products ${error}`);
    });
  }
}
