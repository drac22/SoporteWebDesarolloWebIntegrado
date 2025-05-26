package pruebas.demo.model.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudDTOResponse {
    private Long idSolicitud;
    private Long idUsuario;
    private String nombreUsuario;
    private String motivo;
    private String estado;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaCulminacion;
    private String tipoSolicitud;
    private String nombreCoordinador;
}
