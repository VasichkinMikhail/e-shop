import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ProductGalleryPageComponent} from "./page/product-gallery-page/product-gallery-page.component";
import {ProductInfoPageComponent} from "./page/product-info-page/product-info-page.component";

const routes: Routes = [
  {path: "", pathMatch: "full", redirectTo: "product"},
  {path: "product", component: ProductGalleryPageComponent},
  {path: "product/:id", component: ProductInfoPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
