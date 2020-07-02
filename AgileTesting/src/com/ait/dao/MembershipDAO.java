package com.ait.dao;

import com.ait.dto.MembershipStatusDto;
import com.ait.exception.MembershipDAOException;

public interface MembershipDAO {

	MembershipStatusDto getStatusFor(String id) throws MembershipDAOException;

}
