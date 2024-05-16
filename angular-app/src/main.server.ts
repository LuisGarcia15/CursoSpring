import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { config } from './app/app.config.server';
import { ProductComponent } from './app/products/components/product/product.component';

/*Si quisiermaos cambiar un componente como principal, en main.server debemos
cambiar el componente actual por el nuevo, así como tambien en main
y colocar la etiqueta de selector en index.html*/
const bootstrap = () => bootstrapApplication(AppComponent, config);

export default bootstrap;
