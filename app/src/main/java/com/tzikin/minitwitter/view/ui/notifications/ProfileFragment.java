package com.tzikin.minitwitter.view.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tzikin.minitwitter.R;
import com.tzikin.minitwitter.databinding.FragmentProfileBinding;
import com.tzikin.minitwitter.view.common.Constants;
import com.tzikin.minitwitter.view.viewmodel.repository.model.request.UpdateProfileRequest;

import org.jetbrains.annotations.NotNull;


public class ProfileFragment extends Fragment implements View.OnClickListener {

    private ProfileViewModel profileViewModel;
    private FragmentProfileBinding binding;
    private boolean loadingData = true;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnSaveProfile.setOnClickListener(this);
        binding.btnPasswordUpdate.setOnClickListener(this);


        //ViewModel
        profileViewModel.userProfile.observe(requireActivity(), response -> {

            loadingData = false;

            binding.edtUsernameProfile.setText(response.getUsername());
            binding.edtEmailProfile.setText(response.getEmail());
            binding.edtWebsite.setText(response.getWebsite());
            if(!response.getPhotoURL().isEmpty()){
                Glide.with(requireActivity()).load(Constants.PHOTO_URL_SERVER + response.getPhotoURL())
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .centerCrop()
                        .skipMemoryCache(true)
                        .into(binding.imgProfile);
            }

            if(!loadingData){
                binding.btnSaveProfile.setEnabled(true);
                Toast.makeText(requireActivity(), "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
            }


        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.btnSaveProfile:

                String email = binding.edtEmailProfile.getText().toString();
                String username = binding.edtUsernameProfile.getText().toString();
                String description = binding.edtDescription.getText().toString();
                String website = binding.edtWebsite.getText().toString();
                String password = binding.edtPasswordProfile.getText().toString();

                if(username.isEmpty()){
                    binding.edtUsernameProfile.setError("El nombre de usuario es requerido");
                }else if(email.isEmpty()){
                    binding.edtEmailProfile.setError("El email es requerido");
                }else if(password.isEmpty()){
                    binding.edtPasswordProfile.setError("La contraseña es requerida");
                }else{
                    UpdateProfileRequest request = new UpdateProfileRequest(username, email, description, website);
                    profileViewModel.updateProfile(request);
                    Toast.makeText(requireActivity(), "Enviando información al servidor...", Toast.LENGTH_SHORT).show();
                    binding.btnSaveProfile.setEnabled(false);
                }

                break;

            case R.id.btnPasswordUpdate:
                break;
        }
    }
}