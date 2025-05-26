package pruebas.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pruebas.demo.model.Asignacion;
import pruebas.demo.model.Colaborador;
import pruebas.demo.model.Solicitud;

@Repository
public interface AsignacionRepository extends JpaRepository<Asignacion, Long> {
    boolean existsBySolicitudAndSolicitud_IdColaboradorQueEsCoordinador(Solicitud solicitud, Colaborador coordinador);
}
