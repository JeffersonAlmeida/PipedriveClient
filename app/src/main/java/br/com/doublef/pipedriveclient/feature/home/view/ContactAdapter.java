package br.com.doublef.pipedriveclient.feature.home.view;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import java.util.List;

import javax.inject.Inject;

import br.com.doublef.pipedriveclient.R;
import br.com.doublef.pipedriveclient.model.Post;
import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>{

    private Context context;

    private List<Post> contactList;

    public ContactAdapter(List<Post> contactList) {
        this.contactList = contactList;
    }

    public void setContactList(List<Post> contactList) {
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
        Post contact = contactList.get(position);
        String name = contact.getTitle();
        holder.text.setText(name);

        // holder.container.setOnClickListener(v -> DetailsActivity.start(v.getContext(), contact));

        String url = "https://api.adorable.io/avatars/86/" + contact.getId() + ".png";
        Glide
                .with(context)
                .load(url)
                .centerCrop()
                .crossFade()
                .bitmapTransform( new jp.wasabeef.glide.transformations.RoundedCornersTransformation( context, 100, 0 ) )
                .placeholder(R.drawable.ic_account_circle_grey_400_24dp)
                .into(holder.imageView);

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
