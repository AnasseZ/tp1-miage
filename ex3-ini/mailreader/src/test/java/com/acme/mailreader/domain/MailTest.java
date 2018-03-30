package com.acme.mailreader.domain;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.time.Instant;

import org.junit.Ignore;
import org.junit.Test;

import com.acme.mailreader.domain.Mail.Statut;
import com.acme.mailreader.utils.DateIncorrecteException;

public class MailTest {
	
	MailComparator comparator = new MailComparator();
	

	@Ignore
	@Test(expected=DateIncorrecteException.class)
	public final void erreurSiDateAvant1970() throws DateIncorrecteException {
		new Mail.Builder("sujet").date(Instant.parse("1918-03-25T12:00:00.00Z")).build();			
	}
	
	@Test
	public final void premierPlusPetitSiDateNulle() throws DateIncorrecteException  {
		Mail mail1 = new Mail.Builder("uyyuy").important(false).statut(Statut.READ).build();
		Mail mail2 = new Mail.Builder("uyyuy").important(false).statut(Statut.READ).date(Instant.now()).build();
		assertThat(comparator.compare(mail1, mail2),is(1));
				
	}
}
