package com.babenkovladimir.composite_application_x.abdroid_base_materials.retrofit;

import com.babenkovladimir.composite_application_x.abdroid_base_materials.retrofit.pojo.MultipleResource;
import com.babenkovladimir.composite_application_x.abdroid_base_materials.retrofit.pojo.User;
import com.babenkovladimir.composite_application_x.abdroid_base_materials.retrofit.pojo.UserList;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

interface APIInterface {

  @GET("/api/unknown")
  Call<MultipleResource> doGetListResources();

  @POST("/api/users")
  Call<User> createUser(@Body User user);

  @GET("/api/users?")
  Call<UserList> doGetUserList(@Query("page") String page);

  @FormUrlEncoded
  @POST("/api/users?")
  Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);
}