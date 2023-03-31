package com.example.foodbank;

import static com.example.foodbank.Constants.CONTENT_TYPE;
import static com.example.foodbank.Constants.SERVER_KEY;

import com.example.foodbank.PushNotification;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {


    @Headers({"Authorization: key="+SERVER_KEY,"Content-Type:"+CONTENT_TYPE})
    @POST("fcm/send")
    Call<PushNotification> sendNotifications(@Body PushNotification notification);
}
