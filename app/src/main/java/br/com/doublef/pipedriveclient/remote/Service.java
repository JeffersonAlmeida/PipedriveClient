package br.com.doublef.pipedriveclient.remote;


import java.util.List;

import br.com.doublef.pipedriveclient.model.PersonData;
import br.com.doublef.pipedriveclient.model.Post;
import br.com.doublef.pipedriveclient.model.ResponseUserData;
import br.com.doublef.pipedriveclient.model.User;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface Service {

    @GET("persons")
    Observable<PersonData> fetchDataFromRemote();

    @GET("posts")
    Observable<List<Post>> fetchPostsData();

    @POST("authorizations")
    Observable<ResponseUserData> login(@Body User user);

}
