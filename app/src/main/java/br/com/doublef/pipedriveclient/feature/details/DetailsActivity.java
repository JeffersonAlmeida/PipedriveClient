package br.com.doublef.pipedriveclient.feature.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import javax.inject.Inject;

import br.com.doublef.pipedriveclient.R;
import br.com.doublef.pipedriveclient.application.App;
import br.com.doublef.pipedriveclient.database.DatabaseFacade;
import br.com.doublef.pipedriveclient.model.Contact;
import br.com.doublef.pipedriveclient.model.Email;
import br.com.doublef.pipedriveclient.model.Phone;
import br.com.doublef.pipedriveclient.model.Post;
import br.com.doublef.pipedriveclient.util.Constants;
import br.com.doublef.pipedriveclient.util.UiUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @Inject
    DatabaseFacade databaseFacade;

    @BindView(R.id.image_profile)
    ImageView imageProfile;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.company)
    TextView company;

    @BindView(R.id.phone_label)
    TextView phoneLabel;

    @BindView(R.id.phone_number)
    TextView phoneNumber;

    @BindView(R.id.email_label)
    TextView emailLabel;

    @BindView(R.id.email_address)
    TextView emailAddress;

    private Post contact;

    public static void start(Context context, Post contact) {
        Intent starter = new Intent(context, DetailsActivity.class);
        starter.putExtra(Constants.CONTACT_DATA, contact);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        App application = (App) getApplication();
        application.components().inject(this);

        contact = getIntent().getParcelableExtra(Constants.CONTACT_DATA);

        setToolbarTitle();

        TextDrawable drawable = UiUtil.getTextDrawable(contact.getTitle());
        imageProfile.setImageDrawable(drawable);

        name.setText("Name");

        company.setText("DoubleF");

        setEmailFields();
        setPhoneFields();

    }

    private void setPhoneFields() {
//        if ( contact.getPhoneData() != null && !contact.getPhoneData().isEmpty() ){
//            Phone firstPhone = contact.getPhoneData().get(0);
//            phoneLabel.setText(firstPhone.getLabel());
//            phoneNumber.setText(firstPhone.getValue());
//        }

        phoneLabel.setText("Phone");
        phoneNumber.setText("118989898393");
    }

    private void setEmailFields() {
//        if ( contact.getEmailData() != null && !contact.getEmailData().isEmpty()){
//            Email firstEmail = contact.getEmailData().get(0);
//            emailLabel.setText(firstEmail.getLabel());
//            emailAddress.setText(firstEmail.getValue());
//        }

        emailLabel.setText("EMAIL");
        emailAddress.setText("jefferson@comp.ufu.br");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setToolbarTitle() {
        ActionBar supportActionBar = getSupportActionBar();
        if ( supportActionBar != null ) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setHomeButtonEnabled(true);
            supportActionBar.setTitle(contact.getUserId()+"");
            supportActionBar.setSubtitle(contact.getTitle());
        }
    }
}
