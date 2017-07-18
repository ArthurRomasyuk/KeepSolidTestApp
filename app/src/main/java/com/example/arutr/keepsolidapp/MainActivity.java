package com.example.arutr.keepsolidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.etEmail)
    EditText etEmail;

    @BindView(R.id.clean)
    Button cleanButton;

    @BindView(R.id.send)
    Button sendButton;

    @BindView(R.id.errorMessage)
    TextView errorMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick (R.id.send)
    public void send (View view){
        if (TextUtils.isEmpty(etEmail.getText())){
            errorMessage.setText("Поле email не может быть пустым");
        } else if (!etEmail.getText().toString().contains("@")){
            errorMessage.setText("Введите правильный email");
        } else {
            Intent emailIntent = new Intent(this, EmailActivity.class);
            emailIntent.putExtra("email", etEmail.getText().toString());
            startActivity(emailIntent);
        }
    }
    @OnClick(R.id.clean)
    public void clean (View view){
        etEmail.setText("");
    }
}
