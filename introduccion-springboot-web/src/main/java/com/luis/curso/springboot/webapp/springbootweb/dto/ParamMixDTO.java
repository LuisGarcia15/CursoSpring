package com.luis.curso.springboot.webapp.springbootweb.dto;

public class ParamMixDTO {
    /*Clase de ejemplo para mostrar los multiples
 * parámetros de la anotación @RequestParam
*/

    private String message;
    private Integer code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
