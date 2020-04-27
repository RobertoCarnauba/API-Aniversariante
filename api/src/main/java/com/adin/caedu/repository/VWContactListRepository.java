package com.adin.caedu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.adin.caedu.model.VW_ContactList;

public interface VWContactListRepository extends JpaRepository<VW_ContactList, Integer> {

	@Transactional
	//WHERE vw.DATA_ATUALIZACAO_REGISTRO >= SYSDATE - 1
	@Query(value = "SELECT * from " + "(SELECT vw.*, rownum r FROM VW_CLIENTE vw)"
			+ " WHERE r > ?1 and r < ?2", nativeQuery = true)
	public List<VW_ContactList> getVWContactList(int start, int end);

	@Transactional
	@Query(value = "SELECT count(*) from VW_CLIENTE", nativeQuery = true)
	public int getCount();

	@Transactional
	@Modifying
	@Query(value = "UPDATE CRM.CLIENTE cli SET cli.codigo_cliente_responsys = ?2 "
			+ "where cli.cpf = ?1", nativeQuery = true)
	public void updateRiid(String cpf, Integer codigo_cliente_responsys);

	@Transactional
	@Modifying
	@Query(value = "UPDATE dim_rfv dim SET dim.codigo_cliente_responsys = ?2 "
			+ "where dim.cpf = ?1", nativeQuery = true)
	public void updateRiidRfv(String cpf, Integer codigo_cliente_responsys);

}
