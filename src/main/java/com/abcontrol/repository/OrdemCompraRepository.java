package com.abcontrol.repository;

import com.abcontrol.entity.OrdemCompraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author SavannaDenega
 */
//JpaRepository possui metodos prontos para fazer pesistencia no banco
@Repository
public interface OrdemCompraRepository extends JpaRepository<OrdemCompraEntity, Long> {

    OrdemCompraEntity findById(long id);

}

