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

import java.sql.SQLException;
import java.util.ArrayList;

import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyDouble;
public class SchedulePaymentTest {

	private TransactionDAO transactionDAO = mock(TransactionDAO.class);
	private MembershipDAO membershipDAO = mock(MembershipDAO.class);
	private PayPalFacade payPalFacade = mock(PayPalFacade.class);
	private MembershipStatusDto member;
	private TransactionDto transaction;
	private SchedulePayment schedulePayment ;
	private String idParam = "123456";
	private ArrayList<TransactionDto> transactionList;
	
	@Before
	public void setUp(){
		schedulePayment = new SchedulePayment(transactionDAO,membershipDAO,payPalFacade);
		member = new MembershipStatusDto();
		transaction = new TransactionDto();
		member.setPayPalId(idParam);
		member.setDeductable(0.1);
		transactionList = new ArrayList<TransactionDto>();
		
	}
	@Test
	public void whenNoTransactionToProcessReturnsZero() throws Exception {
		when(membershipDAO.getStatusFor(idParam)).thenReturn(member);
		when(transactionDAO.retrieveUnSettledTransactions(member.getPayPalId())).thenReturn(transactionList);
		assertEquals(0,schedulePayment.payArtist(idParam));
		verify(payPalFacade,times(1)).sendAdvice("", 0, idParam);
	}
	
	@Test
	public void whenOneTransactionToProcessReturnsOne() throws Exception{
		TransactionDto item1 =  new TransactionDto();
		item1.setAmount(10);
		item1.setTargetId("Art001");
		transactionList.add(item1);
		when(membershipDAO.getStatusFor(idParam)).thenReturn(member);
		when(transactionDAO.retrieveUnSettledTransactions(member.getPayPalId())).thenReturn(transactionList);
		assertEquals(1,schedulePayment.payArtist(idParam));
		//one of these lines -either is ok
		//verify(payPalFacade,times(1)).sendAdvice(eq("Art001 "), eq(9.0), eq(member.getPayPalId()));
		verify(payPalFacade,times(1)).sendAdvice("Art001 ", 9.0, "123456");
	}
	
	@Test
	public void whenTwoTransactionToProcessReturnsTwo() throws Exception{
		TransactionDto item1 =  new TransactionDto();
		item1.setAmount(10);
		item1.setTargetId("Art001");
		
		TransactionDto item2 =  new TransactionDto();
		item2.setAmount(20);
		item2.setTargetId("Art002");
		
		transactionList.add(item1);
		transactionList.add(item2);
		when(membershipDAO.getStatusFor(idParam)).thenReturn(member);
		when(transactionDAO.retrieveUnSettledTransactions(member.getPayPalId())).thenReturn(transactionList);
		assertEquals(2,schedulePayment.payArtist(idParam));
		verify(payPalFacade,times(1)).sendAdvice(eq("Art001 Art002 "), eq(27.0), eq(member.getPayPalId()));
	}
	
	@Test(expected = MembershipDAOException.class)
	public void testMembershipDAOException() throws Exception{
		when(membershipDAO.getStatusFor(idParam)).thenThrow(MembershipDAOException.class);
		schedulePayment.payArtist(idParam);
	}

	@Test(expected = ArtGalleryMemberNotFound.class)
	public void testArtGalleryMemberNotFound() throws Exception{
		when(membershipDAO.getStatusFor(idParam)).thenReturn(member);
		when(transactionDAO.retrieveUnSettledTransactions(member.getPayPalId())).thenThrow(SQLException.class);
		schedulePayment.payArtist(idParam);
	}
	
	@Test(expected = PaymentHandlingException.class)
	public void testPaymentHandlingException() throws Exception{
		TransactionDto item1 =  new TransactionDto();
		item1.setAmount(10);
		item1.setTargetId("Art001");
		transactionList.add(item1);
		when(membershipDAO.getStatusFor(idParam)).thenReturn(member);
		when(transactionDAO.retrieveUnSettledTransactions(member.getPayPalId())).thenReturn(transactionList);
		doThrow(PayPalException.class).when(payPalFacade).sendAdvice("Art001 ", 9.0 ,member.getPayPalId());
		schedulePayment.payArtist(idParam);
	} 
}