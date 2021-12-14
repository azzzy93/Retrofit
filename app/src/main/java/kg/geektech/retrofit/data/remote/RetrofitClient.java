package kg.geektech.retrofit.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://android-3-mocker.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public HerokuApi provideApi() {
        return retrofit.create(HerokuApi.class);
    }
}
