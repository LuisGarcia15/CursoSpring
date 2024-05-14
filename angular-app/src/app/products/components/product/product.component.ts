import { Component } from '@angular/core';
import { ProductService } from '../../services/product.service';

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [],
  templateUrl: './product.component.html',
  styleUrl: './product.component.css'
})
export class ProductComponent {
  /*Un constructor de un componente siempre se define como
  constructor. un párametro que se defina su scope, será tambien
  un atributo de clase*/
  constructor(private service: ProductService){

  }
}
