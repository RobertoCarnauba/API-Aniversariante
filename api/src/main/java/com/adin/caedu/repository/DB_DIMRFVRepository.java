package com.adin.caedu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.adin.caedu.model.DB_DimRFVModel;

@Repository
@Component
@Transactional
public interface DB_DIMRFVRepository extends JpaRepository<DB_DimRFVModel, Integer> {
	@Query(value = "SELECT cpf, recencia, frequencia, segmento, data_atualiza, faixa, codigo_cliente_responsys from "
			+ "(SELECT vw.*, rownum r FROM dim_rfv vw)" + " WHERE r > ?1 and r < ?2", nativeQuery = true)
	public List<DB_DimRFVModel> getDBDimRFV(int start, int end);

	@Transactional
	@Query(value = "SELECT count(*) from dim_rfv", nativeQuery = true)
	public int getCount();

}
