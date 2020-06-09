package com.example.requerimentos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         db = FirebaseFirestore.getInstance();

    }

    public void gravarDados(View view){

        EditText nome1 = (EditText)findViewById(R.id.etName);
        EditText mat1 = (EditText)findViewById(R.id.etMat);
        EditText dep1 = (EditText)findViewById(R.id.etDep);
        EditText desc1 = (EditText)findViewById(R.id.etDesc);

        String nome = ((EditText)findViewById(R.id.etName)).getText().toString();
        String mat = ((EditText)findViewById(R.id.etMat)).getText().toString();
        String dep = ((EditText)findViewById(R.id.etDep)).getText().toString();
        String desc = ((EditText)findViewById(R.id.etDesc)).getText().toString();

        if (((nome == " ") || (mat == " ") || (dep == " ") || (desc == ""))){
            popup();
        }else{
            Map<String, Object> requerimento = new HashMap<>();
            requerimento.put("name", nome);
            requerimento.put("matricula", mat);
            requerimento.put("departamento", dep);
            requerimento.put("descricao", desc);

            db.collection("requerimento").add(requerimento);

            toast();
            nome1.setText("");
            mat1.setText("");
            dep1.setText("");
            desc1.setText("");
        }
    }

    public void popup() {
        AlertDialog alerta;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ATENÇÃO!!!");
        builder.setMessage("Campos Nome e Quantidade não pode ser vazio ou 0!!!");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Concluido" , Toast.LENGTH_SHORT).show();
            }
        });
        alerta = builder.create();
        alerta.show();
    }

    public void toast(){
        Context context = getApplicationContext();
        CharSequence text = "Sua solicitação foi cadastrada com sucesso!!!";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        toast.setGravity(Gravity.CENTER|Gravity.CENTER, 0, 0);
    }
}
