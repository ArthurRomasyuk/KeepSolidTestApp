package com.example.arutr.keepsolidapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arutr.keepsolidapp.models.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmailActivity extends AppCompatActivity {

    User user;
    Intent intentForResult;

    @BindView(R.id.username_email_activity)
    TextView tvUsername;

    @BindView(R.id.category_email_activity)
    TextView tvCategory;

    @BindView(R.id.onlineStatus_email_activity)
    ImageView ivOnlineStatus;

    @BindView(R.id.email)
    TextView tvEmail;

    @BindView(R.id.accept)
    Button acceptButton;

    @BindView(R.id.reject)
    Button rejectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        ButterKnife.bind(this);
        user = getIntent().getParcelableExtra("user");
        tvUsername.setText(user.getUsername());
        tvCategory.setText(getString(R.string.category) + getIntent().getStringExtra("categoryDescription"));
        tvEmail.setText(getString(R.string.email)+user.getUserAddress());
        if (user.isOnline()) {
            ivOnlineStatus.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.checkbox_blank_circle_green));
        } else {
            ivOnlineStatus.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.checkbox_blank_circle_grey));
        }
        intentForResult = new Intent();
    }

    @OnClick(R.id.accept)
    public void accept(View view) {
        Intent shareIntent = new Intent(Intent.ACTION_SENDTO);
        String uriText = "mailto:" + Uri.encode(user.getUserAddress());
        Uri uri = Uri.parse(uriText);
        shareIntent.setData(uri);
        try {
            startActivity(Intent.createChooser(shareIntent, "Send mail..."));
            Toast.makeText(EmailActivity.this, "Отправка сообщения...", Toast.LENGTH_SHORT).show();
            intentForResult.putExtra("reject", false);
            setResult(RESULT_OK,intentForResult);
            finish();
        } catch (ActivityNotFoundException e) {
            Toast.makeText(EmailActivity.this, "Email клиент не установлен.", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick (R.id.reject)
    public void reject(){
        intentForResult.putExtra("reject", true);
        setResult(RESULT_OK, intentForResult);
        finish();
    }
}
