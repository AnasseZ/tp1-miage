package com.acme.mailreader.domain;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class MailComparatorTest {
	
	private MailComparator comparator;

	@Before
	public void setUp() throws Exception {
		this.comparator = new MailComparator();
	}

	@Test
	public final void egauxSiDeuxMailsNuls() {
		Mail mail1 = null;
		Mail mail2 = null;
		assertThat(comparator.compare(mail1, mail2), is(0));
	}
	
	@Test
	public final void egauxSiUnDesMailsNuls() {
		Mail mail1 = new Mail();
		Mail mail2 = null;
		assertThat(comparator.compare(mail1, mail2), is(0));
	}

	@Test
	public final void moinsUnSiPremierMailPlusImportantQUeLautre() {
		Mail mail = new Mail();
		Mail other = new Mail();
		mail.setImportant(true);
		other.setImportant(false);
		assertThat(comparator.compare(mail, other), is(-1));
	}
	
	@Test
	public final void unSiPremierMailMoinsImportantQUeLautre() {
		Mail mail = new Mail();
		Mail other = new Mail();
		mail.setImportant(false);
		other.setImportant(true);
		assertThat(comparator.compare(mail, other), is(1));
	}
	
	@Test
	public final void moinsUnSiPremierMailAUnSujetEtLeDeuxiemeNon() {
		Mail mail = new Mail();
		Mail other = new Mail();
		mail.setImportant(true);
		other.setImportant(true);
		
		mail.setSujet("Hey");
		
		assertThat(comparator.compare(mail, other), is(-1));
	}
}
