package pruebas.demo.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsignacionDTO {
    private Long idSolicitud;
    private Long idColaborador;
    private boolean esCoordinador;
}
