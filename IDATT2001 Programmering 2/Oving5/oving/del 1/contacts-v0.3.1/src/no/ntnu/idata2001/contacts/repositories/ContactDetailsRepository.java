package no.ntnu.idata2001.contacts.repositories;

import no.ntnu.idata2001.contacts.model.ContactDetails;


import javax.persistence.EntityManager;
import java.util.List;

public class ContactDetailsRepository extends AbstractRepository<ContactDetails, Integer> {

     public ContactDetailsRepository(EntityManager entityManager) {
          super(entityManager);
          setEntityClass(ContactDetails.class);
     }
     @Override
     public List<ContactDetails> findAll(){
          return entityManager.createQuery("select e from ContactDetails e",ContactDetails.class)
                  .getResultList();
     }
}
