package pruebas.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_software")
public class Software {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSoftware;

    private String nombre;
}
