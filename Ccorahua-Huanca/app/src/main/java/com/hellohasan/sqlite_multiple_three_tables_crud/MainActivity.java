package com.hellohasan.sqlite_multiple_three_tables_crud;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText user, pass;
    Button btnEntrar,btnRegistrar;
    daoUsuario dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        user=(EditText)findViewById(R.id.User);
        pass=(EditText)findViewById(R.id.Pass);
        btnEntrar=(Button)findViewById(R.id.btnEntrar);
        btnRegistrar=(Button)findViewById(R.id.btnRegistrar);

        btnEntrar.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);
        dao=new daoUsuario(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEntrar:
                String u=user.getText().toString();
                String p=user.getText().toString();
                if(u.equals("")&&p.equals("")){
                    Toast.makeText(this,"ERROR:Campos vacios",Toast.LENGTH_LONG).show();

                }else if(dao.login(u,p)==1){
                    Usuario ux=dao.getUsuario(u,p);
                    Toast.makeText(this,"Datos correctos",Toast.LENGTH_LONG).show();
                    Intent i2=new Intent(MainActivity.this,Inicio.class);
                    i2.putExtra("Id",ux.getId());
                    startActivity(i2);
                    finish();
                }
                break;
            case R.id.btnRegistrar:
                Intent i=new Intent(MainActivity.this,Reagistrar.class);
                startActivity(i);

                break;
        }
    }
}
