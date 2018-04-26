package com.anditer.goalit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.anditer.goalit.fragments.GoalFragment;
import com.anditer.goalit.fragments.TaskFragment;
import com.anditer.goalit.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private boolean userPressedBackAgain = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpToolbar();
        setupViewPager();
        setupTabLayout();

    }

    private void setUpToolbar(){
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextAppearance(this,R.style.TolBarStyle);
        setSupportActionBar(toolbar);

    }

    private void setupViewPager() {
        viewPager = findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TaskFragment(), "Today's Tasks");
        adapter.addFragment(new GoalFragment(), "Goals");
        viewPager.setAdapter(adapter);
    }

    private void setupTabLayout(){
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.profileMenu:
                startProfileActivity();
                return true;
            default:
                return false;
        }

    }

    private void startProfileActivity(){
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {

        if (!userPressedBackAgain) {
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
            userPressedBackAgain = true;
        } else {
            moveTaskToBack(true);
        }

        startPressedBackAgainResetCounter();

    }

    private void startPressedBackAgainResetCounter(){
        new CountDownTimer(3000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                userPressedBackAgain = false;
            }
        }.start();
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        private void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
