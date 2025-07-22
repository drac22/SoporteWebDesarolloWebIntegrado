package pruebas.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pruebas.demo.model.Notificacion;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
    List<Notificacion> findByUsuario_IdUsuario(Long idUsuario);
}
