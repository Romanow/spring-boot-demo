package ru.digitalhabbits.spring.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AddressInfo {
    private String city;
    private String street;
    private String building;
}
