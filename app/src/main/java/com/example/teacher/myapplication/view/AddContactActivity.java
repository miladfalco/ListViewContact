package com.example.teacher.myapplication.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.teacher.myapplication.BaseActivity;
import com.example.teacher.myapplication.R;

public class AddContactActivity extends BaseActivity {

    private EditText editName;
    private EditText editFamily;
    private EditText editPhoneNumber;

    @Override
    public int getLayout() {
        return R.layout.activity_add_contact;
    }

    @Override
    public void onLayoutIsReady(Bundle savedInstanceState, Intent intent) {
        editName = (EditText) findViewById(R.id.editName);
        editFamily = (EditText) findViewById(R.id.editFamily);
        editPhoneNumber = (EditText) findViewById(R.id.editPhoneNumber);
    }

    public void onAddContact(View view) {
        String name = editName.getText().toString();
        String family = editFamily.getText().toString();
        String phoneNumber = editPhoneNumber.getText().toString();

        Intent data = new Intent();
        data.putExtra(EXTRA_KEY_NAME, name);
        data.putExtra(EXTRA_KEY_FAMILY, family);
        data.putExtra(EXTRA_KEY_PHONE_NUMBER, phoneNumber);
        setResult(RESULT_OK, data);
        finish();
    }

}
