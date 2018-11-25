package com.abcontrol.repository;

import com.abcontrol.entity.ProjetoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author FrancieleNF
 */
@Repository
public interface ProjetoRepository extends JpaRepository<ProjetoEntity, Long> {

    ProjetoEntity findById(long id);

}