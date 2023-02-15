package iss.ssf.selfRevisionws04.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import iss.ssf.selfRevisionws04.model.Contact;
import iss.ssf.selfRevisionws04.repo.ContactRedis;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ContactController {

    @Autowired
    ContactRedis ctcRedis;
    
    @GetMapping(path={"/"})
    public String getForm(Model model, HttpSession session) {

        Contact ctc = (Contact) session.getAttribute("contact");
        if (null == ctc) {
            session.setAttribute("contact", new Contact());
        }

        model.addAttribute("contact", ctc);
        
        return "contactForm";
    }

    @PostMapping(path={"/contact"})
    public String addContact(@Valid Contact contact, BindingResult result, Model model, HttpSession session) {

        Contact ctc = (Contact) session.getAttribute("contact");

        if (result.hasErrors()) {
            // model.addAttribute("contact", new Contact());
            return "contactForm";
        }

        ctcRedis.saveContact(contact);

        // contain the Contact inside a list
        List<Contact> ctcList = new LinkedList<Contact>();
        ctcList.add(ctcRedis.getContactById(contact.getId()));

        model.addAttribute("contacts", ctcList);

        return "contactList";
    }

    @GetMapping(path={"/contactList"})
    public String retrieveDatabaseContacts(Model model, HttpSession session) {

        List<Contact> ctcList = ctcRedis.findAll(0);

        model.addAttribute("contacts", ctcList);

        return "contactList";
    }
    
    @GetMapping(path={"/sessionContactList"})
    public String retrieveSessionContacts(Model model, HttpSession session) {

        List<Contact> ctcList = new LinkedList<Contact>();

        Contact ctc = (Contact) session.getAttribute("contact");
        ctcList.add(ctc);

        model.addAttribute("contacts", ctcList);


        return "contactList";
    }

    @GetMapping(path={"/checkout"})
    public String killSession(HttpSession session) {

        session.removeAttribute("contact");
        session.invalidate();

        return "checkout";

    }

    // @GetMapping(path={"/clearALL"})
    // public String clearAllContacts() {

    //     ctcRedis.deleteAll();

    //     return "clearAll";
    // }

}
