package com.jaaz.zacharyrice.recyclerviewad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRV;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] data = {"ksjdahfjak;","feawjkhfew","flosujfeka","hjgweafiwaf",
                        "oneahfewa", "mowaforw", "hfejwafewa","fewalfuewaf",
                        "ksjdahfjak;","feawjkhfew","flosujfeka","hjgweafiwaf",
                        "oneahfewa", "mowaforw", "hfejwafewa","fewalfuewaf"};

        mRV = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this, data);
        mRV.setAdapter(adapter);
        mRV.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
