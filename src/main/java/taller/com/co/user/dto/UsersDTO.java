package taller.com.co.user.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

// OBJETO DE TRANSFERENCIA ENTRE EL CLIENTE Y EL SERVIDOR

@Data
public  class UsersDTO {

    private Long id;

    @NotEmpty(message = "el Nombre no puede estar vacio")
    private String name;

    @NotEmpty(message = "el Apellido no puede estar vacio")
    private String lastName;
}
