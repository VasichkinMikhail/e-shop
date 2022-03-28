import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Page} from "../model/page";
import {ProductFilter} from "../model/productFilter";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  public findAll(page?: number, nameFilter?: ProductFilter) : Observable<Page> {
    let params = new HttpParams();
    if (nameFilter?.nameFilter != null) {
      params = params.set("nameFilter", nameFilter.nameFilter);
    }
    params = params.set("page", page != null ? page : 1);
    params = params.set("size", 3);
    return this.http.get<Page>('api/v1/product/all', {params});
  }
}
