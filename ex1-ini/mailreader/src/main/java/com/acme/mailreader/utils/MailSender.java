package com.acme.mailreader.utils;

import com.acme.mailreader.domain.Mail;

public interface MailSender {
	void envoyerMail(Mail mail);
}
