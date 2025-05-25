package pruebas.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pruebas.demo.model.Credencial;

@Repository
public interface CredencialRepository extends JpaRepository<Credencial, Long>{
    Optional<Credencial> findByCorreo(String correo);
}
