package pruebas.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import pruebas.demo.model.tipos.TipoCredencial;

@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "tbl_credencial")
public class Credencial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCredencial;

    @Column(unique = true, nullable = false)
    private String correo;

    @Column(name = "contraseña", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "idTipoCredencial")
    private TipoCredencial tipoCredencial;

    @OneToOne(mappedBy = "credencial")
    @JsonManagedReference
    private Usuario usuario;

    @OneToOne(mappedBy = "credencial")
    @JsonManagedReference
    private Colaborador colaborador;
}
