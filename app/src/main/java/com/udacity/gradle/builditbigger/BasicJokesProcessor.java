package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.mkd.build.it.bigger.backend.myApi.MyApi;
import com.mkd.build.it.bigger.jokeviewer.JokeViewerActivity;

import java.io.IOException;

/**
 * Created by mkdin on 17-12-2016.
 */

public class BasicJokesProcessor extends Fragment {
    ProgressBar progressBar;
    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        progressBar = (ProgressBar) root.findViewById(R.id.prog_joke);
        progressBar.setVisibility(View.GONE);
        return root;
    }*/

    public void getAndDisplayJoke(Context context, ProgressBar progBar) {
        progressBar = progBar;
        new BasicAsyncTask().execute(context);
    }


    private class BasicAsyncTask extends AsyncTask<Context, Void, String> {

        private  MyApi jokesApiServices = null;
        private Context context;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Context... params) {

            if (jokesApiServices == null) {
                final MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });

                jokesApiServices = builder.build();
            }

            context = params[0];

            try {
                return jokesApiServices.retrieveJoke().execute().getData();
            } catch (IOException e) {
                return e.getMessage();
            }


        }

        @Override
        protected void onPostExecute(String joke) {
            final Intent intent = new Intent(context, JokeViewerActivity.class);
            intent.putExtra(Intent.EXTRA_TEXT, joke);
            context.startActivity(intent);
            progressBar.setVisibility(View.GONE);
        }
    }
}