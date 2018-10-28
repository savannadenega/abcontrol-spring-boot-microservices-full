package com.abcontrol.repository;

import com.abcontrol.entity.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author FrancieleNF
 */
//JpaRepository possui metodos prontos para fazer pesistencia no banco
@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {

    FormaPagamento findById(long id);

}

