package com.adin.caedu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.adin.caedu.model.VW_AniversariantesModel;

@Repository
@Component
@Transactional
public interface VW_AniversariantesRepository extends JpaRepository<VW_AniversariantesModel, Integer> {
	@Transactional
	@Query(value = "SELECT * from (select vw.*, rownum r from VW_ANIVERSARIO vw) where r > ?1 and r < ?2", nativeQuery = true)
	public List<VW_AniversariantesModel> getVWAniversariantes(int start, int end);

	@Transactional
	@Query(value = "SELECT count(*) from VW_ANIVERSARIO", nativeQuery = true)
	public int getCount();
}
