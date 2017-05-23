package br.com.doublef.pipedriveclient.dependencyinjection.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.doublef.pipedriveclient.BuildConfig;
import br.com.doublef.pipedriveclient.remote.RequestInterceptor;
import br.com.doublef.pipedriveclient.remote.RxErrorHandlingCallAdapterFactory;
import br.com.doublef.pipedriveclient.remote.Service;
import br.com.doublef.pipedriveclient.sharedprefs.UserPrefs;
import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RestApiModule {

    private static final String BASE_URL = BuildConfig.ENDPOINT;

    @Provides
    Interceptor interceptor(UserPrefs userPrefs){
        return new RequestInterceptor(userPrefs);
    }

    @Provides
    Service service(Interceptor interceptor) {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(httpLoggingInterceptor);

        httpClient.addInterceptor(interceptor);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(httpClient.build())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                        .build();

        return retrofit.create(Service.class);
    }

}
