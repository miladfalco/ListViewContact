package com.example.teacher.myapplication.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.teacher.myapplication.BaseActivity;
import com.example.teacher.myapplication.R;
import com.example.teacher.myapplication.model.Contact;
import com.example.teacher.myapplication.view.adapter.ContactAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity implements ContactAdapter.ContactListener {

    private ListView listViewContacts;
    private ContactAdapter contactAdapter;

    @Override
    public int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void onLayoutIsReady(Bundle savedInstanceState, Intent intent) {
        listViewContacts = (ListView) findViewById(R.id.listViewContacts);
        List<Contact> mockContacts = new ArrayList<>();
        contactAdapter = new ContactAdapter(
                this,
                R.layout.item_contact,
                mockContacts,
                this);

        listViewContacts.setAdapter(contactAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_ADD_CONTACT && resultCode == RESULT_OK) {
            String name = data.getStringExtra(EXTRA_KEY_NAME);
            String family = data.getStringExtra(EXTRA_KEY_FAMILY);
            String phoneNumber = data.getStringExtra(EXTRA_KEY_PHONE_NUMBER);

            contactAdapter.addContact(name, family, phoneNumber);
        }
    }

    public void onAddContact(View view) {
        Intent intent = new Intent(this, AddContactActivity.class);
        startActivityForResult(intent, REQ_ADD_CONTACT);
    }

    @Override
    public void onContactClicked(Contact contact, int position) {
        Toast.makeText(this, "Clicked at pos " + position + " Contact name : " + contact.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onContactLongClicked(Contact contact, int position) {
        Toast.makeText(this, "Long clicked at pos " + position + " Contact name : " + contact.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMoreClicked(View view, final Contact contact, final int position) {
        Toast.makeText(this, "More clicked at pos " + position + " Contact name : " + contact.getName(), Toast.LENGTH_SHORT).show();
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.menu_contact_more);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                int itemId = menuItem.getItemId();

                switch (itemId) {

                    case R.id.actionCall:
                        // TODO: 2017/12/23 add a number on dialing !
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        startActivity(intent);
                        break;

                    case R.id.actionEdit:
                        // TODO: 2017/12/23 show a dialog that has three edit texts that can update contact info
                        promptUserToUpdateContactInfo(contact,position);
                        break;

                    case R.id.actionRemove:
                        contactAdapter.removeContact(position);
                        break;
                }

                return true;
            }
        });

        popupMenu.show();
    }

    private void promptUserToUpdateContactInfo(final Contact contact , final int position){

        View dialogView = getLayoutInflater().inflate(R.layout.layout_dialog_edit_contact, null);
        final EditText editName = (EditText) dialogView.findViewById(R.id.editName);
        final EditText editFamily = (EditText) dialogView.findViewById(R.id.editFamily);
        final EditText editPhoneNumber = (EditText) dialogView.findViewById(R.id.editPhoneNumber);

        editName.setText(contact.getName());
        editFamily.setText(contact.getFamily());
        editPhoneNumber.setText(contact.getPhoneNumber());

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Update contact info !");
        builder.setView(dialogView);
        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                contact.setName(editName.getText().toString().trim());
                contact.setFamily(editFamily.getText().toString().trim());
                contact.setPhoneNumber(editPhoneNumber.getText().toString().trim());

                contactAdapter.updateContact(contact, position);
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

}
