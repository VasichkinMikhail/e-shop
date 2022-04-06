import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AddLineItemDto} from "../model/add-line-item-dto";
import {Observable} from "rxjs";
import {AllCartDto} from "../model/all-cart-dto";
import {LineItem} from "../model/line-item";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient) { }

  public findAll() : Observable<AllCartDto> {
    return this.http.get<AllCartDto>('api/v1/cart/all');
  }

  public addToCart(dto: AddLineItemDto) {
    return this.http.post<any>('api/v1/cart', dto);
  }

  public removeLineItem(lineItem: LineItem) {
    return this.http.delete('api/v1/cart', ({
      body: lineItem
    }));
  }
}
