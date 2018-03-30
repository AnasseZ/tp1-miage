package com.acme.mailreader.domain;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.time.Instant;

import org.junit.Before;
import org.junit.Test;

import com.acme.mailreader.utils.DateIncorrecteException;

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
		assertThat(comparator.compare(mail1, mail2), is(MailComparator.EGAUX));
	}
	
	@Test
	public final void egauxSiUnDesMailsNuls() {
		Mail mail1 = new Mail();
		Mail mail2 = null;
		assertThat(comparator.compare(mail1, mail2), is(MailComparator.EGAUX));
	}

	@Test
	public final void premierMailPlusImportantQUeLautre() {
		Mail mail = new Mail.Builder("miage").important(true).build();
		Mail other = new Mail.Builder("miage").important(false).build();
		assertThat(comparator.compare(mail, other), is(MailComparator.PREMIER_PLUS_GRAND));
	}
	
	@Test
	public final void premierMailMoinsImportantQUeLautre() {
		Mail mail = new Mail.Builder("miage").important(false).build();
		Mail other = new Mail.Builder("miage").important(true).build();
		assertThat(comparator.compare(mail, other), is(MailComparator.PREMIER_PLUS_PETIT));
	}
	
	@Test
	public final void moinsUnSiPremierMailAUnSujetEtLeDeuxiemeNon() {
		Mail mail = new Mail();
		Mail other = new Mail();
		mail.setImportant(true);
		other.setImportant(true);
		
		mail.setSujet("Hey");
		assertThat(comparator.compare(mail, other), is(MailComparator.PREMIER_PLUS_GRAND));
	}
	
	@Test
	public final void premierPlusGrandSiEnvoyee(){
		Mail mail = new Mail.Builder("miage")
				.important(true)
				.statut(Mail.Statut.SENT)
				.build();
		Mail other = new Mail.Builder("miage")
				.important(true)
				.statut(Mail.Statut.UNSENT)
				.build();
		assertThat(comparator.compare(mail, other), is(MailComparator.PREMIER_PLUS_GRAND));
	}
	
	@Test
	public final void premierPlusPetitSiNonEnvoyee(){
		Mail mail = new Mail.Builder("miage")
				.important(true)
				.statut(Mail.Statut.UNSENT)
				.build();
		Mail other = new Mail.Builder("miage")
				.important(true)
				.statut(Mail.Statut.SENT)
				.build();
		assertThat(comparator.compare(mail, other), is(MailComparator.PREMIER_PLUS_PETIT));
	}
	
	@Test
	public final void premierPlusGrandSiDatePlusAncienne() throws DateIncorrecteException{
		Mail mail = new Mail.Builder("miage")
				.date(Instant.parse("2010-01-01T10:00:10Z"))
				.important(true)
				.statut(Mail.Statut.SENT)
				.build();
		Mail other = new Mail.Builder("miage")
				.date(Instant.parse("2015-01-01T10:00:10Z"))
				.important(true)
				.statut(Mail.Statut.SENT)
				.build();
		assertThat(comparator.compare(mail, other), is(MailComparator.PREMIER_PLUS_GRAND));
	}
	
	@Test
	public final void premierPlusPetitSiDatePlusAncienne() throws DateIncorrecteException{
		Mail mail = new Mail.Builder("miage")
				.important(true)
				.statut(Mail.Statut.SENT)
				.date(Instant.parse("2015-01-01T10:00:10Z"))
				.build();
		Mail other = new Mail.Builder("miage")
				.important(true)
				.statut(Mail.Statut.SENT)
				.date(Instant.parse("2010-01-01T10:00:10Z"))
				.build();
		
		assertThat(comparator.compare(mail, other), is(MailComparator.PREMIER_PLUS_PETIT));
	}
	
	@Test
	public final void egauxSiMailsIdentiques() throws DateIncorrecteException{
		Mail mail = new Mail.Builder("miage")
				.important(true)
				.statut(Mail.Statut.SENT)
				.date(Instant.parse("2010-01-01T10:00:10Z"))
				.build();
		Mail other = new Mail.Builder("miage")
				.important(true)
				.statut(Mail.Statut.SENT)
				.date(Instant.parse("2010-01-01T10:00:10Z"))
				.build();
		
		assertThat(comparator.compare(mail, other), is(MailComparator.EGAUX));
	}
}
