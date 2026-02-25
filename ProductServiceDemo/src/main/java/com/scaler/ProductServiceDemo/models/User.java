package com.scaler.ProductServiceDemo.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends BaseModel {
    private String username;
    private String email;
    private String password;
}
