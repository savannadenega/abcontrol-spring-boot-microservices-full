package com.abcontrol.repository;

import com.abcontrol.entity.FormaPagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author FrancieleNF
 */
//JpaRepository possui metodos prontos para fazer pesistencia no banco
@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamentoEntity, Long> {

    FormaPagamentoEntity findById(long id);

}

