package org.androidtown.simple_hw2_list;
/*
ID_NUMBER: 201333493
Name: 최지영
Description:
Use ListView & Adapter for the List of HWs (1~3) and ChangePwd (4 th )
Every list item should contain a proper icon/image for each HW (e.g., time-table,
calculator.. )and each of them is used to launch its corresponding Activity (HW1a/b/c,
change-password Activity)
There is a button on the description Activity for HW1a/b/c to execute
the corresponding HW Activity (that you have made in the HW1a~c)
#HW 1-3 : finish when “=“ is pressed, and return the calculation result
to the description Activity where you will show the result with a Toast
message
*/

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    private ListView myListView = null;
    private ListViewAdapter myAdapter = null;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myListView = (ListView) findViewById(R.id.my_List);

        myAdapter = new ListViewAdapter(this);
        myListView.setAdapter(myAdapter);

        myAdapter.addItem(getResources().getDrawable(R.drawable.timetable), "Time-table");//to add the image in aach icon.
        myAdapter.addItem(getResources().getDrawable(R.drawable.tipcal), "Tip-Counter");
        myAdapter.addItem(getResources().getDrawable(R.drawable.cal), "Calculator");
        myAdapter.addItem(null, "Change Pwd");//no image

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//the click method in ListView

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ListData mData = myAdapter.mListData.get(position);
                if (position == 0) { //To move the timetable activity When the user click the first value of listview
                    Intent intent0 = new Intent(MainActivity.this, Timetable.class);
                    startActivity(intent0);
                } else if (position == 1) {
                    Intent intent1 = new Intent(MainActivity.this, Simple_Tip_Calculator.class);
                    startActivity(intent1);
                } else if (position == 2) {
                    Intent intent2 = new Intent(MainActivity.this, descript_result.class);
                    startActivityForResult(intent2, 101);
                } else {
                    Intent intent3 = new Intent(MainActivity.this, Change_password.class);
                    startActivity(intent3);
                }
            }
        });
    }

    private class ViewHolder {//to manage the view (image and text view) like putting in the holder
        public ImageView mIcon;
        public TextView mText;

    }

    private class ListViewAdapter extends BaseAdapter {
        private Context mContext = null;
        private ArrayList<ListData> mListData = new ArrayList<ListData>();//to arraylist for list data

        public ListViewAdapter(Context mContext) { //listview adapter
            super();
            this.mContext = mContext;
        }

        @Override
        public int getCount() { //to count the list data
            return mListData.size();
        }

        @Override
        public Object getItem(int position) { // return to get each of the list item
            return mListData.get(position);
        }

        @Override
        public long getItemId(int position) { //return to get each of the list item position
            return position;
        }

        public void addItem(Drawable icon, String mTitle) { // to add the data icon and data name(title)
            ListData addInfo = null;
            addInfo = new ListData();
            addInfo.mIcon = icon;
            addInfo.mTitle = mTitle;

            mListData.add(addInfo);// to add the data information in list.
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) { // to implement the view object putting in actual list view.
            ViewHolder holder;
            if (convertView == null) { // convertview that is delivererd the each item of list view by android.
                holder = new ViewHolder();

                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  // read the layout information to make layout object.
                convertView = inflater.inflate(R.layout.item_list, null);

                holder.mIcon = (ImageView) convertView.findViewById(R.id.imageView);//to put icon in the holder.
                holder.mText = (TextView) convertView.findViewById(R.id.textView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            ListData mData = mListData.get(position);

            if (mData.mIcon != null) {//if icon don't have null value
                holder.mIcon.setVisibility(View.VISIBLE);//to set visible
                holder.mIcon.setImageDrawable(mData.mIcon);//to set image in icon
            } else {
                holder.mIcon.setVisibility(View.GONE);
            }

            holder.mText.setText(mData.mTitle);//to set the title of list view.

            return convertView;
        }
    }

    public static class ListData { //To create the object to have a list information

        public Drawable mIcon;
        public String mTitle;

        public static final Comparator<ListData> ALPHA_COMPARATOR = new Comparator<ListData>() {//to sort by lexicographic order
            private final Collator sCollator = Collator.getInstance();

            @Override
            public int compare(ListData mListDate_1, ListData mListDate_2) {
                return sCollator.compare(mListDate_1.mTitle, mListDate_2.mTitle);
            }
        };
    }

}

