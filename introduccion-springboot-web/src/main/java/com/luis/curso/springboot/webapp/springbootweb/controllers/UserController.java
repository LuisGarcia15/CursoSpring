package com.luis.curso.springboot.webapp.springbootweb.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.luis.curso.springboot.webapp.springbootweb.models.User;

@Controller
/*Los componentes de Spring son objetos que se guardan
 * en un contenedor y con Spring los manejas.
 * Un controller funciona como una clase para menejar
 * peticiones y respuestas de usuarios
*/
public class UserController {
    /*El controlador interactua con los datos que provienen de 
     * la lógica de negocios para mostrarlo en la vista, procesando
     * los datos provistos por los usuarios MVC
    */

    /*Al trabajar con MVC, debemos devolver la vista
     * de la peticipon menejada, por ello el método
     * devolverá una vista html (No debe llevar la
     * extención)
    */
    
    /*Get: Permite obtener un recurso, ya sea información
     * o una simple vista
    */
    @GetMapping("/details")
    public String details(Model model){
        User user = new User("Luis", "Garcia");
        model.addAttribute("title", "Hola Mundo");
        model.addAttribute("usuario", user);
        return "details";
    }
    /*Model
     *Es un contenedor de un modelo de atributos que es diseñado para
     agregar atributos al modelo. Es comunmente usado para enviar datos
     entre la vista y el controlador. Actua como un contenedor de datos
     de la aplicación
    */
    @GetMapping("/list")
    public String list(ModelMap modelMap){
        modelMap.addAttribute("title", "Lista Usuarios");
        return "list";
    }
    /*ModelMap
     *Es un contenedor basado en Map java.util que actua como modelo de 
     atributos y que es diseñado para agregar atributos al modelo. Es 
     comunmente usado para enviar datos entre la vista y el controlador. 
     Actua como un contenedor de datos de la aplicación
    */

    @ModelAttribute("users")
    public List<User> userModel(){
        List<User> users = new ArrayList<>();
        users.add(new User("Gus", "Santillan"));
        users.add(new User("Karina", "Gonzales", "karina@icloud.com"));
        users.add(new User("Mariana", "Berumen", "mariana@icloud.com"));
        return users;
    }
    /*ModelAttribute
     * Permite definir una función como un atributo de un objeto
     * Model, por lo que el resultado de esa función, sera un atributo
     * de un objeto Model global, cualquier objeto Model que se cree
     * tendra como atributo aquellos resultados de una función con la
     * notación @ModelAtributte, siempre y cuando sea utilizado en el
     * controller donde se trabaja. Es una variable global para objetos
     * Model en un controlador. Permite reutilizar datos.
    */
}
