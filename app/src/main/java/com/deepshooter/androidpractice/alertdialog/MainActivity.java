package com.deepshooter.androidpractice.alertdialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.deepshooter.androidpractice.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button alertButton ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setValues();

    }

    private void initView(){
        alertButton = findViewById(R.id.alertButton);
    }

    private void setValues(){

        alertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog();

            }
        });
    }


    public void showDialog() {

        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.layout_custom_dialog, null);
        ArrayList<String> dataArrayList = new ArrayList<>();
        dataArrayList.add("Sample Text 1");
        dataArrayList.add("Sample Text 2");
        dataArrayList.add("Sample Text 3");
        dataArrayList.add("Sample Text 4");
        RecyclerViewListAdapter recyclerViewListAdapter ;
        final RecyclerView recyclerView = alertLayout.findViewById(R.id.vR_recyclerViewList);

        recyclerViewListAdapter = new RecyclerViewListAdapter(MainActivity.this,dataArrayList);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(false);

        recyclerView.setAdapter(recyclerViewListAdapter);
        recyclerViewListAdapter.notifyDataSetChanged();


        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Custom Alert Dialog");
        alert.setView(alertLayout);
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Cancel", Toast.LENGTH_SHORT).show();
            }
        });

        alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(getBaseContext(), "Done", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }


    public class RecyclerViewListAdapter extends RecyclerView.Adapter<RecyclerViewListAdapter.MyViewHolder> {

        Context context;
        List<String> dataList;

        public RecyclerViewListAdapter(Context context, List<String> dataList) {
            this.context = context;
            this.dataList = dataList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder,  final int position) {

            holder.mText.setText(dataList.get(position));

        }

        @Override
        public int getItemCount() {

            if (dataList == null || dataList.size() == 0)
                return 0;
            return dataList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView mText;


            public MyViewHolder(View itemView) {
                super(itemView);


                mText = itemView.findViewById(R.id.textView);


            }
        }


    }

}
