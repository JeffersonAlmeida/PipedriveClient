package br.com.doublef.pipedriveclient.datamanager;


import com.orhanobut.hawk.Hawk;

import javax.inject.Inject;

import br.com.doublef.pipedriveclient.database.DatabaseFacade;
import br.com.doublef.pipedriveclient.model.PersonData;
import br.com.doublef.pipedriveclient.model.ResponseUser;
import br.com.doublef.pipedriveclient.model.ResponseUserData;
import br.com.doublef.pipedriveclient.model.User;
import br.com.doublef.pipedriveclient.remote.Service;
import br.com.doublef.pipedriveclient.sharedprefs.UserPrefs;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DataManager {

    private final Service service;

    private final DatabaseFacade databaseFacade;

    private final UserPrefs userPrefs;

    @Inject
    public DataManager(Service service, DatabaseFacade databaseFacade, UserPrefs userPrefs) {
        this.service = service;
        this.databaseFacade = databaseFacade;
        this.userPrefs = userPrefs;
    }

    public Observable<PersonData> fetchData(){
        return service
                .fetchDataFromRemote()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(this::saveDataOnDatabase)
                .observeOn(AndroidSchedulers.mainThread())
                .startWith(fetchDataFromDatabase());
    }

    public Observable<PersonData> fetchLocalData(){
        return Observable.just(fetchDataFromDatabase());
    }

    private PersonData fetchDataFromDatabase(){
       return databaseFacade.fetchAll();
    }

    private PersonData saveDataOnDatabase(PersonData personData) {
        databaseFacade.save(personData);
        return personData;
    }

    public Observable<ResponseUserData> login(User user){
        return Observable.defer(() -> service.login(user));
    }

    public void logout(){
        Hawk.deleteAll();
        databaseFacade.clearDatabase();
    }

    public ResponseUser getLoggedUser() {
        return userPrefs.getResponseUser();
    }

    public void saveLoggedUser(ResponseUser responseUser) {
        userPrefs.saveResponseUser(responseUser);
    }
}
