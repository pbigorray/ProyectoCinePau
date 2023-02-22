package com.example.proyectocinepau.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectocinepau.R;
import com.example.proyectocinepau.db.DataBase;
import com.example.proyectocinepau.db.controller.UserController;
import com.example.proyectocinepau.model.tipos.Rol;
import com.example.proyectocinepau.model.User;

import io.realm.Realm;


public class RegisterActivity extends AppCompatActivity {
    EditText userText,dniText,nombreText,apellidosText,passText,conPassText;
    private Button register,login;

    Realm con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userText=findViewById(R.id.userText);
        nombreText=findViewById(R.id.nombre);
        apellidosText=findViewById(R.id.apellidos);
        dniText=findViewById(R.id.dni);
        passText=findViewById(R.id.passText);
        conPassText=findViewById(R.id.conPassText);
        login=findViewById(R.id.login);
        register=findViewById(R.id.registro);

        login.setOnClickListener(view -> {
            Intent i = new Intent(this,LoginActivity.class);
            startActivity(i);
        });

        register.setOnClickListener(view -> {
            String pass,conPass,user,nombre,apellidos,dni;
            if (userText.length()<1){
                Toast.makeText(this, "El usuario no puede estar vacio", Toast.LENGTH_SHORT).show();
            } else if (nombreText.length()<1) {
                Toast.makeText(this, "El nombre no puede estar vacio", Toast.LENGTH_SHORT).show();

            } else if (apellidosText.length()<1) {
                Toast.makeText(this, "Los apellidos no puede estar vacios", Toast.LENGTH_SHORT).show();

            } else if (dniText.length()<1) {
                Toast.makeText(this, "El DNI no puede estar vacio", Toast.LENGTH_SHORT).show();

            } else if (passText.length()<1) {
                Toast.makeText(this, "La contraseña no puede estar vacia", Toast.LENGTH_SHORT).show();

            }else if (conPassText.length()<1) {
                Toast.makeText(this, "La confirmacion de contraseña no puede estar vacia", Toast.LENGTH_SHORT).show();
            }else {
                user=userText.getText().toString();
                pass=passText.getText().toString();
                conPass=conPassText.getText().toString();
                nombre=nombreText.getText().toString();
                apellidos=apellidosText.getText().toString();
                dni=dniText.getText().toString();

                if (pass.equals(conPass)){
                    User newUser = new User();
                    newUser.setUsuario(user);
                    newUser.setNombre(nombre);
                    newUser.setApellidos(apellidos);
                    newUser.setDNI(dni);
                    newUser.setPass(pass);
                    newUser.setRol(Rol.USER.getNum());

                    UserController userController = new UserController(this);
                    userController.addUser(newUser);
                    Toast.makeText(this, "El usuario se añadido correctamente", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Las contraseñan no coinciden", Toast.LENGTH_SHORT).show();
                }
                Intent i = new Intent(this,LoginActivity.class);
                startActivity(i);
            }
        });
    }
}