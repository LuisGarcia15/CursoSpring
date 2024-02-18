package com.luis.curso.springboot.webapp.springbootweb.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luis.curso.springboot.webapp.springbootweb.dto.UserDTO;
import com.luis.curso.springboot.webapp.springbootweb.models.User;

import org.springframework.web.bind.annotation.RequestMethod;


@RestController
/*Los componentes de Spring son objetos que se guardan
 * en un contenedor y con Spring los manejas.
 * Un RestController nos sirve para crear servicios
 * RestFUL web usando Spring MVC. Le permite a la clase
 * manejar peticiones hechas por el cliente.
 * La respuesta a la petición, sera un JSON
 * Articulo sobre APIS
 * https://aws.amazon.com/es/what-is/api/#:~:text=en%20su%20teléfono.-,¿Qué%20significa%20API%3F,de%20servicio%20entre%20dos%20aplicaciones.
*/
@RequestMapping("/api")
/*
 * Permite definir una ruta de primer nivel. Tambien permite
 * generalizar el tipo de petición al colocarlo en un método
 * por ejemplo, en vez de colocar GetMapping, colocamos
 * RequestMapping y la petición Get seguira funcionando (Por defecto
 * es get), pero podemos enviar como parámetro que sea Post, Delete,
 * etc.
 * RequestMapping tiene como función PRINCIPAL mapear un request a 
 * una ruta url
 */
public class UserRestController {

   /*El controlador interactua con los datos que provienen de 
     * la lógica de negocios para mostrarlo en el modelo, procesando
     * los datos provistos por los usuarios MVC. El modelo es una
     * presentación JSON
    */

    /*Get: Permite obtener un recurso, ya sea información
     * o una simple vista
    */

    /*Dispatcher Servlet: Controlador de vistas. Se encarga de seleccionar
     * el controlador y su handler mapping (y tipo de request) a una
     * url.
     * Handler mapping: Ruta de mapeo de accion del controllador. Se puede
     * mapear a una ruta con rquestmapping, get mapping, etc
    */
    @RequestMapping(path="/detailsRest", method=RequestMethod.GET)
    public Map<String,Object> details(){
        User user = new User("Luis", "Garcia");
        Map<String, Object> body = new HashMap<>();
        body.put("title", "Hola mundo json");
        body.put("user", user);
        return body;
        /*Es posible retornar como respuesta a una REST cualquier
         * clase POJO que sea Serializble,por lo que podriamos retornar
         * user sin problema. Es posible tambien retornar un objeto
         * collection
        */

        /*Un controlador proporciona acceso a la lógica de negocio.
         * Delegando la lógica de necgocio a un conjunto de componentes
         * de servicio. Los servicios a su vez acceden a las bases de datos
         * mediante un DAO (Objeto de acceso a datos). Los controladores
         * tambien reciben parámetros del usuario (input) y lo convierte
         * en un objeto del modelo, poblando en sus atributos los datos
         * enviados, como resultado de la lógica de negocio
         */
    }

    @RequestMapping(path="/detailsRestDTO", method=RequestMethod.GET)
    public UserDTO detailsDTO(){
        UserDTO userDTO = new UserDTO();
        User user = new User("Luis DTO", "Garcia DTO");
        userDTO.setUser(user);
        userDTO.setTitle("Hola mundo DTO");
        return userDTO;
        /*Un DTO tambien es un POJO pero
     * de propocito especifico al solo
     * ser usado para el almacenamiento
     * y transporte de datos. Es usado tambien para mostrar
     * o transportar datos en formato JSON, en vez de usar
     * una estructura llave-valor (MAP)
    */
}

    @GetMapping("/list")
    public List<User> list(){
        User user1 = new User("Luis", "Garcia");
        User user2 = new User("Karina", "Gonzalez");
        User user3 = new User("Mariana", "Rendon");
        /*List<User> users = Arrays.asList(user1,user2,user3);
         * Si se conocen los elementos de la lista
        */
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        return users;
    }
}
