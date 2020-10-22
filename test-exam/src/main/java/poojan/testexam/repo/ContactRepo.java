package poojan.testexam.repo;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import poojan.testexam.domain.Contact;
import poojan.testexam.domain.PhoneType;
import poojan.testexam.dto.CallItem;

import java.util.List;
import java.util.Optional;

public interface ContactRepo extends JpaRepository<Contact, Long> {
    @Override
    @EntityGraph(attributePaths = {"phone"})
    List<Contact> findAll();

    @Override
    @EntityGraph(attributePaths = {"phone"})
    Optional<Contact> findById(Long aLong);

    @Query("select new poojan.testexam.dto.CallItem(c.name.first, c.name.middle, c.name.last, p.number)  from Contact c left  join c.phone p where p.type = ?1 order by c.name.last, c.name.first")
    List<CallItem> findAllByPhoneType(PhoneType type);
}
