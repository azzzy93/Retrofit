package kg.geektech.retrofit;

import android.app.Application;

import kg.geektech.retrofit.data.remote.HerokuApi;
import kg.geektech.retrofit.data.remote.RetrofitClient;

public class App extends Application {
    public static HerokuApi api;

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitClient client = new RetrofitClient();
        api = client.provideApi();
    }
}
