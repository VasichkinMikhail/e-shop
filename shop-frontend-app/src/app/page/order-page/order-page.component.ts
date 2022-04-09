import { Component, OnInit } from '@angular/core';
import {Order} from "../../model/order";
import {OrderService} from "../../service/order.service";
import {OrderStatusService} from "../../service/order-status.service";
import {CartService} from "../../service/cart.service";

@Component({
  selector: 'app-order-page',
  templateUrl: './order-page.component.html',
  styleUrls: ['./order-page.component.scss']
})
export class OrderPageComponent implements OnInit {

  orders: Order[] = [];

  constructor(private orderService: OrderService, private orderStatusService: OrderStatusService,private cartService: CartService) { }

  ngOnInit(): void {
    this.orderService.findOrdersByCurrentUser()
      .subscribe(orders => {
        this.orders = orders;
      }, err => {
        console.log(`Error retriving orders ${err}`);
      });
    this.orderStatusService.onMessage('/order_out/order')
      .subscribe(msg => {
        let updated = this.orders.find(order => order.id === msg.orderId);
        if (updated) {
          updated.status = msg.status;
        }
      });
  }
  viewOrder() {
    this.cartService.findAll()
      .subscribe(orders => {
        this.orders.find(order => order.id === order.id)
      });
  }

  deleteOrder() {
    this.orderService.deleteOrder(this.orders)
  }

}
