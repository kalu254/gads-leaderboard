package com.kalu.leaderboard.FetchData;

import org.jetbrains.annotations.NotNull;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {

    private static final String LEADER_URL = "https://gadsapi.herokuapp.com/api/";
    private static final String SUBMIT_URL = "https://docs.google.com/forms/d/e/";

    private static HttpLoggingInterceptor logger = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient.Builder okHttp = new OkHttpClient.Builder().addInterceptor(logger);



    private static Retrofit.Builder lBUILDER = new Retrofit.Builder().baseUrl(LEADER_URL)
        .addConverterFactory(GsonConverterFactory.create()).client(okHttp.build());

    private static Retrofit lRetrofit = lBUILDER.build();

    public static <S> S leaderBuildService(Class<S> serviceType){
        return lRetrofit.create(serviceType);
    }

    private static Retrofit.Builder sBuilderBUILDER = new Retrofit.Builder().baseUrl(SUBMIT_URL)
        .addConverterFactory(GsonConverterFactory.create()).client(okHttp.build());

    private static Retrofit sRetrofit = sBuilderBUILDER.build();

    @NotNull
    public static <S> S submitBuildService(Class<S> serviceType){
        return sRetrofit.create(serviceType);
    }

}
