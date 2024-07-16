package com.mycompany.appBackend.models;

import java.util.Stack;

public interface BaseModelInterface<TheModel> {
    Boolean create();
    void update();
    void delete();
    Stack<TheModel> view();
    TheModel viewById(int id);
}
