package com.example.mansi.databaseoperation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etId,etName;
    Button btnGet,btnAdd,btnUpdate,btnDelete;
    TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etId= (EditText) findViewById(R.id.etId);
        btnGet= (Button) findViewById(R.id.btnGrt);
        btnAdd= (Button) findViewById(R.id.btnAdd);
        btnUpdate= (Button) findViewById(R.id.btnUpdate);
        btnDelete= (Button) findViewById(R.id.btnDelete);
        tvData= (TextView) findViewById(R.id.tvData);
        etName= (EditText) findViewById(R.id.etName);


        final MyDbHelper db=new MyDbHelper(this);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data=db.getEmployee();
                tvData.setText(data);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eid=etId.getText().toString();
                String ename=etName.getText().toString();
                if(eid.length()==0){
                    Toast.makeText(getApplicationContext(),"id cannot be empty",Toast.LENGTH_LONG).show();
                    etId.requestFocus();
                    return;
                }

                if(ename.length()==0){
                    Toast.makeText(getApplicationContext(),"name cannot be empty",Toast.LENGTH_LONG).show();
                    etName.requestFocus();
                    return;
                }
                db.addEmployee(Integer.parseInt(eid),ename);
                etId.setText("");
                etName.setText("");
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eid=etId.getText().toString();
                String ename=etName.getText().toString();
                if(eid.length()==0){
                    Toast.makeText(getApplicationContext(),"id cannot be empty",Toast.LENGTH_LONG).show();
                    etId.requestFocus();
                    return;
                }

                if(ename.length()==0){
                    Toast.makeText(getApplicationContext(),"name cannot be empty",Toast.LENGTH_LONG).show();
                    etName.requestFocus();
                    return;
                }
                db.updateEmployee(Integer.parseInt(eid),ename);
                etId.setText("");
                etName.setText("");
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eid=etId.getText().toString();
                if(eid.length()==0){
                    Toast.makeText(getApplicationContext(),"id cannot be empty",Toast.LENGTH_LONG).show();
                    etId.requestFocus();
                    return;
                }


                db.deleteEmployee(Integer.parseInt(eid));
                etId.setText("");
                etName.setText("");
            }
        });
    }
}
