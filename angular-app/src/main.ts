import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';
import { ProductComponent } from './app/products/components/product/product.component';

/*Si quisiermaos cambiar un componente como principal, en main debemos
cambiar el componente actual por el nuevo, así como tambien en main.server
y colocar la etiqueta de selector en index.html*/

/*appConfig provee de la configuración necesaria a la aplicación. es por
ello que dentro de appConfig debemos configurar el cliente httpClient
para poder consumir una api backend*/
bootstrapApplication(AppComponent, appConfig)
  .catch((err) => console.error(err));
