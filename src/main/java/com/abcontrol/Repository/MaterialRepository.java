package com.abcontrol.Repository;
import com.abcontrol.Entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//JpaRepository possui metodos prontos para fazer pesistencia no banco
@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    Material findById(long id);

}
