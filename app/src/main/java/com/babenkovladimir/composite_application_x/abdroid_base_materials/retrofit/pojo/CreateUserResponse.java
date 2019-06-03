package com.babenkovladimir.composite_application_x.abdroid_base_materials.retrofit.pojo;

import com.google.gson.annotations.SerializedName;


public class CreateUserResponse {

  @SerializedName("name")
  public String name;
  @SerializedName("job")
  public String job;
  @SerializedName("id")
  public String id;
  @SerializedName("createdAt")
  public String createdAt;
}
