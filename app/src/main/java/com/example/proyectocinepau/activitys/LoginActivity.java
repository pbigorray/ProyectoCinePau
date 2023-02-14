package com.example.proyectocinepau.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectocinepau.MainActivity;
import com.example.proyectocinepau.R;
import com.example.proyectocinepau.db.DataBase;
import com.example.proyectocinepau.model.User;

import io.realm.Realm;


public class LoginActivity extends AppCompatActivity {
    private Button login,register;
    EditText userText, passText;

    Realm con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userText =findViewById(R.id.userText);
        passText =findViewById(R.id.passText);
        login=findViewById(R.id.login);

        con= DataBase.getInstance().connection(this);
        long users= con.where(User.class).count();
        if (users==0){
            User user= new User();
            user.setDNI("admin");
            user.setNombre("admin");
            user.setUsuario("admin");
            user.setPass("admin");
            user.setRol(3);

            con.beginTransaction();
            con.copyToRealmOrUpdate(user);
            con.commitTransaction();

        }
        login.setOnClickListener(view -> {
            String pass,user;
            user=userText.getText().toString();
            pass=passText.getText().toString();

            User userLog=con.where(User.class).equalTo("usuario",user).findFirst();
            if(userLog==null){
                Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
            }else {
                if(userLog.getPass().equals(pass)){
                        Intent i= new Intent(this, MainActivity.class);
                        startActivity(i);
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