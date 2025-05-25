package pruebas.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pruebas.demo.model.tipos.EstadoSolicitud;

@Repository
public interface EstadoSolicitudRepository extends JpaRepository<EstadoSolicitud, Long>{
    
}
