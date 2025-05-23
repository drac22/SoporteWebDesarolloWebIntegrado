package pruebas.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pruebas.demo.model.tipos.TipoCliente;

@Repository
public interface TipoClienteRepository extends JpaRepository<TipoCliente, Long>{
    
}
