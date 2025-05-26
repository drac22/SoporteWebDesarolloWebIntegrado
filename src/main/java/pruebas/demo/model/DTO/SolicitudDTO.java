package pruebas.demo.model.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudDTO {
    private Long idUsuario;
    private String motivo;
    private Long idTipoSolicitud;
}
