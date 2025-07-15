package pruebas.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pruebas.demo.model.Notificacion;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
}
