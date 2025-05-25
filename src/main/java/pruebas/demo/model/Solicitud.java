package pruebas.demo.model;

import java.time.LocalDateTime;
// import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
// import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import pruebas.demo.model.tipos.EstadoSolicitud;

@Data
@Entity
@Table(name = "tb_solicitud")
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSolicitud;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String motivo;

    @ManyToOne
    @JoinColumn(name = "idEstado", nullable = false)
    private EstadoSolicitud estado;

    @Column(nullable = false)
    private LocalDateTime fechaRegistro;

    private LocalDateTime fechaCulminacion;

    @ManyToOne
    @JoinColumn(name = "idTipoSolicitud")
    private TipoSolicitud tipoSolicitud;

    // @OneToMany(mappedBy = "solicitud")
    // private List<Asignacion> asignaciones;
}