package iss.ssf.selfRevisionws03.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import iss.ssf.selfRevisionws03.model.Contact;
import iss.ssf.selfRevisionws03.repo.ContactRepo;
import iss.ssf.selfRevisionws03.service.ContactService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ContactController {

    @Autowired
    ContactService ctcSvc;

    @GetMapping(path={"/"})
    public String getContact(Model model, HttpSession session) {

        ContactRepo contactRepo = (ContactRepo) session.getAttribute("contactRepo");
        if (null == contactRepo) {
            session.setAttribute("contactRepo", new ContactRepo());
        }

        model.addAttribute("contactRepo", contactRepo);
        model.addAttribute("contact", new Contact());
        
        return "contact";
    }

    @PostMapping(path={"/contact"})
    public String addContact(@Valid Contact contact, BindingResult result, Model model, HttpSession session) throws Exception{

        ContactRepo contactRepo = (ContactRepo) session.getAttribute("contactRepo");

        if (result.hasErrors()) {
            model.addAttribute("contact", contact);
            model.addAttribute("contactRepo", contactRepo);
            return "contact";
        }

        // add the new contact to current session
        contactRepo.getContactList().add(contact);

        List<Contact> ctc = contactRepo.getContactList().stream().filter(c -> c.getId().equalsIgnoreCase(contact.getId())).toList();
        // System.out.println(ctc.toString());

        // write to file if id match
        if (ctc.size() != 0) {
            String id = ctc.get(0).getId();
            ctcSvc.setContactRepo(contactRepo);
            ctcSvc.writeToFile(id);
        }

        // add the new contact to central depository
        // ctcSvc.addContact(contact);

        model.addAttribute("contact", contact);
        model.addAttribute("contactRepo", contactRepo);
        model.addAttribute("contactList", ctc);
        
        return "contactList";
    }

    
    @GetMapping(path={"/contactList"})
    public String getAllContactList(Model model, HttpSession session) {

        ContactRepo ctcRepo = (ContactRepo) session.getAttribute("contactRepo");

        // ctcRepo could be null for a new session
        if (null == ctcRepo) {
            session.setAttribute("contactRepo", new ContactRepo());
            ctcRepo = (ContactRepo) session.getAttribute("contactRepo");
        }
        System.out.println(ctcRepo.toString());

        List<Contact> ctc = ctcRepo.getContactList();

        model.addAttribute("contactList", ctc);

        return "contactList";
    }

    @GetMapping(path={"/checkout"})
    public String removeSession(HttpSession session) {

        session.removeAttribute("contactRepo");
        session.invalidate();

        return "checkout";
    }

    @GetMapping(path={"/contact/{id}"})
    public String getContactById(@PathVariable("id") String id, Model model, HttpSession session) {

        ContactRepo ctcRepo = (ContactRepo) session.getAttribute("contactRepo");

        if (ctcRepo.getContactById(id)) {
            List<Contact> ctc = ctcRepo.getContactList().stream().filter(c -> c.getId().equalsIgnoreCase(id)).toList();
            model.addAttribute("contactList", ctc);
            return "contactList";
        }

        return "error";
    }
    
    @GetMapping(path={"/allContact"})
    public String displayAllContacts(Model model, HttpSession session) {

        ContactRepo ctcRepo = (ContactRepo) session.getAttribute("contactRepo");

        List<Contact> ctc = ctcRepo.getContactList();

        model.addAttribute("contacts", ctc);

        return "contacts";
    }
}
