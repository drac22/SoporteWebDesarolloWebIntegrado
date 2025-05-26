package pruebas.demo.model.DTO;


import lombok.Data;

@Data
public class ActividadDTO {
    private Long idAsignacion;
    private String detalleActividad;
    private int minutosTrabajados;
}
