package com.example.apppostgresqlcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class activity_menu extends AppCompatActivity {
    Variables_Globales va=Variables_Globales.getInstance();

    //igualmente declaramos los elementos
    TextView txtdatos,txtdireccion,txtemail,txttelefono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

txtdatos=findViewById(R.id.txtdatos);
        txtdireccion=findViewById(R.id.txtdireccion);
        txtemail=findViewById(R.id.txtemail);
        txttelefono=findViewById(R.id.txttelefono);

        if(va.get_codigousuario()!=0){
            txtdatos.setText(va.get_datos().toString());
            txtdireccion.setText(va.get_direccion().toString());
            txtemail.setText(va.get_email().toString());
            txttelefono.setText(va.get_telefono().toString());
        }

    }
}
