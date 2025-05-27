package pruebas.demo.model.DTO;

import java.util.List;

import lombok.Data;

@Data
public class ClienteDTO {
    private String nombreCliente;
    private Long tipoCliente;
    private List<Long> softwares;
}
