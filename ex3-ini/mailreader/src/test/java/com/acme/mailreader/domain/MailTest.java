package com.acme.mailreader.domain;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.time.Instant;

import org.hamcrest.core.Is;
import org.junit.Ignore;
import org.junit.Test;

import com.acme.mailreader.domain.Mail.Statut;
import com.acme.mailreader.utils.DateIncorrecteException;

public class MailTest {
	
	MailComparator comparator = new MailComparator();
	

	@Ignore
	@Test(expected=DateIncorrecteException.class)
	public final void erreurSiDateAvant1970() throws DateIncorrecteException {
		//TODO
				
	}
	
	@Test
	public final void premierPlusPetitSiDateNulle() throws DateIncorrecteException  {
		Mail mail1 = new Mail.Builder("uyyuy").important(false).statut(Statut.READ).build();
		Mail mail2 = new Mail.Builder("uyyuy").important(false).statut(Statut.READ).date(Instant.now()).build();
		assertThat(comparator.compare(mail1, mail2),is(1));
				
	}
}
