package com.madebyasshad.complainretriveapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ListView compalinoflist;
    Intent i;
    String value;

    ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<String> arrayofname=new ArrayList<>();
    ArrayList<String> arrayofcomplain=new ArrayList<>();
    ArrayList<getdata> jsoncollectdata=new ArrayList<>();
    HashMap<String,String> map =new HashMap<>();

    FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference dbref=db.getReference();
    DatabaseReference usser=dbref.child("Users");


    ArrayAdapter<String> arrayAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        compalinoflist=findViewById(R.id.listviewofcomplain);
        arrayAdapter=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList);

        compalinoflist.setAdapter(arrayAdapter);
        compalinoflist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(),"you clicked on"+id,Toast.LENGTH_SHORT).show();
                i=new Intent(getApplicationContext(),complaincontent.class);
                i.putExtra("name",value);

                startActivity(i);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        getdata();
        Log.i("datadddd","isof hashmap"+map);
        Log.i("datadddd","isof arraylisst"+arrayList);
//        itemcalss currentItem = mylistofitem.get(position);

//        getdata gt=jsoncollectdata.get(1);
//        Log.i("datadddd","isof json collection"+gt);

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




    public void getdata()
    {


        usser.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
              Log.i("data","apnshot is"+dataSnapshot);
               if (dataSnapshot.getValue().toString()!=null)
               {
                   value=dataSnapshot.getValue().toString();
                   try {
                       JSONObject jsonObject=new JSONObject(value);
                       Log.i("jsonobjewect","adad"+jsonObject);
//                       mylistofitem.add(new itemcalss(q,a));

//                       jsoncollectdata.add(new getdata(jsonObject));
                       String Complainname=jsonObject.getString("Name");
                       String Complaintitle=jsonObject.getString("title");
                       String Complaincontent=jsonObject.getString("Complain");
//                       map.put("complain",Complain);
                       arrayList.add(Complaintitle);
                       arrayofcomplain.add(Complaincontent);
                       arrayofname.add(Complainname);
                       arrayAdapter.notifyDataSetChanged();

                   } catch (JSONException e) {
                       e.printStackTrace();
                   }
               }
               else
               {
                   Toast.makeText(getApplicationContext(),"Something Wents Wrong",Toast.LENGTH_SHORT).show();
               }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }


}
