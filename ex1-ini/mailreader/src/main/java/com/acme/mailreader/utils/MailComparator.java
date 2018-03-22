package com.acme.mailreader.utils;

import java.util.Comparator;

import com.acme.mailreader.model.Mail;

/**
 * Comparateur de mails
 * 
 * Comme on désire afficher les mails les plus importants en premier, l'element le plus grand retourne une valeur négative
 *
 */
public class MailComparator implements Comparator<Mail> {
	
	public final static int EQUALS = 0;
	public final static int MAIL_MORE_IMPORTANT = -1;
	public final static int OTHER_MORE_IMPORTANT = 1;
	
	public int compare(Mail mail, Mail other) {
		if (oneOfMailsNull(mail, other)) {
			return EQUALS;
		}
		if (differentImportance(mail, other)) {
			return sortByImportance(mail, other);
		}
		if (differentStatut(mail, other)) {
			return sortByStatut(mail, other);
		}
		if (differentSujet(mail, other)) {
			return sortBySujet(mail, other);
		}
		return mail.getDate().compareTo(other.getDate());
	}

	private int sortByImportance(Mail mail, Mail other) {
		if (mail.isImportant() && !other.isImportant()) {
			return MAIL_MORE_IMPORTANT;
		} 
		
		return OTHER_MORE_IMPORTANT;
	}

	private int sortByStatut(Mail mail, Mail other) {
		int comp = mail.getStatut().ordinal() - other.getStatut().ordinal();
		return comp < 0 ? OTHER_MORE_IMPORTANT : MAIL_MORE_IMPORTANT;
	}

	private int sortBySujet(Mail mail, Mail other) {
		if (mail.getSujet() == null && other.getSujet() != null) {
			return OTHER_MORE_IMPORTANT;
		}
		else if (mail.getSujet() != null && other.getSujet() == null) {
			return MAIL_MORE_IMPORTANT;
		}
		return mail.getSujet().compareTo(other.getSujet());
	}

	private boolean oneOfMailsNull(Mail mail, Mail other) {
		return mail == null || other == null;
	}

	private boolean differentImportance(Mail mail, Mail other) {
		return mail.isImportant() != other.isImportant();
	}

	private boolean differentStatut(Mail mail, Mail other) {
		return mail.getStatut() != other.getStatut();
	}

	private boolean differentSujet(Mail mail, Mail other) {
		if (mail.getSujet() == null || other.getSujet() == null) {
			return true;
		}
		return !mail.getSujet().equals(other.getSujet());
	}

}
