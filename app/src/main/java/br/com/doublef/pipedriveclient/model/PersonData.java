package br.com.doublef.pipedriveclient.model;


import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PersonData {

    private boolean success;

    private List<Contact> data;

}
