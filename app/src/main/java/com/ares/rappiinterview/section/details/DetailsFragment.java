package com.ares.rappiinterview.section.details;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.ares.rappiinterview.BuildConfig;
import com.ares.rappiinterview.R;
import com.ares.rappiinterview.databinding.FragmentMovieDetailsBinding;
import com.bumptech.glide.Glide;

/**
 * Created by ares on 2019-05-28
 */
public class DetailsFragment extends Fragment {

    private FragmentMovieDetailsBinding binding;
    private DetailsViewModel detailsViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false);

        init();
        return binding.getRoot();
    }

    private void init() {
        detailsViewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
        detailsViewModel.getMovieDetails(getArguments() != null ? getArguments().getInt("id") : 0, getString(R.string.language)).observe(this, details -> {
            binding.setDetails(details);
            if(null != ((AppCompatActivity) getActivity()).getSupportActionBar()) {
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(details.title);
            }
        });
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(BuildConfig.BASEURL_IMG + url)
                .into(imageView);
    }

    @BindingAdapter("rating")
    public static void setRating(AppCompatRatingBar appCompatRatingBar, String rate) {
        appCompatRatingBar.setRating(Float.valueOf(rate));
    }

    @BindingAdapter("mins")
    public static void setMins(TextView textView, int mins){
        textView.setText(String.format("%smins", String.valueOf(mins)));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        detailsViewModel.dispose();
    }
}
