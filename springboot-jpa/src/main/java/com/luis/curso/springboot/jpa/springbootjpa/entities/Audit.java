package com.luis.curso.springboot.jpa.springbootjpa.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Embeddable
/*Embeddable
 * Se coloca en una clase cuya función es contener atribiutos (columnas)
 * y métodos genéricos que varias tablas pueden utilizar al ser incrustado
 * un objeto de este tipo en una entidad
*/
public class Audit {

    @Column(name = "create_at")
    private LocalDateTime createAt;
    @Column(name = "updated_at")
    private LocalDateTime updateAt;

    @PrePersist
    public void prePersist(){
        System.out.println("****++++ Evento del ciclo de vida de la Entidad People - PrePersist ****++++");
        this.createAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        System.out.println("****++++ Evento del ciclo de vida de la Entidad People - PreUpdate ****++++");
        this.updateAt = LocalDateTime.now();
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
    
}
