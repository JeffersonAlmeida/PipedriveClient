package br.com.doublef.pipedriveclient.sharedprefs;


import br.com.doublef.pipedriveclient.model.ResponseUser;

public interface UserPrefs {

    ResponseUser getResponseUser();

    void saveResponseUser(ResponseUser responseUser);

}
