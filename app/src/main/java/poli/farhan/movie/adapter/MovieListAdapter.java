package poli.farhan.movie.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.Callback;

import poli.farhan.movie.R;
import poli.farhan.movie.model.Movie;
import poli.farhan.movie.model.MovieData;
import poli.farhan.movie.network.Constant;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {
    private  Context context;
    private List<MovieData>movieDatas;

    public MovieListAdapter(Context context) {
        this.context = context;
        movieDatas = new ArrayList<>();
    }
    private void add(MovieData item){
        movieDatas.add(item);
        notifyItemInserted(movieDatas.size()-1);
    }
    public void addAll(List<MovieData> movieDatas){
        for (MovieData movieData:movieDatas){
            add(movieData);
        }
    }
    public void remove (MovieData item){
        int position = movieDatas.indexOf(item);
        if (position>-1){
            movieDatas.remove(position);
            notifyItemRemoved(position);
        }

    }
    public void  clear(){
        while (getItemCount()>0){
            remove(getItem(0));
        }
    }

    private MovieData getItem(int i) {
        return movieDatas.get(i);
    }

    @NonNull
    @Override
    public  MovieListAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_movie,viewGroup,false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.MovieViewHolder movieViewHolder, int i) {

        final  MovieData movieData = movieDatas.get(i);

        movieViewHolder.bind(movieData);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView)itemView.findViewById(R.id.img_data);
        }

        public void bind(MovieData movieData) {
            Picasso.get().load(Constant.IMG_URL+movieData.getProfile_path())
                    .into(img);
        }
    }


}
