package pruebas.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pruebas.demo.model.tipos.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{
    
}
