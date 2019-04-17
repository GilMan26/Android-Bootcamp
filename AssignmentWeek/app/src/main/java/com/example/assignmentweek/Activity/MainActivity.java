package com.example.assignmentweek.Activity;

import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.Toast;

import com.example.assignmentweek.Adapter.CustomAdapter;
import com.example.assignmentweek.DataManager;
import com.example.assignmentweek.Fragments.AddDialog;
import com.example.assignmentweek.Fragments.DetailsFragment;
import com.example.assignmentweek.Fragments.UpdateDialog;
import com.example.assignmentweek.Interfaces.IAddUser;
import com.example.assignmentweek.Interfaces.IApiReponseListener;
import com.example.assignmentweek.Interfaces.IUpdateUser;
import com.example.assignmentweek.Interfaces.IUserTouchListener;
import com.example.assignmentweek.R;
import com.example.assignmentweek.Request.UserRequest;
import com.example.assignmentweek.Response.CreateResponse;
import com.example.assignmentweek.Response.Datum;
import com.example.assignmentweek.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IApiReponseListener, IUserTouchListener, IAddUser, IUpdateUser {
    static int page = 1;
    ActivityMainBinding binding;
    CustomAdapter adapter;
    DataManager dataManager;
    List<Datum> list;
    LinearLayoutManager linearLayoutManager;
    private boolean isScrolling = false;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        linearLayoutManager = new LinearLayoutManager(this);
        list = new ArrayList<>();
        fragmentManager = getSupportFragmentManager();
        dataManager = new DataManager(this);
        dataManager.setReponseListener(this);
        adapter = new CustomAdapter(list);
        adapter.setTouchListener(this);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setAdapter(adapter);
        new ListCallTask().execute();
        binding.recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    Log.d("isScrolling", "Hello there");
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItems = linearLayoutManager.getItemCount();
                int visibleItems = linearLayoutManager.getChildCount();
                int scrolledOutItems = linearLayoutManager.findFirstVisibleItemPosition();
                Log.d("shit", totalItems + "" + visibleItems + "" + scrolledOutItems);
                if (isScrolling && (visibleItems + scrolledOutItems) == totalItems) {
                    Log.d("test", "im here");
                    if (page < 4) {
                        page++;
                        Toast.makeText(MainActivity.this, "loading more...", Toast.LENGTH_SHORT).show();
                        isScrolling = false;
                        new ListCallTask().execute();
                        Log.d("test2", "still here");
                    }

                }
            }
        });
    }


//            @Override
//            public void onResponse(Call<DummyData> call, Response<DummyData> response) {
//                if(!response.isSuccessful()){
//                    Toast.makeText(getApplicationContext(), response.code()+"",Toast.LENGTH_LONG).show();
//                }
//                DummyData data=response.body();
//                Log.d("test", data.toString());
//                list.addAll(data.getPosts());
//                customAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<DummyData> call, Throwable t) {
////                httpButton.setText(t.getMessage());
//                Log.d("fail", "failed");
//            }
//        });

//        public void callApi() {
//            APIInterface apiInterface =RetrofitClient.getRetrofitInstance().create(APIInterface.class);
//            Call<ListReponse> call = apiInterface.getAllUsers(page);
//            call.enqueue(new Callback<ListReponse>() {
//                @Override
//                public void onResponse(Call<ListReponse> call, Response<ListReponse> response) {
//                    if (!response.isSuccessful()) {
//                        Toast.makeText(getApplicationContext(), response.code() + "", Toast.LENGTH_LONG).show();
//                    }
//                    ListReponse listReponse = response.body();
//                    list.addAll(listReponse.getData());
//                    adapter.notifyDataSetChanged();
//                }
//
//                @Override
//                public void onFailure(Call<ListReponse> call, Throwable t) {
//
//                }
//
//            });
//        }

    @Override
    public void dataListResponse(List<Datum> rList) {
        list.addAll(rList);
        adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void addUsserResponse(CreateResponse createResponse) {
        Toast.makeText(this, createResponse.getCreatedAt(),Toast.LENGTH_LONG).show();
        Log.d("addUser", createResponse.getCreatedAt() + createResponse.getJob());
    }


    //add user button to add user placed on the toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.addMenu) {
            AddDialog dialog = new AddDialog();
            dialog.setInstance(this);
//            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//            fragmentTransaction.add(R.id.mainFrame, dialog);
//            fragmentTransaction.addToBackStack("dialog");
//            fragmentTransaction.commit();
//            UserRequest request=new UserRequest("job" , "name");
//            dataManager.addUsser(request);
            dialog.show(getSupportFragmentManager(), null);
        }
        return super.onOptionsItemSelected(item);

    }

    //handles item click and adds a fragment on top of the list with the details of the item clicked
    @Override
    public void getClickedUser(Datum datum) {
        Bundle bundle = new Bundle();
        bundle.putString("fname", datum.getFirstName());
        bundle.putString("lname", datum.getLastName());
        bundle.putString("avatar", datum.getAvatar());
        bundle.putLong("id", datum.getId());
        DetailsFragment detailsFragment = new DetailsFragment();
        detailsFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.mainFrame, detailsFragment);
        fragmentTransaction.addToBackStack("details");
        fragmentTransaction.commit();
    }

    //long click handled here to delete the item long clicked
    @Override
    public void deleteUser(long id) {
        new DeleteTask().execute(id);
//        UserRequest request=new UserRequest("job" , "name");
//        new UpdateTask(request).execute(id);
    }

    @Override
    public void updateUser(long id) {
        UpdateDialog dialog=new UpdateDialog();
        Bundle bundle=new Bundle();
        bundle.putLong("id", id);
        dialog.setArguments(bundle);
        dialog.setInstance(this);
        dialog.show(getSupportFragmentManager(), null );

    }

    @Override
    public void addUser(UserRequest request) {
        dataManager.addUsser(request);

    }

    @Override
    public void updateUser(long id, UserRequest request) {
        dataManager.updateUser(id, request);
    }


    //api call to delete a user in an async task
    class DeleteTask extends AsyncTask<Long, Void, Void> {
        @Override
        protected Void doInBackground(Long... longs) {
            dataManager.deleteUser(longs[0]);
            return null;
        }
    }

    //api call to update user in an async task
    class UpdateTask extends AsyncTask<Long, Void, Void> {
        UserRequest request;

        public UpdateTask(UserRequest request) {
            this.request = request;
        }

        @Override
        protected Void doInBackground(Long... longs) {
            dataManager.updateUser(longs[0], request);
            return null;
        }
    }

    //api call to get the list of users for a partcular page no
    class ListCallTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            dataManager.getUserList(page);
            return null;
        }
    }
}

