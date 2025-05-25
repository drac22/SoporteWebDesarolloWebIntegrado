package pruebas.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pruebas.demo.model.tipos.TipoCredencial;

@Repository
public interface TipoCredencialRepository extends JpaRepository<TipoCredencial, Long>{
    
}
