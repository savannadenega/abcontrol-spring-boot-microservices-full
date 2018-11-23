package com.abcontrol.repository;

import com.abcontrol.entity.CompraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author SavannaDenega
 */
//JpaRepository possui metodos prontos para fazer pesistencia no banco
@Repository
public interface CompraRepository extends JpaRepository<CompraEntity, Long> {

    CompraEntity findById(long id);

}

