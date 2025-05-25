package pruebas.demo.model.DTO;


import lombok.Data;

@Data
public class UsuarioDTO {
    private String nombreUsuario;
    private String telefonoUsuario;
    private String correo;
    private String password;
    private Long idCliente;
}
