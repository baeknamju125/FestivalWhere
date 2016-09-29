/*
 * Copyright 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kr.hs.emirim.festivalwhere;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import kr.hs.emirim.festivalwhere.fragment.Cal_Fragment;
import kr.hs.emirim.festivalwhere.fragment.FestivalFragment;
import kr.hs.emirim.festivalwhere.fragment.MyPostsFragment;
import kr.hs.emirim.festivalwhere.fragment.MyTopPostFragment;
import kr.hs.emirim.festivalwhere.fragment.RecentPostsFragment;

public class  MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    private FragmentPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            private final Fragment[] mFragments = new Fragment[]{
                    new Cal_Fragment(),
                    new RecentPostsFragment(),
                    new FestivalFragment(),
                    new MyPostsFragment(),
                    new MyTopPostFragment(),
            };
            private final String[] mFragmentNames = new String[]{
                    "Home",
                    getString(R.string.heading_recent),
                    getString(R.string.heading_my_posts),
                    getString(R.string.heading_my_top_posts),
                    getString(R.string.handling_festival),
            };

            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }

            @Override
            public int getCount() {
                return mFragments.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mFragmentNames[position];
            }
        };
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_tab_schedule);
        tabLayout.getTabAt(0).setText(null);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_tab_festival);
        tabLayout.getTabAt(1).setText(null);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_tab_search);
        tabLayout.getTabAt(2).setText(null);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_tab_more);
        tabLayout.getTabAt(3).setText(null);
        for (int i = 0; i < tabLayout.getChildCount(); i++) {
            tabLayout.getChildAt(i).setPadding(0, 0, 0, 0);
        }
    }
}
//        // Button launches NewPostActivity
//        findViewById(R.id.fab_new_post).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, NewPostActivity.class));
//            }
//        });

        //Toast.makeText(getApplicationContext(), "uid야 나와라 "+ getUid(), Toast.LENGTH_SHORT).show();;

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int i = item.getItemId();
//        if (i == R.id.action_logout) {
//            FirebaseAuth.getInstance().signOut();
//            startActivity(new Intent(MainActivity.this, SignInActivity.class));//로그인화면 띄어주는 화면
//            finish();
//            return true;
//        } else {
//            return super.onOptionsItemSelected(item);
//        }
//    }
