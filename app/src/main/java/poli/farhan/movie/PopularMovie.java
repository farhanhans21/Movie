package poli.farhan.movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;
import java.net.SocketTimeoutException;

import poli.farhan.movie.adapter.MovieListAdapter;
import poli.farhan.movie.model.Movie;
import poli.farhan.movie.network.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularMovie extends AppCompatActivity {
    MovieListAdapter movieListAdapter;
    RecyclerView recyclerView;
    private int page=1;
    GridLayoutManager gridLayoutManager;

    private ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_movie);


        movieListAdapter = new MovieListAdapter(this);
        recyclerView = (RecyclerView)findViewById(R.id.rv_movies);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(movieListAdapter);

        loadData();

    }

    private void loadData() {
        apiService = new ApiService();
        apiService.getPopularMovie(page, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Movie movie = (Movie) response.body();
                if (movieListAdapter != null) {
                    movieListAdapter.addAll(movie.getResults());
                } else {
                    Toast.makeText(getBaseContext(), "Tidak ada Data", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    Toast.makeText(getBaseContext(), "Request Time Out", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getBaseContext(), "Connection Error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

