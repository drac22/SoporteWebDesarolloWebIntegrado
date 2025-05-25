package pruebas.demo.model.DTO;

import java.util.List;

public class ClienteDTO {
    private String nombreCliente;
    private Long tipoCliente;
    private List<Long> softwares;

    // Getters y Setters
    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Long getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(Long tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public List<Long> getSoftwares() {
        return softwares;
    }

    public void setSoftwares(List<Long> softwares) {
        this.softwares = softwares;
    }
}
