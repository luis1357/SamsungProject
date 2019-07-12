package com.ruisu1357.samsungproject.ui.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.ruisu1357.samsungproject.R;
import com.ruisu1357.samsungproject.databinding.ActivityMainBinding;
import com.ruisu1357.samsungproject.ui.adapters.ImageRecyclerViewAdapter;
import com.ruisu1357.samsungproject.ui.viemodel.MainViewModel;

public class MainActivity extends AppCompatActivity
{
    public static final String TAG = "MainActivity";
    private MainViewModel mainViewModel;
    private ActivityMainBinding myBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        myBinder = DataBindingUtil.setContentView(this, R.layout.activity_main);

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
       mainViewModel.getImageResponse().observe(this,
                imageList ->
                {
                    Log.d(TAG, "setUpObservers: ");
                    myBinder.myRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
                    myBinder.myRecyclerView.setAdapter(new ImageRecyclerViewAdapter(this,
                            imageList));
                    myBinder.myRecyclerView.setItemAnimator(new DefaultItemAnimator());
                });
    }
}
