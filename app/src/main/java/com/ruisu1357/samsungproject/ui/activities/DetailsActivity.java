package com.ruisu1357.samsungproject.ui.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;

import com.ruisu1357.samsungproject.R;
import com.ruisu1357.samsungproject.data.local.database.FavoritesSqlHelper;
import com.ruisu1357.samsungproject.data.remote.models.ImageResponse;
import com.ruisu1357.samsungproject.databinding.ActivityDetailsBinding;
import com.ruisu1357.samsungproject.databinding.ActivityMainBinding;
import com.ruisu1357.samsungproject.ui.adapters.ImageRecyclerViewAdapter;
import com.ruisu1357.samsungproject.ui.viemodel.MainViewModel;
import com.squareup.picasso.Picasso;

import static com.ruisu1357.samsungproject.utils.Constants.KEY_IMAGE_RESPONSE;

public class DetailsActivity extends AppCompatActivity
{
    public static final String TAG = "DetailsActivity";
    private MainViewModel mainViewModel;
    private ActivityDetailsBinding myBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myBinder = DataBindingUtil.setContentView(this, R.layout.activity_details);


        setUpViewModels();
        setUpObservers();



        myBinder.setViewModel(mainViewModel);
        myBinder.setLifecycleOwner(this);


    }

    private void setUpViewModels()
    {
        mainViewModel = ViewModelProviders.of(this)
                .get(MainViewModel.class);
    }

    private void setUpObservers()
    {

        final Intent passedIntent = getIntent();
        if(passedIntent != null) {
            Bundle bundle = passedIntent.getExtras();
            if(bundle != null) {
                ImageResponse imageResponse = bundle.getParcelable(KEY_IMAGE_RESPONSE);
                if(imageResponse != null) {
                    //populate

                    Log.d(TAG, "setUpObservers: ");
                    Picasso.get().load(imageResponse.getUrl()).into(myBinder.imageD);
                    myBinder.textViewImageURL.setText(imageResponse.getTitle());

                }
            }
        }
    }

    public void saveTodb(View view)
    {
        final Intent passedIntent = getIntent();
        if(passedIntent != null) {
            Bundle bundle = passedIntent.getExtras();
            if(bundle != null) {
                ImageResponse imageResponse = bundle.getParcelable(KEY_IMAGE_RESPONSE);
                if(imageResponse != null) {
                    //populate

                    FavoritesSqlHelper favoritesSqlHelper = new FavoritesSqlHelper(this);
                    favoritesSqlHelper.insertIntoDatabase(imageResponse);

                }
            }
        }
    }
}
