package com.acme.mailreader.domain;

import org.junit.Ignore;
import org.junit.Test;

import com.acme.mailreader.utils.DateIncorrecteException;

public class MailTest {
	

	@Ignore
	@Test(expected=DateIncorrecteException.class)
	public final void erreurSiDateAvant1979() throws DateIncorrecteException {
		//TODO
				
	}
	
	
	
}
