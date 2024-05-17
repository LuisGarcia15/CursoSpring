import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { FormComponent } from '../form.component';

@Component({
  selector: 'app-product',
  standalone: true,
  /*colocamos un componente hijo de un formulario en este componente de product.

RECUERDA
para que el componente pueda existir, debemos importar el componente hijo
en el componente padre en el archivo ts y colocar su selector del componente
hijo en el archivo html del componente padre, en este caso FormComponent como
import*/
  imports: [CommonModule, RouterOutlet, FormComponent],
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

  /*Método que maneja el producto creado recientemente enviado por el
  componente hijo, como envia un producto, el parámetro a recibir es
  un objeto producto*/
  addProduct(product: Product){
    /*primera forma de agregar un elemento a la tabla front end
    Utilizar, en el arreglo existente, la función push y pasar el objeto
    existente*/
    product.id = new Date().getTime();
    this.products.push(product);
    /*segunda forma de agregar un elemento a la tabla front end
    Esparcimos todos los objetos de un array anteriormente
    existente, y agregamos un nuevo elemento a ese arreglo.
    Similar a react, donde en react solo existe esta forma*/
    //this.products = [... this.products, {...product, id: new Date().getTime()}]
  }
}
