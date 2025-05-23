package pruebas.demo.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import pruebas.demo.model.tipos.TipoCliente;

@Data
@Entity
@Table(name = "tbl_cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    private String nombreCliente;

    @ManyToOne
    @JoinColumn(name = "idTipoCliente")
    private TipoCliente tipoCliente;

    @ManyToMany
    @JoinTable(name = "tb_cliente_software", joinColumns = @JoinColumn(name =
    "idCliente"), inverseJoinColumns = @JoinColumn(name = "idSoftware"))
    private Set<Software> softwares = new HashSet<>();
}
