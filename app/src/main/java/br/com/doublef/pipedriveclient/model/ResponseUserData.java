package br.com.doublef.pipedriveclient.model;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ResponseUserData {

    private List<ResponseUser> data;

}
