package com.tzikin.minitwitter.view;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.tzikin.minitwitter.R;
import com.tzikin.minitwitter.databinding.ActivityDashboardBinding;
import com.tzikin.minitwitter.view.common.Constants;
import com.tzikin.minitwitter.view.ui.home.TweetListFragment;

import org.jetbrains.annotations.NotNull;


public class Dashboard extends AppCompatActivity {

    private ActivityDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);

        navView.setOnNavigationItemSelectedListener(item -> {

            Fragment fragment = null;

            switch (item.getItemId()){
                case R.id.navigation_home:

                    fragment = TweetListFragment.newInstance(Constants.TWEET_LIST_ALL);

                    break;

                case R.id.navigation_tweet_like:

                    fragment = TweetListFragment.newInstance(Constants.TWEET_LIST_FAVS);

                    break;

                case R.id.navigation_profile:
                    break;
            }

            if(fragment != null){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_dashboard, fragment)
                        .commit();

                return true;
            }
            return false;
        });

        /*

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_home, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_dashboard);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

         */
    }

}