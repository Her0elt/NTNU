package no.ntnu.idata2001.contacts.handlers;

import no.ntnu.idata2001.contacts.model.ContactDetails;

import java.util.Collection;
import java.util.Iterator;

public interface AdressBook extends Iterable<ContactDetails> {
     void addContact(ContactDetails contact);
     void removeContact(ContactDetails contactDetails);
     Collection<ContactDetails> getAllContacts();
     Iterator<ContactDetails> iterator();
     void close();
}
