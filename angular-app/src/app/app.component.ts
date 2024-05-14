import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

/*Un componente debe ser anotado con la notación component*/
@Component({
  /*El selector es aque nombre del componente al cual haremos
  referencia para usarlo en la aplicación*/
  selector: 'app-root',
  /* Indica que un componente no depende de modulos. Una aplicacion
  de angulas no es modular*/
  standalone: true,
  /*Modulos necesarios para el funcionamiento*/
  imports: [RouterOutlet, CommonModule],
  /*Vista asociada al componente y estilos asociados al componente.
  esa vista es única para este componente*/
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Hola mundo angular 17';
  enabled : boolean = false;

  courses: string[] = ['Angular', 'React', 'SpringBoot'];

  setEnabled(): void{
    /*Similar al operador ternario en java*/
    this.enabled = this.enabled? false: true;
  }
}

/*Se tiene una hoja de estilos global y una página html global para toda
la aplicación, siendo style e index respectivamente*/