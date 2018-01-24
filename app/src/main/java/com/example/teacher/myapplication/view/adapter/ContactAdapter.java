package com.example.teacher.myapplication.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.teacher.myapplication.R;
import com.example.teacher.myapplication.model.Contact;

import java.util.List;

import static com.example.teacher.myapplication.R.id.textFamily;
import static com.example.teacher.myapplication.R.id.textName;

/**
 * Created by Teacher on 2017/12/23.
 */

public class ContactAdapter extends ArrayAdapter<Contact> {


    public static final String TAG = ContactAdapter.class.getName();

    private Context context;
    private int resource;
    private List<Contact> contactList;
    private ContactListener contactListener;

    public ContactAdapter(
            @NonNull Context context,
            @LayoutRes int resource,
            @NonNull List<Contact> contactList,
            @NonNull ContactListener contactListener) {
        super(context, resource, contactList);
        this.context = context;
        this.resource = resource;
        this.contactList = contactList;
        this.contactListener = contactListener;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View scrapView, @NonNull ViewGroup parent) {


        final ViewHolder holder;
        if (scrapView == null) {

            Log.i(TAG, "scrapView is null at pos " + position);

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            scrapView = inflater.inflate(resource, null);

            holder = new ViewHolder();

            holder.containerItem = (RelativeLayout) scrapView.findViewById(R.id.containerItem);
            holder.textName = (TextView) scrapView.findViewById(R.id.textName);
            holder.textFamily = (TextView) scrapView.findViewById(R.id.textFamily);
            holder.btnMore = (ImageView) scrapView.findViewById(R.id.btnMore);

            Log.i(TAG, "On set tag name: " + holder.textName);
            Log.i(TAG, "On set tag name: " + holder.textFamily);

            Log.i(TAG, "Holder on set tag : " + holder);
            scrapView.setTag(holder);
        } else {
            Log.i(TAG, "scrapView is not null at pos " + position);
            holder = (ViewHolder) scrapView.getTag();

            Log.i(TAG, "Holder on get tag : " + holder);
            Log.i(TAG, "On get tag name: " + holder.textName);
            Log.i(TAG, "On get tag name: " + holder.textFamily);
        }

//        if (position % 2 == 0) {
//            holder.containerItem.setBackgroundColor(Color.parseColor("#4a19a4"));
//        } else{
//            holder.containerItem.setBackgroundColor(Color.parseColor("#099323"));
//        }

        final Contact contact = contactList.get(position);

        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "on more clicked at pos " + position);
                contactListener.onMoreClicked(holder.btnMore,contact,position);
            }
        });

        holder.containerItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "on row clicked at pos " + position);
                contactListener.onContactClicked(contact,position);
            }
        });

        holder.containerItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Log.i(TAG, "on row long clicked at pos " + position);
                contactListener.onContactLongClicked(contact,position);
                return true;
            }
        });


        holder.textName.setText(contact.getName());
        holder.textFamily.setText(contact.getFamily());

        return scrapView;
    }

    public void addContact(String name, String family, String phoneNumber) {
        Contact contact = new Contact(name, family, phoneNumber);
        contactList.add(contact);
        notifyDataSetChanged();
    }

    public void removeContact(int position) {
        contactList.remove(position);
        notifyDataSetChanged();
    }

    public void updateContact(Contact contact, int position) {
        contactList.set(position, contact);
        notifyDataSetChanged();
    }

    public class ViewHolder {
        RelativeLayout containerItem;
        TextView textName;
        TextView textFamily;
        ImageView btnMore;
    }

    public interface ContactListener {
        void onContactClicked(Contact contact, int position);
        void onContactLongClicked(Contact contact, int position);
        void onMoreClicked(View view,Contact contact, int position);
    }

}
