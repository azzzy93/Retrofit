package kg.geektech.retrofit.data.remote;


import java.util.List;

import kg.geektech.retrofit.models.Post;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HerokuApi {

    @GET("/posts")
    Call<List<Post>> getPosts(@Query("group") Integer group);

    @POST("/posts")
    Call<Post> setPost(@Body Post post);

    @DELETE("/posts/{id}")
    Call<Post> deletePost(@Path("id") Integer id);

    @PUT("/posts/{id}")
    Call<Post> updatePost(@Path("id") Integer id, @Body Post post);

}
