package br.com.doublef.pipedriveclient.feature.home.view;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.util.List;

import javax.inject.Inject;

import br.com.doublef.pipedriveclient.R;
import br.com.doublef.pipedriveclient.feature.details.DetailsActivity;
import br.com.doublef.pipedriveclient.model.Contact;
import br.com.doublef.pipedriveclient.util.UiUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>{

    private Context context;

    private List<Contact> contactList;

    public ContactAdapter(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
        this.notifyDataSetChanged();
    }

    @Inject
    public ContactAdapter() {
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();
        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.activity_contacts_list_item, parent, false);

        return new ContactViewHolder(view);

    }

    @Override
    public int getItemCount() {
        return contactList != null ? contactList.size() : 0;
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        String name = contact.getName();
        holder.text.setText(name);

        holder.container.setOnClickListener(v -> DetailsActivity.start(v.getContext(), contact));

        TextDrawable drawable = UiUtil.getTextDrawable(name);
        holder.imageView.setImageDrawable(drawable);

    }

    static class ContactViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text)
        TextView text;

        @BindView(R.id.image_view)
        ImageView imageView;

        @BindView(R.id.container)
        View container;

        public ContactViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
