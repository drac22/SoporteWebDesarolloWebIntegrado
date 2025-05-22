package pruebas.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pruebas.demo.model.Software;

@Repository
public interface SoftwareRepositoy extends JpaRepository<Software,Long>{
    
}
