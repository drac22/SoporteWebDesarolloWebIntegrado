package pruebas.demo.model.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AsignacionDTOResponse {
    private Long idAsignacion;
    private Long idSolicitud;
    private String motivo;
    private String nombreUsuario;
    private String nombreTipoSolicitud;
    private LocalDateTime fechaRegistro;
}
