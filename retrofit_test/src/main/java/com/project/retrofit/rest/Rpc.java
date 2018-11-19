package com.project.retrofit.rest;//package comn.project.retrofit2.com.project.retrofit.rest;
//
//import com.google.gson.JsonObject;
//import org.slf4j.Logger;
//import retrofit2.Call;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//import java.io.IOException;
//
//public class Rpc {
//
//    public boolean getMess(JsonObject jsonObject, String restIp, Logger logger) throws IOException {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(restIp)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        LocalEndPoint retrofit2 = retrofit.create(LocalEndPoint.class);
//        Call<JsonObject> message = retrofit2.getUserById(1);
//
//        logger.info(String.format("获取到的信息为 %s", message));
//
//        return processBooleanResponse(message, logger);
//    }
//
//    private boolean processBooleanResponse(Call<JsonObject> call, Logger logger) {
//        Response<JsonObject> result = null;
//        try {
//            result = call.execute();
//            if (!result.isSuccessful()) {
//                logger.error("rpc调用失败,可能是http header不合法,errorBody:{} ,raw:{},message:{},body:{}!", result.errorBody(), result.raw(), result.message(), result.body());
//            }
//        } catch (IOException e) {
//            logger.error("rpc远程调用失败{}", e.getMessage(), e);
//        }
//        return result.body().get("data").getAsJsonObject().get("result").getAsBoolean();
//    }
//}
