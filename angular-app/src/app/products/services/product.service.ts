import { Injectable } from '@angular/core';
import { Product } from '../models/product';
import { Observable, of } from 'rxjs';
/*Clase que se va a conectar a la BD y obtener registros*/
@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private products: Product[] = [
    {
      id:1,
      name: 'Telefono Samsung',
      description: 'Samsung 2024',
      price: 399
    },
    {
      id:2,
      name: 'Telefono Apple',
      description: 'iPhone 2024',
      price: 399
    }
  ];
  constructor() { }

  /*Las funciones/observables son flujos cuando se conectan al backend y de forma
  programática podemos cancelar la petición por cualquier situación.
  Son asincronos*/
  findAll(): Observable<Product[]> {
    /* Transforma un dato en un objeto reactivo y observable*/
    return of(this.products);
  }
}
