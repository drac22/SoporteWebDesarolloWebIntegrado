package pruebas.demo.model.DTO;

import lombok.Data;

@Data
public class ColaboradorDTO {
    private String nombreColaborador;
    private String telefonoColaborador;
    private String correo;
    private String password;
    private Long idRol;
}
