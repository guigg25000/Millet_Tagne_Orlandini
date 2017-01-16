package com.example.yoann.localisation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Todo { //Code généré a l'aide du site http://www.jsonschema2pojo.org/

@SerializedName("iduser")
@Expose
private String iduser;
@SerializedName("idpicture")
@Expose
private String idpicture;
@SerializedName("login")
@Expose
private String login;
@SerializedName("password")
@Expose
private String password;

public String getIduser() {
return iduser;
}

public void setIduser(String iduser) {
this.iduser = iduser;
}

public String getIdpicture() {
return idpicture;
}

public void setIdpicture(String idpicture) {
this.idpicture = idpicture;
}

public String getLogin() {
return login;
}

public void setLogin(String login) {
this.login = login;
}

public String getPassword() {
return password;
}

public void setPassword(String password) {
this.password = password;
}

}