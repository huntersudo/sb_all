package com.project.retrofit.main;

import com.project.retrofit.rest.LocalEndPoint;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author sml
 * created on  2018/10/30
 */
@Slf4j
public class LocalService {

    private volatile Retrofit localRetrofit;

    private volatile LocalEndPoint localEndPoint;

    public LocalService() {

        if (localRetrofit == null) {
            localRetrofit = new Retrofit.Builder()
                    .baseUrl("http://localhost:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        if (localEndPoint == null) {
            localEndPoint = localRetrofit.create(LocalEndPoint.class);
        }
    }

    public String getUserById(Integer id)throws Exception {

        return localEndPoint.getUserById(id).execute().body()
                .getData().toString();
    }

    public static void main(String[] args) throws Exception {

        try {
            LocalService localService = new LocalService();
//            UserDto user = localService.getUserById(1);
            String user = localService.getUserById(1);
            log.info(user);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }



    }

}
