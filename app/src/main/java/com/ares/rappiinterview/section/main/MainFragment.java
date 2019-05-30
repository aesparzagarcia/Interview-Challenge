package com.ares.rappiinterview.section.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.ares.rappiinterview.R;
import com.ares.rappiinterview.data.model.Movie;
import com.ares.rappiinterview.databinding.FragmentMainBinding;
import com.ares.rappiinterview.utils.Utils;

import java.util.List;

/**
 * Created by ares on 2019-05-28
 */
public class MainFragment extends Fragment implements iMovieClickListener {

    private MoviesAdapter adapter;
    private MainViewModel model;
    private FragmentMainBinding fragmentMainBinding;
    private boolean isLoading = true;
    private Utils utils;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentMainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);

        model = ViewModelProviders.of(this).get(MainViewModel.class);
        utils = new Utils(getContext());

        setTitle();

        if(isLoading)
            fragmentMainBinding.progressCircular.setVisibility(View.VISIBLE);


        //Ofline mode
        //if(utils.isOnline())
            observePopular();
        //else
          //  getMoviesOfLine();

        return fragmentMainBinding.getRoot();
    }

    private void getMoviesOfLine() {
        model.getAllMovies().observe(this, new Observer<List<Movie.results>>() {
            @Override
            public void onChanged(List<Movie.results> results) {
                adapter.setMovies(results);
            }
        });
    }

    private void observePopular() {
        model.getPopularMovies(1, getString(R.string.language)).observe(this, movie -> {
            isLoading = false;
            fragmentMainBinding.progressCircular.setVisibility(View.GONE);
            fragmentMainBinding.rvMovies.setVisibility(View.VISIBLE);
            //Ofline mode
          /*  for(Movie.results results : movie.results){
                model.insert(results);
            }*/
            adapter = new MoviesAdapter(movie.results, this, getContext());
            fragmentMainBinding.rvMovies.setLayoutManager(new GridLayoutManager(getContext(), 3));
            fragmentMainBinding.rvMovies.setAdapter(adapter);
        });

    }

    private void observeTopRated() {
        model.getTopRated(1, getString(R.string.language)).observe(this, movie -> {
            adapter = new MoviesAdapter(movie.results, this, getContext());
            fragmentMainBinding.rvMovies.setLayoutManager(new GridLayoutManager(getContext(), 3));
            fragmentMainBinding.rvMovies.setAdapter(adapter);
        });
    }

    private void observeUpComing() {
        model.getUpComing(1, getString(R.string.action_up_coming)).observe(this, movie -> {
            adapter = new MoviesAdapter(movie.results, this, getContext());
            fragmentMainBinding.rvMovies.setLayoutManager(new GridLayoutManager(getContext(), 3));
            fragmentMainBinding.rvMovies.setAdapter(adapter);
        });
    }

    private void setTitle() {
        if (null != ((AppCompatActivity) getActivity()).getSupportActionBar()) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Movies");
            setHasOptionsMenu(true);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.popular:
                observePopular();
                break;
            case R.id.top_rated:
                observeTopRated();
                break;
            case R.id.up_coming:
                observeUpComing();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMovieClick(Movie.results movie) {
        Bundle args = new Bundle();
        args.putInt("id", movie.id);
        NavHostFragment.findNavController(this).navigate(R.id.movieDetailsFragment, args);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        model.dispose();
    }
}
