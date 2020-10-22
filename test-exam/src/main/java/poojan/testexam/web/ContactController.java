package poojan.testexam.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poojan.testexam.domain.Contact;
import poojan.testexam.dto.CallItem;
import poojan.testexam.service.ContactService;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/contacts")
public class ContactController {

    final ContactService contactService;

    @GetMapping({"/", ""})
    ResponseEntity<List<Contact>> getContacts() {
        return ok(contactService.findAll());
    }

    @PostMapping
    ResponseEntity<Contact> newContact(@RequestBody Contact c) {
        return ok(contactService.save(c));
    }

    @GetMapping("/{id}")
    ResponseEntity<Contact> getContact(@PathVariable Long id) {
        return of(contactService.findById(id));
    }

    @GetMapping("/call-list")
    ResponseEntity<List<CallItem>> getCallList() {
        return ok(contactService.findCallList());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        contactService.delete(id);
        return ok().build();
    }

    @PutMapping("/{id}")
    ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody Contact c) {
        if (!c.getId().equals(id)) {

            badRequest();
        }

        return ok(contactService.save(c));
    }

}
