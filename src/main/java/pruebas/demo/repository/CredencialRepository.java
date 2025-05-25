package pruebas.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pruebas.demo.model.Credencial;

@Repository
public interface CredencialRepository extends JpaRepository<Credencial, Long>{
    
}
