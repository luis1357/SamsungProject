package com.ruisu1357.samsungproject.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ruisu1357.samsungproject.R;
import com.ruisu1357.samsungproject.data.remote.models.ImageResponse;
import com.ruisu1357.samsungproject.databinding.ImageItemBinding;
import com.ruisu1357.samsungproject.ui.activities.DetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.ruisu1357.samsungproject.utils.Constants.KEY_IMAGE_RESPONSE;

public class ImageRecyclerViewAdapter extends
        RecyclerView.Adapter<ImageRecyclerViewAdapter.ViewHolder>
{
    private Context context;
    private ArrayList<ImageResponse> imageResponse = new ArrayList<>();

    public static final String TAG = "RecyclerView Adapter: ";


    public ImageRecyclerViewAdapter(Context context, ArrayList<ImageResponse> imageResponse)
    {
        this.context = context;
        this.imageResponse = imageResponse;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position)
    {

        ImageItemBinding myBinder =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.image_item,
                        viewGroup,
                        false);

        return new ViewHolder(myBinder);

        //return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position)
    {
        ImageResponse imageResponseItem = imageResponse.get(position);
        Picasso.get().load(imageResponseItem.getThumbnailUrl()).into(viewHolder.myBinder.image1);
        viewHolder.setImageResponse(imageResponseItem);
    }

    @Override
    public int getItemCount() {
        return imageResponse.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageItemBinding myBinder;
        ImageResponse imageResponse;

        ImageView imageView;

        //ImageView imgImage;

        public ViewHolder(ImageItemBinding imageItemBinding)
        {
            super(imageItemBinding.getRoot());
            this.myBinder = imageItemBinding;

            imageView = myBinder.image1;

            imageView.setOnClickListener(this);
        }

        /*
        public ViewHolder(View itemView) {
            super(itemView);
            imgImage = itemView.findViewById(R.id.image1);
        }*/

        public void setImageResponse(ImageResponse imageResponse) {
            this.imageResponse = imageResponse;
        }

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(v.getContext(), DetailsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable(KEY_IMAGE_RESPONSE, imageResponse);
            intent.putExtras(bundle);
            v.getContext().startActivity(intent);

            Log.d(TAG, "onClick: " + bundle.toString());
        }

    }
}
