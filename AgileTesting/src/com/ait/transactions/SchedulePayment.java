package com.ait.transactions;

import java.sql.SQLException;
import java.util.List;
import com.ait.dao.TransactionDAO;
import com.ait.dao.MembershipDAO;
import com.ait.dto.MembershipStatusDto;
import com.ait.dto.TransactionDto;
import com.ait.exception.ArtGalleryException;
import com.ait.exception.ArtGalleryMemberNotFound;
import com.ait.exception.MembershipDAOException;
import com.ait.exception.PayPalException;
import com.ait.exception.PaymentHandlingException;

public class SchedulePayment {
	private final TransactionDAO transactionDAO;
	private final MembershipDAO membershipDAO;
	private final PayPalFacade payPalFacade;

	public SchedulePayment(TransactionDAO transactionDAO,
			MembershipDAO membershipDAO, PayPalFacade payPalFacade) {
		super();
		this.transactionDAO = transactionDAO;
		this.membershipDAO = membershipDAO;
		this.payPalFacade = payPalFacade;
	}

	public int payArtist(final String artistIdentity)
			throws ArtGalleryException {
		List<TransactionDto> unPaidTransactions;
		try {
			MembershipStatusDto membershipDto = membershipDAO
					.getStatusFor(artistIdentity);
			unPaidTransactions = transactionDAO
					.retrieveUnSettledTransactions(artistIdentity);
			String payPalId = null;
			String transactionAdvice = "";
			double totalTxAmount = 0.00;
			for (TransactionDto unPaidTransaction : unPaidTransactions) {
				totalTxAmount += unPaidTransaction.getAmount();
				transactionAdvice += unPaidTransaction.getTargetId() + " ";
			}
			payPalId = membershipDto.getPayPalId();
			double payableAmount = totalTxAmount - totalTxAmount
					* membershipDto.getDeductable();
			payPalFacade.sendAdvice(transactionAdvice, payableAmount, payPalId);
		} catch (SQLException e) {
			throw new ArtGalleryMemberNotFound(artistIdentity);
		} catch (MembershipDAOException e) {
			throw new MembershipDAOException(artistIdentity);
		} catch (PayPalException e) {
			throw new PaymentHandlingException(artistIdentity);
		}
		return unPaidTransactions.size();
	}

}
