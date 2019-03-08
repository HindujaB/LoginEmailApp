package com.example.hindu.tha2_app_154010j;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.facebook.stetho.Stetho;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private static final Pattern INDEX_PATTERN =
            Pattern.compile("^"+"(1)"+"(5)"+"(4|5)"+"(\\d)"+"(\\d)"+"(\\d)"+"([a-z])"+
                    "$",Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    private static final Pattern MOBILE_NO =
            Pattern.compile("^[0-9]{9,10}$");
    DatabaseHelper openHelper;
    Button _btnReg, _btnlogin;
    EditText _nametext, _indextxt, _mailtxt, _mobiletxt, _GPAtxt, _passtxt, _cpasstxt ;
    TextView _errortxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_main);

        openHelper = new DatabaseHelper(this);

        _btnReg = (Button)findViewById(R.id.btnReg);
        _btnlogin = (Button)findViewById(R.id.btnlgn) ;
        _nametext = (EditText) findViewById(R.id.nametxt);
        _indextxt = (EditText) findViewById(R.id.indextxt);
        _mailtxt = (EditText) findViewById(R.id.mailtxt);
        _mobiletxt = (EditText) findViewById(R.id.mobiletxt);
        _GPAtxt = (EditText) findViewById(R.id.GPAtxt);
        _passtxt = (EditText) findViewById(R.id.passtxt);
        _cpasstxt = (EditText) findViewById(R.id.cpasstxt);
        _errortxt = (TextView) findViewById(R.id.Errtxt);

        _indextxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String index = _indextxt.getText().toString();
                validateIndex(index);
            }
        });


        _mailtxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String mail = _mailtxt.getText().toString();
                validateMail(mail);
            }
        });

        _mobiletxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String mobile = _mobiletxt.getText().toString();
                validateMobile(mobile);
            }
        });

        _btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //db = openHelper.getWritableDatabase();

                String name = _nametext.getText().toString();
                String index = _indextxt.getText().toString();
                String mail = _mailtxt.getText().toString();
                String mobile = _mobiletxt.getText().toString();
                String GPA = _GPAtxt.getText().toString();
                String password = _passtxt.getText().toString();
                String cpassword = _cpasstxt.getText().toString();

                if (validate(name)&& validate(GPA)&&validate(password)&& validate(cpassword)&& validateMobile(mobile) && validateMail(mail) && validateIndex(index)) {

                    if (password.equals(cpassword)) {
                        boolean ab = openHelper.addInfo(name, index, mail, mobile, GPA, password);
                        if (ab){
                            Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(MainActivity.this, Login.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Registration not successful!", Toast.LENGTH_LONG).show();
                        }

                    } else {

                        Toast.makeText(getApplicationContext(), "Passwords mismatching!", Toast.LENGTH_LONG).show();
                    }
                }
                else
                    _errortxt.setText("Field cannot be empty");
        }
        });

        _btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });






    }


    private boolean validate(String inputS){

        if (inputS.isEmpty()){

            return  false;
        }
        else
            return  true;
    }

    private boolean validateMail(String inputS){

        if (inputS.isEmpty()){
            _errortxt.setText("Field cannot be empty");
            return  false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(inputS).matches()) {
            _errortxt.setText("Invalid email address!");
            return false;
        }
        else{
            _errortxt.setText("Now the email address is Valid");
            return  true;
        }

    }

    private boolean validateMobile(String inputS){

        if (inputS.isEmpty()){
            _errortxt.setText("Field cannot be empty");
            return  false;
        }
        else if (!MOBILE_NO.matcher(inputS).matches()){
            _errortxt.setText("Invalid Mobile number!");
            return false;
        }
        else{
            _errortxt.setText("Now the mobile number is Valid");
            return  true;
        }
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
