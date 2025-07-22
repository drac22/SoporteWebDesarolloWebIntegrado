package pruebas.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pruebas.demo.model.Solicitud;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {
    List<Solicitud> findByUsuarioIdUsuario(Long idUsuario);

    int countByEstadoIdEstadoSolicitud(Long idEstadoSolicitud);
}
