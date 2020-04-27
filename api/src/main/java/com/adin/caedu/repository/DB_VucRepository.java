package com.adin.caedu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.adin.caedu.model.DB_VucModel;

public interface DB_VucRepository extends JpaRepository<DB_VucModel, Integer> {
	@Transactional
//	@Query(value = "SELECT * from (select vw.*, rownum r from VUC vw where vw.RIID is null) where r > ?1 and r < ?2", nativeQuery = true)
	@Query(value = "SELECT * from (select vw.*, rownum r from VUC vw where atualizacli > sysdate-5) where r > ?1 and r < ?2", nativeQuery = true)
	public List<DB_VucModel> getVWVuc(int start, int end);

	@Transactional
//	@Query(value = "SELECT count(*) from VUC where VUC.RIID is null", nativeQuery = true)
	@Query(value = "SELECT count(*) from VUC where atualizacli > sysdate-5", nativeQuery = true)
	public int getCount();

	@Transactional
	@Modifying
	@Query(value = "UPDATE VUC set RIID = ?2 WHERE CPF = ?1", nativeQuery = true)
	public void updateRiid(String cpf, Integer codigo_cliente_responsys);
}
