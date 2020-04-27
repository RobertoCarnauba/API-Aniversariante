package com.adin.caedu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.adin.caedu.model.VW_InadimplentesModel;

@Repository
@Component
@Transactional
public interface VW_InadimplentesRepository extends JpaRepository<VW_InadimplentesModel, Integer> {
	@Transactional 
	@Query(value = "SELECT * from (select vw.*, rownum r from VW_INADIMPLENTES vw) where r > ?1 and r < ?2", nativeQuery = true)
	public List<VW_InadimplentesModel> getVWInabimplentes(int start, int end);

	@Transactional
	@Query(value = "SELECT count(*) from VW_INADIMPLENTES", nativeQuery = true)
	public int getCount();
}
