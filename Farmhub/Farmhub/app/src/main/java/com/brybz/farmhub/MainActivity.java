package com.brybz.farmhub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Button button;
    public EditText editTextName, editTextPhoneNumber, editTextQuestion, editTextEmail, editTextLocation;
    public RadioGroup radioGroup;
    public RadioButton radioButton;

    String expertPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail=(EditText)findViewById(R.id.editTextEmail);
        editTextPhoneNumber=(EditText)findViewById(R.id.editTextPhoneNumber);
        editTextName=(EditText)findViewById(R.id.editTextName);
        editTextQuestion=(EditText)findViewById(R.id.editTextQuestion);
        editTextLocation=(EditText)findViewById(R.id.editTextLocation);

        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
                System.exit(0);
            }
        });

        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent brand = new Intent(MainActivity.this,second.class);
                startActivity(brand);*/
                sendSMSMessage();

            }
        });
    }
    protected void sendSMSMessage() {
        Log.i("Send SMS", "");
        String name=editTextName.getText().toString();
        String phoneNo = editTextPhoneNumber.getText().toString();
        String location= editTextLocation.getText(). toString();
        String question = editTextQuestion.getText().toString();
        String email=editTextEmail.getText().toString();
        //get selected radioButton from RadioGroup
        int selectedId=radioGroup.getCheckedRadioButtonId();
        //find the radioButton by returned id
        radioButton=(RadioButton)findViewById(selectedId);
        String farmingType=radioButton.getText().toString();

        if (farmingType.equalsIgnoreCase("Maize Farming")){
            expertPhoneNumber="0733717162";
        }
        else{
            expertPhoneNumber="0723701894";
        }
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(expertPhoneNumber, null,name+","+phoneNo+" from "+location+" asks: "+question, null, null);
            smsManager.sendTextMessage(phoneNo, null, "Your question has been sent to an expert in "+farmingType+" on Mobile No: "+expertPhoneNumber+". The expert will respond to your enquiry as soon as possible", null, null);
            Toast.makeText(getApplicationContext(), "Query Sent", Toast.LENGTH_LONG).show();
        }

        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS failed, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

}