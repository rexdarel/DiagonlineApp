package com.fungeonstudio.diagonline;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fungeonstudio.diagonline.R;
import com.fungeonstudio.diagonline.recycler.CommentsAdapter;
import com.fungeonstudio.diagonline.recycler.ItemComment;
import com.fungeonstudio.diagonline.recycler.ItemPreparation;
import com.fungeonstudio.diagonline.recycler.PreparationAdapter;
import com.fungeonstudio.diagonline.utils.CircleGlide;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DetailActivity extends AppCompatActivity implements PreparationAdapter.ViewHolder.ClickListener {

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private RecyclerView recyclerViewPreparation;
    private PreparationAdapter mAdapterPreparation;
    private RecyclerView recyclerViewComments;
    private CommentsAdapter mAdapterComments;
    private CoordinatorLayout rootView;

    private TextView tv_name, tv_desc, tv_location;
    private RatingBar ratingBar;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        rootView = (CoordinatorLayout) findViewById(R.id.rootview);
        setupToolbar(R.id.toolbar, "DESSERTS", android.R.color.white, android.R.color.transparent, R.drawable.ic_arrow_back);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitleEnabled(false);

        tv_name = (TextView) findViewById(R.id.tv_recipe_name);
        tv_desc = (TextView) findViewById(R.id.tv_desc);
        tv_location = (TextView) findViewById(R.id.tv_location);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        Intent i = this.getIntent();
        String name=i.getExtras().getString("NAME");
        String desc = i.getExtras().getString("DESC");
        String location = i.getExtras().getString("LOCATION");
        String photo = i.getExtras().getString("PHOTO");
        ratingBar.setRating(3);
        tv_name.setText(name);
        tv_desc.setText(desc);
        tv_location.setText(location);

        recyclerViewPreparation = (RecyclerView) findViewById(R.id.recyclerPreparation);

        mAdapterPreparation = new PreparationAdapter(getBaseContext(), generatePreparation(),this);
        LinearLayoutManager mLayoutManagerPreparation = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewPreparation.setLayoutManager(mLayoutManagerPreparation);
        recyclerViewPreparation.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPreparation.setAdapter(mAdapterPreparation);

        recyclerViewComments = (RecyclerView) findViewById(R.id.recyclerComment);

        mAdapterComments = new CommentsAdapter(generateComments(), this);
        LinearLayoutManager mLayoutManagerComment = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewComments.setLayoutManager(mLayoutManagerComment);
        recyclerViewComments.setItemAnimator(new DefaultItemAnimator());
        recyclerViewComments.setAdapter(mAdapterComments);


        final ImageView imageComment = (ImageView) findViewById(R.id.iv_user);
        Glide.with(this)
                .load(Uri.parse("https://randomuser.me/api/portraits/women/75.jpg"))
                .transform(new CircleGlide(this))
                .into(imageComment);

        final ImageView image = (ImageView) findViewById(R.id.image);
        Glide.with(this).load(photo).into(image);

    }

    @Override
    public void onItemClicked(int position) {

    }

    @Override
    public boolean onItemLongClicked(int position) {
        toggleSelection(position);
        return true;
    }

    private void toggleSelection(int position) {
        mAdapterPreparation.toggleSelection(position);
        final RelativeLayout rlShare = (RelativeLayout) findViewById(R.id.rl_share);
        if (position == mAdapterPreparation.getItemCount()-1) {

            Log.d("selected", "toggleSelection: "+position+" "+(mAdapterPreparation.getItemCount()-1));
            rlShare.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        Drawable drawable = menu.findItem(R.id.action_search).getIcon();

        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(this,android.R.color.white));
        menu.findItem(R.id.action_search).setIcon(drawable);
        return true;
    }
    public List<ItemComment> generateComments(){
        List<ItemComment> itemList = new ArrayList<>();
        String username[] = {"LAURA MAGNAGO"};
        String date[] = {".27-01-2017"};
        String comments[] = {"Made this for a BBQ today and it was amazing. Bought 2 Madiera cakes from Tesco and cut them Into wedges. Poured the coffee over the top. And used 75ml of Ameretti instead of masala in the cream. Will be making again next week for a gathering and probably many more times! :)"};
        String userphoto[] = {"https://randomuser.me/api/portraits/women/20.jpg"};
        String img1[] = {"https://images.pexels.com/photos/8382/pexels-photo.jpg?h=350&auto=compress&cs=tinysrgb"};
        String img2[] = {"https://images.pexels.com/photos/134574/pexels-photo-134574.jpeg?h=350&auto=compress&cs=tinysrgb"};

        for (int i = 0; i<username.length; i++){
            ItemComment comment = new ItemComment();
            comment.setUsername(username[i]);
            comment.setUserphoto(userphoto[i]);
            comment.setDate(date[i]);
            comment.setComments(comments[i]);
            comment.setImg1(img1[i]);
            comment.setImg2(img2[i]);
            itemList.add(comment);
        }
        return itemList;
    }

    public List<ItemPreparation> generatePreparation(){
        List<ItemPreparation> itemList = new ArrayList<>();
        String step[] = {"In a medium saucepan, whisk together egg yolks and sugar until well blended. Whisk in milk and cook over medium heat, stirring constantly, until mixture boils. Boil gently for 1 minute, remove from heat and allow to cool slightly. Cover tightly and chill in refrigerator 1 hour.",
                "In a medium bowl, beat cream with vanilla until stiff peaks form. Whisk mascarpone into yolk mixture until smooth.",
                "In a small bowl, combine coffee and rum. Split ladyfingers in half lengthwise and drizzle with coffee mixture.",
                "Arrange half of soaked ladyfingers in bottom of a 7x11 inch dish. Spread half of mascarpone mixture over ladyfingers, then half of whipped cream over that. Repeat layers and sprinkle with cocoa. Cover and refrigerate 4 to 6 hours, until set."};

        for (int i = 0; i<step.length; i++){
            ItemPreparation item = new ItemPreparation();
            item.setStep(step[i]);
            item.setNumber(String.valueOf(i+1));
            itemList.add(item);
        }
        return itemList;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void setupToolbar(int toolbarId, String title, @ColorRes int titleColor, @ColorRes int colorBg, @DrawableRes int burger){
        toolbar = (Toolbar) findViewById(toolbarId);
        toolbar.setBackgroundColor(getResources().getColor(colorBg));
        setSupportActionBar(toolbar);
        TextView pageTitle = (TextView) toolbar.findViewById(R.id.tv_title);
        pageTitle.setText("DIAGONLINE");
        pageTitle.setTextColor(getResources().getColor(titleColor));
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(burger);
        changeStatusBarColor();
    }
    public void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

}
