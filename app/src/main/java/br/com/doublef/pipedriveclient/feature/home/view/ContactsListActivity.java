package br.com.doublef.pipedriveclient.feature.home.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import br.com.doublef.pipedriveclient.R;
import br.com.doublef.pipedriveclient.application.App;
import br.com.doublef.pipedriveclient.datamanager.DataManager;
import br.com.doublef.pipedriveclient.feature.home.contract.HomeContract;
import br.com.doublef.pipedriveclient.feature.home.presenter.HomePresenterImpl;
import br.com.doublef.pipedriveclient.feature.login.view.LoginActivity;
import br.com.doublef.pipedriveclient.model.Post;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsListActivity extends AppCompatActivity implements HomeContract.HomeView {

    @Inject
    HomePresenterImpl homePresenter;

    @Inject
    ContactAdapter contactAdapter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Inject
    DataManager dataManager;

    @BindView(R.id.homeProgressBar)
    ProgressBar progressBar;

    public static void start(Context context) {
        Intent starter = new Intent(context, ContactsListActivity.class);
        starter.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);
        ButterKnife.bind(this);

        ((App) getApplication()).components().inject(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(contactAdapter);

        homePresenter.attachView(this);
        loadData(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        homePresenter.detachView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_logout) {
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        homePresenter.logout();
        LoginActivity.start(this);
    }

    private void loadData(Bundle savedInstanceState) {
        boolean restoreData = savedInstanceState != null;
        if ( restoreData ){
            homePresenter.loadLocalData();
        }else {
            homePresenter.loadData();
        }
    }

    @Override
    public void showList(List<Post> personData) {
        contactAdapter.setContactList(personData);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}
