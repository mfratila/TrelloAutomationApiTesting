package utilities.retrofit_utilities;

import models.TrelloBoard;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ApiService {
    @GET("/1/boards/{id}")
    Call<TrelloBoard> getBoardById(
            @Path("id") String id,
            @Query("key") String key,
            @Query("token") String token);

    @GET("/1/members/me/boards")
    Call<List<TrelloBoard>> getAllBoards(
            @Query("key") String key,
            @Query("token") String token);

    @POST("/1/boards/")
    Call<TrelloBoard> createBoard(
            @Query("key") String key,
            @Query("token") String token,
            @Body TrelloBoard board);

    @PUT("/1/boards/{id}")
    Call<TrelloBoard> updateBoard(
            @Path("id") String id,
            @Query("key") String key,
            @Query("token") String token,
            @Body TrelloBoard board);

    @DELETE("/1/boards/{id}")
    Call<Void> deleteBoard(
            @Path("id") String id,
            @Query("key") String key,
            @Query("token") String token);
}

