package com.project.retrofit.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LocalEndPoint {


    @GET(value = "/user/{id}")
    Call<Result> getUserById(@Path("id") Integer id);
}
