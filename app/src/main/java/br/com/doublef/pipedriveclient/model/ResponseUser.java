package br.com.doublef.pipedriveclient.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ResponseUser {

    private int id;

    @SerializedName("company_id")
    private int companyId;

    @SerializedName("api_token")
    private String apiToken;

}
