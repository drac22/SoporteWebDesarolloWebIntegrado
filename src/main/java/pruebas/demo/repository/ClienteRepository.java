package pruebas.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pruebas.demo.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long>{
    
}
