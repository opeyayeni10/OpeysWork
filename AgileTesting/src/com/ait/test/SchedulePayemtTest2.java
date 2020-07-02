package com.ait.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ait.dao.MembershipDAO;
import com.ait.dao.TransactionDAO;
import com.ait.dto.MembershipStatusDto;
import com.ait.dto.TransactionDto;
import com.ait.exception.ArtGalleryException;
import com.ait.exception.ArtGalleryMemberNotFound;
import com.ait.exception.MembershipDAOException;
import com.ait.exception.PayPalException;
import com.ait.exception.PaymentHandlingException;
import com.ait.transactions.PayPalFacade;
import com.ait.transactions.SchedulePayment;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

import java.awt.SecondaryLoop;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyDouble;

public class SchedulePayemtTest2 {
	private TransactionDAO transactionDAO = mock(TransactionDAO.class);
	private MembershipDAO membershipDAO = mock(MembershipDAO.class);
	private PayPalFacade payPalFacade = mock(PayPalFacade.class);
	private MembershipStatusDto member;
	private TransactionDto transaction;
	private SchedulePayment schedulePayment;
	private String idParam = "123456";
	private ArrayList<TransactionDto> transactionList;

	@Before
	void setUp() {
		schedulePayment = new SchedulePayment(transactionDAO, membershipDAO, payPalFacade);
		member = new MembershipStatusDto();
		transactionList = new ArrayList<TransactionDto>();
		member.setDeductable(0.1);
		member.setPayPalId(idParam);
		transaction = new TransactionDto();

	}

	@Test
	public void testWhenNoTransactionToProcessReturnsZero() throws Exception {
		when(membershipDAO.getStatusFor(idParam)).thenReturn(member);
		when(transactionDAO.retrieveUnSettledTransactions(member.getPayPalId())).thenReturn(transactionList);
		assertEquals(0, schedulePayment.payArtist(idParam));
		verify(payPalFacade, times(1)).sendAdvice("", 0, idParam);
	}

	@Test
	public void testWhenOneTransactionToProcessReturnsOne() throws Exception {
		TransactionDto item1 = new  TransactionDto();
		item1.setAmount(10);
		item1.setTargetId("ART001");
		transactionList.add(item1);
		when(membershipDAO.getStatusFor(idParam)).thenReturn(member);
		when(transactionDAO.retrieveUnSettledTransactions(member.getPayPalId())).thenReturn(transactionList);
		assertEquals(1, schedulePayment.payArtist(idParam));
		verify(payPalFacade, times(1)).sendAdvice("ART001", 9.0, idParam);
		}
	@Test
	public void testWhenTwoTransactionToProcessReturnsTwo() throws Exception {
		TransactionDto item1 = new  TransactionDto();
		item1.setAmount(10);
		item1.setTargetId("ART001");
		
		TransactionDto item2 = new  TransactionDto();
		item1.setAmount(20);
		item1.setTargetId("ART002");
		
		transactionList.add(item1);
		transactionList.add(item2);
		when(membershipDAO.getStatusFor(idParam)).thenReturn(member);
		when(transactionDAO.retrieveUnSettledTransactions(member.getPayPalId())).thenReturn(transactionList);
		assertEquals(2, schedulePayment.payArtist(idParam));
		verify(payPalFacade, times(1)).sendAdvice(eq("ART001 ART002"), eq(27.0), eq(member.getPayPalId()));
	}
	@Test(expected = MembershipDAOException.class)
	public void testMembershipDAOException() throws Exception {
		when(membershipDAO.getStatusFor(idParam)).thenThrow(MembershipDAOException.class);
		schedulePayment.payArtist(idParam);
	}
	@Test(expected = ArtGalleryMemberNotFound.class)
	public void testArtGalleryMemberNotFound() throws Exception {
		when(membershipDAO.getStatusFor(idParam)).thenReturn(member);
		when(transactionDAO.retrieveUnSettledTransactions(member.getPayPalId())).thenThrow(SQLException.class);
		schedulePayment.payArtist(idParam);
	}
	@Test(expected = PaymentHandlingException.class)
	public void testPaymentHandlingException() throws Exception {
		TransactionDto item1 =  new TransactionDto();
		item1.setAmount(10);
		item1.setTargetId("Art001");
		transactionList.add(item1);
		when(membershipDAO.getStatusFor(idParam)).thenReturn(member);
		when(transactionDAO.retrieveUnSettledTransactions(member.getPayPalId())).thenReturn(transactionList);
		doThrow(PayPalException.class).when(payPalFacade).sendAdvice("Art001", 10, member.getPayPalId());
		schedulePayment.payArtist(idParam);
	}
}

