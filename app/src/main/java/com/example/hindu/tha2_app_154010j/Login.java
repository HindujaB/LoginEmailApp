package com.example.hindu.tha2_app_154010j;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    private static final Pattern INDEX_PATTERN =
            Pattern.compile("^"+"(1)"+"(5)"+"(4|5)"+"(\\d)"+"(\\d)"+"(\\d)"+"([a-z])"+
                    "$",Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    DatabaseHelper openHelper;
    SQLiteDatabase db;
    Button _btnlogin;
    EditText  _txtIndex, _txtPass;
    String  USER_INDEX;
    TextView _errortxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        openHelper = new DatabaseHelper(this);


        _btnlogin = (Button)findViewById(R.id.btnlogin) ;
        _txtIndex = (EditText) findViewById(R.id.TxtIndex);
        _txtPass = (EditText) findViewById(R.id.TxtPassword);
        _errortxt = (TextView) findViewById(R.id.error);
        _txtIndex.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String index = _txtIndex.getText().toString();
                validateIndex(index);
            }
        });

        _btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String index = _txtIndex.getText().toString();
                String pass = _txtPass.getText().toString();



                User user = openHelper.getUser(index);
                boolean status = false;


                if (user != null && user.password.equals(pass) &&validateIndex(index)){
                    Toast.makeText(getApplicationContext(),"Login successful!",Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(Login.this, userView.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("IndexNo",index);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(getApplicationContext(),"Wrong username or password!",Toast.LENGTH_LONG).show();
                }



            }
        });
    }
    public void setIndex(String index){
        USER_INDEX = index;
    }
    public String getIndex(){
        return USER_INDEX;
    }
    private boolean validateIndex(String inputS){

        if (inputS.isEmpty()){
            _errortxt.setText("Field cannot be empty");
            return  false;
        }
        else if (!INDEX_PATTERN.matcher(inputS).matches()){
            _errortxt.setText("Invalid Index number!");
            return false;
        }
        else{
            _errortxt.setText("Now the Index number is Valid");
            return  true;
        }
    }

}
