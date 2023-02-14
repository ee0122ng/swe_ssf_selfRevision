package iss.ssf.selfRevisionws03.service;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iss.ssf.selfRevisionws03.model.Contact;
import iss.ssf.selfRevisionws03.repo.ContactRepo;

@Service
public class ContactService {
    
    @Autowired
    ContactRepo contactRepo;

    public ContactRepo getContactRepo() {
        return this.contactRepo;
    }

    public void setContactRepo(ContactRepo contactRepo) {
        this.contactRepo = contactRepo;
    }

    public void addContact(Contact contact) {
        contactRepo.getContactList().add(contact);
    }

    public void writeToFile(String id) throws Exception {

        File file = new File("./contact/" + id + ".txt");

        if (!file.exists()) {
            System.out.println(">>> File created: " + file.getCanonicalPath());
        } else {
            System.out.println(">>> File already exists.");
        }

        List<Contact> ctc = contactRepo.getContactList().stream().filter(c -> c.getId().equalsIgnoreCase(id)).toList();
        if (ctc.size() != 0) {
            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(file);
            pw.print(ctc.get(0).toString());
            pw.flush();
            pw.close();
            fw.close();
            System.out.println("File for " + id + " created");
        } else {
            System.out.println(">>> Id not found!");
        }

    }

    public List<Contact> getAllContactList() {

        return this.contactRepo.getContactList();
    }
    
}
