package poojan.testexam.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import poojan.testexam.domain.Contact;
import poojan.testexam.domain.PhoneType;
import poojan.testexam.dto.CallItem;
import poojan.testexam.repo.ContactRepo;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContactService {

    final ContactRepo contactRepo;

    public List<Contact> findAll() {
        return contactRepo.findAll();
    }

    public Optional<Contact> findById(Long id) {
        return contactRepo.findById(id);
    }

    public Contact save(Contact c) {
        return contactRepo.save(c);
    }

    public void delete(Long id) {
        contactRepo.deleteById(id);
    }

    public List<CallItem> findCallList() {
        return contactRepo.findAllByPhoneType(PhoneType.home);
    }


}
