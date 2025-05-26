package pruebas.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pruebas.demo.model.Actividad;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Long> {
    @Query("SELECT a FROM Actividad a WHERE a.asignacion.idAsignacion = :id")
    List<Actividad> findByIdAsignacion(@Param("id") Long id);
}
