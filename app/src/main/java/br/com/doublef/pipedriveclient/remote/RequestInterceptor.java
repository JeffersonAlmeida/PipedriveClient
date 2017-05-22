package br.com.doublef.pipedriveclient.remote;

import java.io.IOException;

import br.com.doublef.pipedriveclient.model.ResponseUser;
import br.com.doublef.pipedriveclient.sharedprefs.UserPrefs;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {

    private static final String KEY = "api_token";

    private final UserPrefs userPrefs;

    public RequestInterceptor(UserPrefs userPrefs) {
        this.userPrefs = userPrefs;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        if ( isUserLogged() ) {

            String apiToken = getApiToken();

            HttpUrl httpUrl = request
                    .url()
                    .newBuilder()
                    .addQueryParameter(KEY, apiToken)
                    .build();

            request = request
                    .newBuilder()
                    .url(httpUrl)
                    .build();
        }



        return chain.proceed(request);
    }

    private boolean isUserLogged(){
        ResponseUser responseUser = userPrefs.getResponseUser();
        return responseUser != null && !responseUser.getApiToken().isEmpty();
    }

    private String getApiToken(){
        ResponseUser responseUser = userPrefs.getResponseUser();
        return responseUser.getApiToken();
    }
}
