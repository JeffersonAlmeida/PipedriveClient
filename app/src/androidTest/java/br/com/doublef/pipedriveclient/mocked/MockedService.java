package br.com.doublef.pipedriveclient.mocked;

import java.util.ArrayList;
import java.util.List;

import br.com.doublef.pipedriveclient.TestSupport;
import br.com.doublef.pipedriveclient.model.PersonData;
import br.com.doublef.pipedriveclient.model.ResponseUser;
import br.com.doublef.pipedriveclient.model.ResponseUserData;
import br.com.doublef.pipedriveclient.model.User;
import br.com.doublef.pipedriveclient.remote.Service;
import retrofit2.http.Body;
import rx.Observable;

public class MockedService implements Service {

    @Override
    public Observable<PersonData> fetchDataFromRemote() {
        return new TestSupport().createBiggerPersonData();
    }

    @Override
    public Observable<ResponseUserData> login(@Body User user) {
        ResponseUserData responseUserData = new ResponseUserData();
        List<ResponseUser> responseUserList = new ArrayList<>();
        ResponseUser responseUser = new ResponseUser();
        responseUser.setId(11111);
        responseUser.setCompanyId(22222);
        responseUser.setApiToken("Empty");
        responseUserData.setData(responseUserList);
        return Observable.just(responseUserData);
    }
}
