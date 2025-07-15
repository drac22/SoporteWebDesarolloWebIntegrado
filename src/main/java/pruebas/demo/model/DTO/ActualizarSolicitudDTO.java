package pruebas.demo.model.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ActualizarSolicitudDTO {
    private Long idEstado;
    private LocalDateTime fechaCulminacion;
}
