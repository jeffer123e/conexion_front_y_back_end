package com.example.apppostgresqlcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.QuickContactBadge;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.Types;

public class activity_login extends AppCompatActivity {
//Declaramos nuestra variable de conexion
    private static clsConexionPG con=new clsConexionPG();
Variables_Globales va=Variables_Globales.getInstance();

    //Declaramos los elementos de nuestro activity login
    Button btniniciar;
    EditText txtusuario,txtclave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Luego a esas variables le asignamos los valores de cada elemento
        btniniciar=findViewById(R.id.btniniciar);
        txtusuario=findViewById(R.id.txtusuario);
        txtclave=findViewById(R.id.txtclave);

        btniniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Incio_Sesion(txtusuario.getText().toString(),txtclave.getText().toString());
            }
        });
    }

    //Crearemos la Función para Iniciar Sesion de Postgresql
    public  void Incio_Sesion(String usuario, String clave){
        try{
            String storeProcedureCall="{CALL pa_logueo_android(?,?,?,?,?,?,?,?)}";
            CallableStatement cStmt=con.conexionBD().prepareCall(storeProcedureCall);
            //Estos dos primeros parametros son los de entrada
            cStmt.setString(1,usuario);
            cStmt.setString(2,clave);

            // y estos  6 ultimos son los de salida aqui solo le indicamos que tipo de parametro se
            cStmt.registerOutParameter(3, Types.INTEGER);
            cStmt.registerOutParameter(4, Types.VARCHAR);
            cStmt.registerOutParameter(5, Types.VARCHAR);
            cStmt.registerOutParameter(6, Types.VARCHAR);
            cStmt.registerOutParameter(7, Types.VARCHAR);
            cStmt.registerOutParameter(8, Types.VARCHAR);

            cStmt.executeUpdate();

            //declaramos las variables que recibiremos de la funcion de postgresql

            Integer _codigo=cStmt.getInt(3);
            String _datos=cStmt.getString(4);
            String _direccion=cStmt.getString(5);
            String _telefono=cStmt.getString(6);
            String _email=cStmt.getString(7);
            String _msj=cStmt.getString(8);

            //agregamos una condicional para sercioranos si se ingresaron correctamente el usuario y la clave

            if(_msj.equals("OK")){
                //si es correcto cargamos el menu con los datos del usuario
va.set_codigousuario(_codigo);
va.set_datos(_datos);
va.set_direccion(_direccion);
va.set_email(_email);
va.set_telefono(_telefono);

//Luego Abrimos el activity menu
                Intent menu=new Intent(this,activity_menu.class);
                startActivity(menu);

            }else{
                Toast.makeText(getApplicationContext(),_msj,Toast.LENGTH_SHORT).show();
            }

        }catch (Exception er){
            Toast.makeText(getApplicationContext(),er.toString(),Toast.LENGTH_SHORT).show();
        }

    }
}
