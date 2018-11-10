package com.abcontrol.repository;

import com.abcontrol.entity.ObraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author FrancieleNF
 */
@Repository
public interface ObraRepository extends JpaRepository<ObraEntity, Long> {

    ObraEntity findById(long id);

}
