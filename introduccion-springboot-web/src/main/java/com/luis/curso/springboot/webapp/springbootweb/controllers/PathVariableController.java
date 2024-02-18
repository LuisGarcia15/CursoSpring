package com.luis.curso.springboot.webapp.springbootweb.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luis.curso.springboot.webapp.springbootweb.dto.ParamDTO;
import com.luis.curso.springboot.webapp.springbootweb.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/var")
public class PathVariableController {
/*Se ejemplificará el envio de de parametros a travez de la ruta sin
 * involucrar los parametros del request de la url. Permite el envio
 * de datos desde un controlador hasta la aplicación
 */
    @Value("${config.code}")
    private Integer code;
/*@Values
 * Permite inyectar valores en atributos a nivel de clase
 * o en método a partir de valores definidos en application.properties
 * o un archivo propio .properties. Permite inyectar CONFIGURACIONES
 * 
 * Tambien es posible definir un archivo de configuración propio para
 * dentro de ese archivo definir valores que seran injectados en clases
 * de Spring
 */
    @Value("${config.username}")
    private String username;

    @Value("${config.listOfValues}")
    private String[] valueArray;
/*Es posible almacenar listas de un valor inyectado de un archivo .properties
 * Solo que el archivo JSON, de mostrarlo, lo tratará como un array no como
 * una lista Java
 */

    @Value("#{'${config.listOfValues}'.split(',')}")
    private List<String> valueList;
/*#{} 
 * Es un Lenguaje de Expresión de Spting, es quel que permite manipular 
 * consultas y manipular objetos en tiempo real
*/
    @Value("#{'${config.listOfValues}'.toUpperCase()}")
    private String valueString;

    @Value("#{${config.valuesMap}}")
    private Map<String, Object> valuesMap;

/*El lenguaje de expresión de Spring permite tambien inyectar elementos llave-valor
  *que se almacenen en un archivo .properties
  *
  *Es muy similar a lenguaje Java, podemos incluso, inyectar un valor apartir
  * de una llave en una estructura llave valor como lo hariamos en Java
  *
*/    
    @Value("#{${config.valuesMap}.product}")
    private String product;

    @Value("#{${config.valuesMap}.price}")
    private Integer price;

    @Autowired()
    private Environment environment;
    /*@Autowired
     * Permite buscar un componente de Spring que este almacenado
     * en su contenedor, cuando encuentre ese componente por su
     * tipo de dato, lo inyectara.
     * Esta anotación, permite que, cuando se defina una variable,
     * método, constructor, etc y ese código dependa de una instancia
     * ajena, en cuanto se intancie ese método faltante se enlaza´ra
     * al código que se le brindo la anotación.
     * 
     * Enviroment
     * Java Bean propia de Spring, encargado de encontrar recursos en
     * archivos .properties. al colocar la notación Autowired, podremos,
     * mediante la llave en el JSON, obtener el valor de esa llave del
     * entorno del programa. Esto último es por que Enviroment representa
     * la información de entorno de la aplicción de Spring cuando se ejecuta
     * y contiene dos elementos de llave, los cuales son Profiles y Properties
     * Es por ello que puede acceder a los archivos de configuración
     * 
     */

@GetMapping("/baz/{message}")
public ParamDTO pathVariable(@PathVariable(name = "message") String message) {
    ParamDTO param = new ParamDTO();
    param.setMessage(message);
    return param;
}
/*PathVariable
 *Permite definir variables a la ruta y enviarlas a la aplicación.
 *
 * Es más frecuente su uso en API Rest
 *
 * Permite enviar parametros a la url atraves de una url variable
 * Similar a RequestParam, solo que la url con PathVariable es 
 * más noble que la ur de RequestParam.
 * 
 * La url variable se coloca como argumento en la anotación
 * @GetMapping, entre llaves se coloca el parámetro.
 * 
 * Los parametros en de esta anotación son obligatorios.
 * 
 * Es posible definir un nombre para el parámetro enviado a la
 * url, de ser así, el nombre de variable debe ser igual al
 * nombre del parámetro en la url variable. Al hacer esto
 * es posible colocar otro nombre en la variable de método, 
 * aunque por convención esto no se hace.
 * 
 * Diferencia entre @PathVariable y @RequestParam
 * https://javabeat.net/spring-mvc-requestparam-pathvariable/
*/
@GetMapping("/mix/{product}/{id}")
public Map<String,Object> pathVariableMix(@PathVariable String product,
@PathVariable Long id) {
    Map <String, Object> json = new HashMap<>();
    json.put("product", product);
    json.put("id", id);
    return json;
}
/* Método que muestra como definir varios parámetros a la ruta
 * con @PathVariable. Siguen las mismas reglas de nombramiento
 * que cuando solo se envía un parámetro con @PathVariable
*/
@PostMapping("/create")
public User create(@RequestBody User user) {
    /*Lógica para manipular el usuario obtenido */
    user.setName(user.getName().toUpperCase());
    return user;
}
/*@RequestBody
 * Obtiene el cuerpo del request(Que tiene que ser un archivo
 * JSON) para leerlo y deserealizarlo en un Objeto Java siempre
 * y cuando el objeto cumpla con las características base de la
 * POJO modelo a travez de HTTPMessageREAD, siendo esta una}
 * interfaz que permite la codificación y decodificación de 
 * un contenido de mensaje HTTP
 */
/*Este ejemplo estamos enviando, por medio de postman un objeto
 * al contenido de request y es capturado por la anotación 
 * @RequestBody 
 */
/*@PostMapping
 *  Perimte enviar un recurso a la aplicación y dar tratamiento
 * a los datos
 * 
 * Regularmete @PostMapping no debe llevar ruta, solo es una
 * ejemplificación de la misma
 */

 @GetMapping("/values")
public Map<String,Object> values( @Value("${config.message}") String message) {
    Map <String, Object> json = new HashMap<>();
    json.put("code", code);
    json.put("username", username);
    json.put("message", message);
    json.put("valueArray", valueArray);
    json.put("valueString", valueString);
    json.put("valueList", valueList);
    json.put("valuesMap", valuesMap);
    json.put("price", price);
    json.put("product", product);
    json.put("messageAutowired", environment.getProperty(
        "config.messageAutowired"));
    json.put("codeClass", environment.getProperty(
        "config.codeClass",Integer.class));
    return json;
}
}
