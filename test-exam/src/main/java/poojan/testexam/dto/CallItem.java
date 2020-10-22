package poojan.testexam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import poojan.testexam.domain.Name;

@Data
@AllArgsConstructor
public class CallItem {
    Name name;
    String phone;

    public CallItem(String first, String middle, String last, String phone) {
        this.name = new Name(first, middle, last);
        this.phone = phone;
    }
}
