package com.abcontrol.repository;
import com.abcontrol.entity.MaterialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//JpaRepository possui metodos prontos para fazer pesistencia no banco
@Repository
public interface MaterialRepository extends JpaRepository<MaterialEntity, Long> {

    MaterialEntity findById(long id);

}
