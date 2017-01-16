package com.example.yoann.localisation;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by YoannO on 15/01/2017.
 */

public interface GetInfo { //interface permettant l'interraction avec la BDD

   /** @GET("")
    Call<Test> all();

    @GET("")
    Call<Todo> select(@Path("iduser") int id);
    **/

    @FormUrlEncoded //pour poster des infos sur le serveur
    @POST("") //pour diriger le code vers l'url cible
    Call<Todo> serverCall(
            @Field("iduser") int idUser,
            @Field("idpicture") int idPicture,
            @Field("login") String login,
            @Field("password") String Password);

    //@FIELD pour selectionner les champs dans lesquels on doit écrire une information
}
