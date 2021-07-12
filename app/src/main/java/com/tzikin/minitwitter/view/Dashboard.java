package com.tzikin.minitwitter.view;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.tzikin.minitwitter.R;
import com.tzikin.minitwitter.databinding.ActivityDashboardBinding;
import com.tzikin.minitwitter.view.common.Constants;
import com.tzikin.minitwitter.view.ui.home.TweetListFragment;
import com.tzikin.minitwitter.view.ui.notifications.ProfileViewModel;

import org.jetbrains.annotations.NotNull;


public class Dashboard extends AppCompatActivity implements PermissionListener {

    private ActivityDashboardBinding binding;
    private ProfileViewModel profileViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
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

    @Override
    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
        // Invocamos la selección de fotos de la galería
        Intent selectPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(selectPhoto, Constants.SELECT_PHOTO_GALLERY);
    }

    @Override
    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
        Toast.makeText(this, "No se puede seleccionar la fotografía", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode != RESULT_CANCELED){
            if(requestCode == Constants.SELECT_PHOTO_GALLERY){
                if(data != null){
                    Uri imageSelected = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};   // Array de las propiedades
                    Cursor cursor = getContentResolver().query(imageSelected, filePathColumn, null, null, null);
                    if(cursor != null){
                        cursor.moveToFirst();
                        int imageIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String photoPath = cursor.getString(imageIndex);
                        profileViewModel.uploadPhoto(photoPath);
                        cursor.close();
                    }

                }
            }
        }


    }
}