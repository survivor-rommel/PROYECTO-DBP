package com.hellohasan.sqlite_multiple_three_tables_crud;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hellohasan.sqlite_multiple_three_tables_crud.features.student_crud.student_list_show.StudentListActivity;

public class Inicio extends AppCompatActivity implements View.OnClickListener  {
    Button btnEditar ,btnEliminar,btnMostrar,btnSalir;
    TextView nombre;
    int id=0;
    Usuario u;
    daoUsuario dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);

        nombre=(TextView)findViewById(R.id.nombreUsuario);
        btnEditar=(Button)findViewById(R.id.btnEditar);
        btnEliminar=(Button)findViewById(R.id.btnEliminar);
        btnMostrar=(Button)findViewById(R.id.btnMostrar);
        btnSalir=(Button)findViewById(R.id.btnSalir);

        btnEditar.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
        btnMostrar.setOnClickListener(this);
        btnSalir.setOnClickListener(this);

        Bundle b=getIntent().getExtras();
        id=b.getInt("Id");
        dao=new daoUsuario(this);
        u=dao.getUsuarioById(id);
        nombre.setText(u.getNombre()+" "+u.getApellidos());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.btnEditar:
                Intent a=new Intent(Inicio.this,Editar.class);
                a.putExtra("Id",id);
                startActivity(a);
                break;
            case  R.id.btnEliminar:
                AlertDialog.Builder b=new AlertDialog.Builder(this);
                b.setMessage("Estas seguro de eliminar este usuario...");
                b.setCancelable(false);
                b.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(dao.deleteUsuario(id)) {
                            Toast.makeText(Inicio.this, "SE ELIMINO CORRECTAMENTE", Toast.LENGTH_LONG).show();
                            Intent a=new Intent(Inicio.this,MainActivity.class);
                            startActivity(a);
                            finish();
                        }else{
                            Toast.makeText(Inicio.this, "ERROR NO SE ELIMINO CORRECTAMENTE", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                b.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                b.show();
                break;
            case  R.id.btnMostrar:
                Intent c=new Intent(Inicio.this,Mostrar.class);
                startActivity(c);
                break;
            case  R.id.btnSalir:
                Intent i2=new Intent(Inicio.this, MainActivity.class);
                startActivity(i2);
                break;
            case  R.id.btnMatricula:
                Intent m=new Intent(Inicio.this, StudentListActivity.class);
                startActivity(m);
                break;
        }
    }
}
