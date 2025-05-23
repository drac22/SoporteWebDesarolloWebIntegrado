package pruebas.demo.model.tipos;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_tipo_credencial")
public class TipoCredencial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoCredencial;

    private String tipoCredencial;
}
