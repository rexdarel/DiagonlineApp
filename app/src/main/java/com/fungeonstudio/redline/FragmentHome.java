package com.fungeonstudio.redline;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.fungeonstudio.redline.recycler.HospitalAdapter;
import com.fungeonstudio.redline.recycler.ItemHospital;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Dytstudio.
 */

public class FragmentHome extends Fragment{
    private List<ItemHospital> hospitals = new ArrayList<>();
    private RecyclerView recyclerView;
    private HospitalAdapter mAdapter;
    private AppCompatActivity appCompatActivity;
    private ProgressBar progressBar;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public FragmentHome(){
        setHasOptionsMenu(true);
    }
    public void onCreate(Bundle a){
        super.onCreate(a);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null, false);

        ((MainActivity)getActivity()).setupToolbar(R.id.toolbar, "DESSERT", R.color.colorPink, R.color.colorWhiteTrans, R.drawable.ic_burger);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBarHome);

        mAdapter = new HospitalAdapter(setupRecipe(), getActivity());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        mLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        appCompatActivity = (AppCompatActivity) getActivity();

        return view;
    }


    private List<ItemHospital> setupRecipe(){
        hospitals = new ArrayList<>();

        databaseReference.child("hospitals").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    for(DataSnapshot postSnapShot:dataSnapshot.getChildren())
                    {
                        ItemHospital hospital = postSnapShot.getValue(ItemHospital.class);
                        hospitals.add(hospital);
                        mAdapter.notifyDataSetChanged();
                    }
                }
                //progressBar.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                //hideProgressDialog();
            }
        });

        String recipe[] = {"BLOOD ORANGE CAKE", "SEMIFREDDO TIRAMISU", "MARBLE CAKE", "RICE PUDDING", "RAINBOW CAKE", "ICE CREAM", "STROWBERRY CAKE", "CUPCAKE FRUIT"};
        String img[] = {"https://images.pexels.com/photos/53468/dessert-orange-food-chocolate-53468.jpeg?h=350&auto=compress&cs=tinysrgb",
                "https://images.pexels.com/photos/159887/pexels-photo-159887.jpeg?h=350&auto=compress",
                "https://images.pexels.com/photos/136745/pexels-photo-136745.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb",
                "https://images.pexels.com/photos/39355/dessert-raspberry-leaf-almond-39355.jpeg?h=350&auto=compress&cs=tinysrgb",
                "https://images.pexels.com/photos/239578/pexels-photo-239578.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb",
                "https://images.pexels.com/photos/8382/pexels-photo.jpg?w=1260&h=750&auto=compress&cs=tinysrgb",
                "https://images.pexels.com/photos/51186/pexels-photo-51186.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb",
                "https://images.pexels.com/photos/55809/dessert-strawberry-tart-berry-55809.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb"};
        String time[] = {"1h 5'", "30m", "1h 10'", "50m", "20m", "1h 20'", "20m", "1h 20'"};
        float rating[] = {3, 4, 4, 3, 5, 4, 4, 3};

        return hospitals;
    }


    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_home, menu);
    }
}
