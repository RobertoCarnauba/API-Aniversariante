package com.adin.caedu.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.adin.caedu.model.CED_CampaingListModel;

@Repository
@Component
@Transactional
public interface CED_CampaingResultRepository extends JpaRepository<CED_CampaingListModel, Integer> {

	@Query(value = "SELECT NVL(MAX(c.ID), 0) AS MAX_VAL FROM CED c", nativeQuery = true)
	public int lastId();

	/*
	 * @Modifying
	 * 
	 * @Query(value =
	 * "INSERT INTO CED (EVENT_TYPE_ID, ACCOUNT_ID, LIST_ID, RIID, CUSTOMER_ID," +
	 * "EVENT_CAPTURED_DT, EVENT_STORED_DT, CAMPAIGN_ID, LAUNCH_ID," +
	 * "EMAIL_FORMAT, OFFER_NAME, OFFER_CATEGORY, OFFER_URL, OFFER_NUMBER," +
	 * "EMAIL, BOUNCE_TYPE, REASON, REASON_CODE, SUBJECT," +
	 * "CONTACT_INFO, EMAIL_ISP, COMPLAINER_EMAIL, SPAM_TYPE, COMPLAINT_DT," +
	 * "OFFER_SIGNATURE_ID, DYNAMIC_CONTENT_SIGNATURE_ID, MESSAGE_SIZE, SEGMENT_INFO) "
	 * +
	 * "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12, ?13, ?14, ?15, ?16, ?17, ?18, ?19, ?20, "
	 * + "?21, ?22, ?23, ?24, ?25, ?26, ?27, ?28)", nativeQuery = true) void saveCED
	 * (Integer EVENT_TYPE_ID, Integer ACCOUNT_ID, Integer LIST_ID, Integer RIID,
	 * String CUSTOMER_ID, String EVENT_CAPTURED_DT, String EVENT_STORED_DT, Integer
	 * CAMPAIGN_ID, Integer LAUNCH_ID, Character EMAIL_FORMAT, String OFFER_NAME,
	 * String OFFER_CATEGORY, String OFFER_URL, Integer OFFER_NUMBER, String EMAIL,
	 * Character BOUNCE_TYPE, String REASON, String REASON_CODE, String SUBJECT,
	 * String CONTACT_INFO, String EMAIL_ISP, String COMPLAINER_EMAIL, Integer
	 * SPAM_TYPE, String COMPLAINT_DT, String OFFER_SIGNATURE_ID, String
	 * DYNAMIC_CONTENT_SIGNATURE_ID, String MESSAGE_SIZE, String SEGMENT_INFO);
	 * 
	 * //@Query(value = "select * from VW_CONTACTLIST", nativeQuery = true) //public
	 * List<CED_ComplaintListModel> saveCED_ComplaintList();
	 * 
	 * //@Modifying //@Query(value =
	 * "INSERT INTO CED (EVENT_TYPE_ID, ACCOUNT_ID, LIST_ID, RIID, CUSTOMER_ID, EVENT_CAPTURED_DT, EVENT_STORED_DT, CAMPAIGN_ID, LAUNCH_ID, ) VALUES (?1, ?2, ?3, ?4);"
	 * , nativeQuery = true) //void setLastRoutine(Long lastID, Long quantia,
	 * Timestamp time, Integer ID_customer);
	 */
}
