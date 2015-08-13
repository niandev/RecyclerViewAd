package com.jaaz.zacharyrice.recyclerviewad;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

/**
 * Created by zacharyrice on 8/13/15.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private String[] mDataset;
    private LayoutInflater inflater;
    private Activity mainActivity;
    int AD_TYPE = 0;
    int CONTENT_TYPE = 1;


    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;

        public ViewHolder(View v) {
            super(v);
            if (!(itemView instanceof AdView)) {
                mTextView = (TextView) v.findViewById(R.id.text);
            }
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(Context context, String[] myDataset) {
        inflater = LayoutInflater.from(context);
        mDataset = myDataset;
        mainActivity = (Activity) context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        AdView adview;
        ViewHolder holder;

        if (viewType == AD_TYPE) {
            adview = new AdView(mainActivity);
            adview.setAdSize(AdSize.BANNER);

            // this is the good adview
            adview.setAdUnitId(mainActivity.getString(R.string.aduid));

            float density = mainActivity.getResources().getDisplayMetrics().density;
            int height = Math.round(AdSize.BANNER.getHeight() * density);
            AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.FILL_PARENT, height);
            adview.setLayoutParams(params);

            // dont use below if testing on a device
            // follow https://developers.google.com/admob/android/quick-start?hl=en to setup testing device
            AdRequest request = new AdRequest.Builder().build();
            adview.loadAd(request);
            holder = new ViewHolder(adview);

        }else{
            View view = inflater.inflate(R.layout.custom_row, parent, false);
            holder = new ViewHolder(view);
        }
        return holder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(position % 6 != 3) {
            System.out.println(mDataset[position]);
            holder.mTextView.setText(mDataset[position]);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 6 == 3)
            return AD_TYPE;
        return CONTENT_TYPE;
    }
}

