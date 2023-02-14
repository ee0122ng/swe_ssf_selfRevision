package iss.ssf.selfRevisionws03.repo;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import iss.ssf.selfRevisionws03.model.Contact;

@Repository
public class ContactRepo {

    private List<Contact> contactList;

    public ContactRepo() {
        if (null == contactList) {
            this.contactList = new LinkedList<Contact>();
        }
    }

    public ContactRepo(Contact contact) {

        if (null == this.contactList) {
            this.contactList = new LinkedList<Contact>();
        }
        this.contactList.add(contact);
    }

    public List<Contact> getContactList() {
        return this.contactList;
    }

    public void setContactList(List<Contact> contact) {
        this.contactList = contact;
    }

    public boolean getContactById(String id) {

        boolean idExist = false;

        if (null != this.contactList.stream().filter(c -> c.getId().equalsIgnoreCase(id)).toList().get(0)) {
            idExist = true;
        }

        return idExist;
    }
    
}
