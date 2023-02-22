package com.example.proyectocinepau.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.proyectocinepau.MainActivity;
import com.example.proyectocinepau.R;
import com.example.proyectocinepau.db.DataBase;
import com.example.proyectocinepau.db.controller.UserController;
import com.example.proyectocinepau.model.User;
import com.example.proyectocinepau.model.tipos.Rol;

import io.realm.Realm;


public class LoginActivity extends AppCompatActivity {
    private Button login,register;
    EditText userText, passText;

    private UserController userController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userText =findViewById(R.id.userText);
        passText =findViewById(R.id.passText);
        login=findViewById(R.id.login);
        userController=new UserController(this);


        login.setOnClickListener(view -> {
            String pass,user;
            user=userText.getText().toString();
            pass=passText.getText().toString();

//            User userLog=con.where(User.class).equalTo("usuario",user).findFirst();
            User userLog=userController.findUser(user);
            if(userLog==null){
                Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
            }else {
                if(userLog.getPass().equals(pass)){
                        Intent i= new Intent(this, CarteleraActivity.class);
                        i.putExtra("dni",userLog.getDNI());
                        i.putExtra("onCartelera",true);
                        startActivity(i);
                        finish();
                }else {
                    Toast.makeText(this, "La contraseña no coniciden", Toast.LENGTH_SHORT).show();
                }
            }
        });
        register=findViewById(R.id.register1);
        register.setOnClickListener(view -> {
            Intent intent = new Intent(this,RegisterActivity.class);
            startActivity(intent);
        });
    }

}