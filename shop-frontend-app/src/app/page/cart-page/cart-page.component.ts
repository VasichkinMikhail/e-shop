import {Component, OnInit} from '@angular/core';
import {CartService} from "../../service/cart.service";
import {AllCartDto} from "../../model/all-cart-dto";
import {OrderService} from "../../service/order.service";

export const CART_URL = 'cart'

@Component({
  selector: 'app-cart-page',
  templateUrl: './cart-page.component.html',
  styleUrls: ['./cart-page.component.scss']
})
export class CartPageComponent implements OnInit {

  content?: AllCartDto;

  constructor(private cartService: CartService, private orderService: OrderService) {
  }

  ngOnInit(): void {
    this.cartUpdated();
  }

  cartUpdated() {
    this.cartService.findAll().subscribe(
      res => {
        this.content = res;
      }
    )
  }
  createOrder(){
    this.orderService.createOrder();
  }
}
