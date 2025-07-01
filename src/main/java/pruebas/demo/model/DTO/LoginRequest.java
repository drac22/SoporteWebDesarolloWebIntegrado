package pruebas.demo.model.DTO;

import lombok.Data;

@Data
public class LoginRequest {
    private String usuario;
    private String contrasenia;
}
