package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.karthik.self.jokeandroidlibrary.JokeActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements JokeDelegate {
    private ProgressBar spinner;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        spinner = (ProgressBar)root.findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);
        root.findViewById(R.id.joke_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setVisibility(View.VISIBLE);
                new JokeDownloader(MainActivityFragment.this)
                        .execute(new Pair<Context, String>(getActivity(), "Karthik"));
            }
        });
        return root;
    }

    @Override
    public void downloadComplete(String joke) {
        Intent intent = new Intent(getActivity(), JokeActivity.class);
        intent.putExtra("joke", joke);
        spinner.setVisibility(View.GONE);
        startActivity(intent);
    }
}
