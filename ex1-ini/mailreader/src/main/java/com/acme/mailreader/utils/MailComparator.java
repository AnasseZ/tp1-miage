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
	
	public final static int IMPOSSIBLE_COMPARAISON = 0;
	public final static int MAIL_MORE_IMPORTANT = -1;
	public final static int OTHER_MORE_IMPORTANT = 1;
	
	public int compare(Mail mail, Mail other) {
		if (mail == null || other == null) {
			return IMPOSSIBLE_COMPARAISON;
		}
		if (mail.isImportant() != other.isImportant()) {
			if (mail.isImportant() && !other.isImportant()) {
				return MAIL_MORE_IMPORTANT;
			} 
			
			return OTHER_MORE_IMPORTANT;		
		}
		if (mail.getStatut() != other.getStatut()) {
			int comp = mail.getStatut().ordinal()
					- other.getStatut().ordinal();
			return comp > IMPOSSIBLE_COMPARAISON ? MAIL_MORE_IMPORTANT : OTHER_MORE_IMPORTANT;
		}
		if (mail.getSujet() != other.getSujet()) {
			return other.getSujet().compareTo(mail.getSujet());
		}
		return other.getDate().compareTo(mail.getDate());
	}
	

}
