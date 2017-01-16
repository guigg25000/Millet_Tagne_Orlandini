package com.example.yoann.localisation;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Test { //Classe générée a l'aide du site http://www.jsonschema2pojo.org/

@SerializedName("todos")
@Expose
private List<Todo> todos = null;

public List<Todo> getTodos() {
return todos;
}

public void setTodos(List<Todo> todos) {
this.todos = todos;
}

}