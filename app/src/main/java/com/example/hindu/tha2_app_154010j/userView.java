package com.example.hindu.tha2_app_154010j;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class userView extends AppCompatActivity {

    DatabaseHelper openHelper;
    Button _btnEmail;
    TextView _namelbl, _indexlbl, _mailbl, _mobilelbl, _gpalbl;

    String name,index,mail,mobile,gpa,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);
        openHelper = new DatabaseHelper(this);




        _btnEmail = (Button)findViewById(R.id.emailbtn) ;
        _namelbl = (TextView) findViewById(R.id.nameV);
        _indexlbl = (TextView) findViewById(R.id.indexV);
        _mailbl = (TextView) findViewById(R.id.mailV);
        _mobilelbl = (TextView) findViewById(R.id.mobileV);
        _gpalbl = (TextView) findViewById(R.id.gpaV);

        final boolean res = loadData();


        _btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(res) {
                    sendMail();
                }

            }
        });




    }
    private void sendMail(){
        String [] receiver = {"b15in4500@gmail.com",""};
        String subject = "My contact details";
        String message = "Name : "+ name +"\n Index : "+ index +"\n Email address : "+ mail +"\n Mobile number : "+ mobile +"\n GPA : "+ gpa +"\n Password : "+ password;

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL,receiver);
        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
        intent.putExtra(Intent.EXTRA_TEXT,message);

        intent.setType("message/rfc822");

        try {
            startActivityForResult(Intent.createChooser(intent,"Choose an email client"), 1);
        }
        catch (android.content.ActivityNotFoundException ex) {

        }




    }
    private boolean loadData(){

        Bundle bundle = getIntent().getExtras();
        String IndexNo = bundle.getString("IndexNo");




        User user =  openHelper.getUser(IndexNo);

        boolean status = false;
        if (user != null){
            status = true;
            name = user.getName();
            index = user.getIndex();
            mail = user.getMail();
            mobile = user.getMobile();
            gpa = user.getGpa();
            password = user.getPassword();

            _namelbl.setText(name);
            _indexlbl.setText(index);
            _mailbl.setText(mail);
            _mobilelbl.setText(mobile);
            _gpalbl.setText(gpa);

        }



        return status;

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 1) {
            if (requestCode == 1 && resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Mail sent successfully!", Toast.LENGTH_SHORT).show();

            }
        }
    }


}
