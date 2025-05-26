package pruebas.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_actividad")
public class Actividad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActividad;

    private LocalDate fecha;

    private String detalleActividad;

    @Column(name = "horasTrabajadas")
    private int minutosTrabajados;

    @ManyToOne
    @JoinColumn(name = "idAsignacion")
    private Asignacion asignacion;
}
