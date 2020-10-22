package poojan.testexam;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import poojan.testexam.domain.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApiTest {
    @Autowired
    MockMvc mvc;

    @org.junit.jupiter.api.Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testCrud() throws Exception {
        Contact c = dummyContact();

        mvc.perform(post("/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(c)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.email").value(c.getEmail()));

        mvc.perform(get("/contacts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.email").value(c.getEmail()));


        mvc.perform(delete("/contacts/1"))
                .andExpect(status().isOk());

    }


    @org.junit.jupiter.api.Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testCallList() throws Exception {
        Contact c = dummyContact();
        Contact c2= dummyContact2();

        mvc.perform(post("/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(c)))
                .andExpect(status().isOk());


        mvc.perform(post("/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(c2)))
                .andExpect(status().isOk());

        mvc.perform(get("/contacts/call-list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$.[0].phone").value("507-497-1737"));


    }


    public static byte[] convertObjectToJsonBytes(Object object)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsBytes(object);
    }

    public static Contact dummyContact() {
        Contact c = new Contact();
        c.setEmail("poojan.pradhan22@gmail.com");

        Name name = new Name();
        name.setFirst("Poojan");
        name.setLast("Pradhan");
        c.setName(name);

        Set<Phone> phones = new HashSet<>();
        Phone homePhone = new Phone();
        homePhone.setNumber("507-497-1737");
        homePhone.setType(PhoneType.home);

        Phone workPhone = new Phone();
        workPhone.setNumber("507-111-1111");
        workPhone.setType(PhoneType.work);
        phones.add(homePhone);
        phones.add(workPhone);

        c.setPhone(phones);

        Address address = new Address();
        address.setStreet("9860 Oakdale Woods");
        address.setCity("Vienna");
        address.setState("Virginia");
        address.setZip("22181");
        c.setAddress(address);

        return c;
    }

    public static Contact dummyContact2() {
        Contact c = new Contact();
        c.setEmail("poojan.pradhan22@gmail.com");

        Name name = new Name();
        name.setFirst("Poojan");
        name.setLast("Pradhan");
        c.setName(name);

        Set<Phone> phones = new HashSet<>();
        Phone homePhone = new Phone();
        homePhone.setNumber("507-497-1737");
        homePhone.setType(PhoneType.work);

        c.setPhone(phones);

        return c;
    }
}
