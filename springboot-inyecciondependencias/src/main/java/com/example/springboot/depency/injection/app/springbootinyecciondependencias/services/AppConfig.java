package com.example.springboot.depency.injection.app.springbootinyecciondependencias.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.io.Resource;

import com.example.springboot.depency.injection.app.springbootinyecciondependencias.repositories.ProductRepository;
import com.example.springboot.depency.injection.app.springbootinyecciondependencias.repositories.ProductRepositoryJSON;

@Configuration
@PropertySources({@PropertySource(value =  "classpath:config.properties"
, encoding = "UTF-8")})
/*
 * @Configuration
 * Permite definir una clase de la cual se obtendran varios beans
 * para poder inyectarse a lo largo de la aplicacion, esto srive para
 * aquellas clases que no poseemos y que tramos por ejemplo de una API.
 * Tambien es posible definir una clase especializada para cargar
 * los elementos de archivos .properties con @PropetySources y
 * ----------------------------------------
 * @PropertySources
 * Permite agregar varias anotaciones PropertieSource para cargar
 * varios archivos de configuracion a una clase de configuracion
 * ----------------------------------------
 * @PropertySource
 * Permite cargar un archivo de configuracion a una clase de
 * condiguracion, en conjunto con PropertySources, permite definir
 * varias rutas de varios archivos .properties para utilizar
 * configuraciones de la aplicación
 * ----------------------------------------
 * Clase Enviroment
 * Proporciona acceso al entorno de la aplicacion que se este ejecutando.
 * Este entorno incluye información acerca de perfiles activos, propiedades
 * sistema, las propiedades del entorno, y otras configuraciones relacionadas 
 * con el entorno de ejecución de la aplicación.
 * ----------------------------------------
 * Para definir variables de los archivos de configuración, debe
 * existir un clase especializada de configuración que cargue todos
 * archivos .properties. Además con la clase Envioroment, se encarga de
 * buscar con ayuda de la clase de configuracion las variables de los archivos
 * ,properties con el metodo .getProperty("Propiedad de archivo .properties").
 * No olvidar que eobjeto de Enviroment debe tener la anotacion de Autowired
 * para enlazar (inyectar) el entorno de la aplicacion al objeto Enviroment y
 * asi manipular el entorno gracias a  la instancia de enviroment 
 */
public class AppConfig {

    @Value("classpath:product.json")
    private Resource resource;
    /*Un bean definida en una clase de la cual obtendremos varias
     * clases para inyectarlas, tambien puede cambiar su identificador
     * para usarse en @Qualifier ya que despues de todo es una clase 
     * almacenada en el contenedor de Spring o puede incluso llevar
     * la anotación @Primary, pues es una clase en el contenedor de
     * Spring
     */
    @Bean("productGames")
    public ProductRepository productRepositoryJSON(){
    /*Instaciamos siempre el metodo generico no el 
     * especifico
     */
    return new ProductRepositoryJSON(this.resource);
    }
}
