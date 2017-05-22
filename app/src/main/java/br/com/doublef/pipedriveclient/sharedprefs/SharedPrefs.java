package br.com.doublef.pipedriveclient.sharedprefs;


import android.content.Context;

import com.orhanobut.hawk.Hawk;

import br.com.doublef.pipedriveclient.model.ResponseUser;
import br.com.doublef.pipedriveclient.util.Constants;

public class SharedPrefs implements UserPrefs {

    private Context context;

    public SharedPrefs(Context context) {
        this.context = context;
    }

    @Override
    public ResponseUser getResponseUser(){
        return Hawk.get(Constants.RESPONSE_USER);
    }

    @Override
    public void saveResponseUser(ResponseUser responseUser){
        Hawk.put(Constants.RESPONSE_USER, responseUser);
    }
}
