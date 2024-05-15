import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product';

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [],
  templateUrl: './product.component.html',
  styleUrl: './product.component.css'
})
export class ProductComponent implements OnInit{
    products:Product[] = [];
  /*Un constructor de un componente siempre se define como
  constructor. un párametro que se defina su scope, será tambien
  un atributo de clase*/
  constructor(private service: ProductService){}

  /*Método que se ejecuta cuando se crea el componente.
  ES UN MÉTODO DE LA INTERFAZ OnInit*/
  ngOnInit():void{
    this.service.findAll().subscribe(products => {
      this.products = products;
    })
    /*Con subscribe podemos manejar las peticiones, desde cancelarla
    hasta tratar los elementos obtenidos*/
  }
}
