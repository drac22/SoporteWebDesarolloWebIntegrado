package pruebas.demo.model.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActividadDTOResponse {
    private Long idActividad;
    private LocalDate fecha;
    private String detalleActividad;
    private int minutosTrabajados;
    private Long idSolicitud;
    private String nombreColaborador;
}
