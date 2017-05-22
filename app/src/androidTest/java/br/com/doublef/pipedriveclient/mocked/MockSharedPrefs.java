package br.com.doublef.pipedriveclient.mocked;


import android.content.Context;

import br.com.doublef.pipedriveclient.model.ResponseUser;
import br.com.doublef.pipedriveclient.sharedprefs.UserPrefs;

public class MockSharedPrefs implements UserPrefs {

    private Context context;

    public MockSharedPrefs(Context context) {
        this.context = context;
    }

    @Override
    public ResponseUser getResponseUser() {

        ResponseUser responseUser = new ResponseUser();
        responseUser.setId(11111);
        responseUser.setCompanyId(22222);
        responseUser.setApiToken("Empty");

        return responseUser;
    }

    @Override
    public void saveResponseUser(ResponseUser responseUser) {

    }
}
