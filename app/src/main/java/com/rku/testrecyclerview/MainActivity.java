package com.rku.testrecyclerview;

import android.os.Bundle;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    RecyclerView rcvUsers;
    RequestQueue requestQueue;
    JsonArrayRequest jsonArrayRequest;
    UserAdapter userAdapter;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcvUsers = findViewById(R.id.rcvUsers);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        rcvUsers.setLayoutManager(layoutManager);

        // Add Divider
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rcvUsers.getContext(), LinearLayoutManager.VERTICAL);
        rcvUsers.addItemDecoration(dividerItemDecoration);
        fetchAssetUserData();

        userAdapter = new UserAdapter(MyUtil.userData);
        rcvUsers.setAdapter(userAdapter);
        
    }

    private void fetchAssetUserData() {
        JSONArray jsonArray = null;
        try {
            InputStream is = getAssets().open("users.json");
            int size = 0;
            size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");
            Log.i("json", json);

            MyUtil.userData = new JSONArray(json);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

   
}