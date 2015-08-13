# RecyclerViewAd
### Description
RecyclerViewAd is an example application that displays an embedded AdView inside a RecyclerView. 

Usage
-----
First you need to add the required dependencies:

```dependencies {
    compile 'com.android.support:recyclerview-v7:22.2.1'
    compile 'com.google.android.gms:play-services-ads:7.5.0'
}```

How to implement AdView in a RecyclerView?
------------------------------------------
First in your Activity, instantiate a RecyclerView as normal: 
```
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] data = {"ksjdahfjak;","feawjkhfew","flosujfeka","hjgweafiwaf",
                        "oneahfewa", "mowaforw", "hfejwafewa","fewalfuewaf"};

        mRV = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this, data);
        mRV.setAdapter(adapter);
        mRV.setLayoutManager(new LinearLayoutManager(this));

    }
```
Our RecyclerView will only contain one TextView for each row. However, you could do interesting things inside the RecyclerView adapter like embed native ads (something like Twitter or Instagram).

Next you need to define a custom adapter for the RecyclerView. I called mine MyAdapter (Reference: https://developer.android.com/intl/ko/training/material/lists-cards.html). 

```
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private String[] mDataset;
    private LayoutInflater inflater;
    private Activity mainActivity;
    int AD_TYPE = 0;
    int CONTENT_TYPE = 1;


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            if (!(itemView instanceof AdView)) {
                mTextView = (TextView) v.findViewById(R.id.text);
            }
        }
    }

    public MyAdapter(Context context, String[] myDataset) {
        inflater = LayoutInflater.from(context);
        mDataset = myDataset;
        mainActivity = (Activity) context;
    }

    ...
    ...
}```

In the custom Adapter we can choose the frequency of AdViews that are being displayed by Overriding getItemViewType. Below I set the freqency of adviews at 1 every 6 rows starting at the 4th row.
```
    @Override
    public int getItemViewType(int position) {
        if (position % 6 == 3)
            return AD_TYPE;
        return CONTENT_TYPE;
    }```

To make sure we don't crash our app when we assign values to the rows do the following. This will make sure we are only assigning values to valid views. 
```
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(position % 6 != 3) {
            holder.mTextView.setText(mDataset[position]);
        }
    }
```

Now in the onCreateViewHolder method we need to make sure we load everything correctly:
```
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

```

And now you should have a nice AdView inside your RecyclerView! 



