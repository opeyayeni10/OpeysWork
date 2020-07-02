package com.ait.dao;

import java.sql.SQLException;
import java.util.List;

import com.ait.dto.TransactionDto;

public interface TransactionDAO {

	List<TransactionDto> retrieveUnSettledTransactions(String artistIdentity) throws SQLException ;

}
