package kr.hs.emirim.festivalwhere.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import kr.hs.emirim.festivalwhere.FestivalDetailActivity;
import kr.hs.emirim.festivalwhere.MConfig;
import kr.hs.emirim.festivalwhere.MonthlyFragment;
import kr.hs.emirim.festivalwhere.R;
import kr.hs.emirim.festivalwhere.models.Festival;
import kr.hs.emirim.festivalwhere.viewholder.FestivalViewHolder;


public class Cal_Fragment extends Fragment {

    private DatabaseReference mDatabase;

    private FirebaseRecyclerAdapter<Festival, FestivalViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    private static final String TAG = MConfig.TAG;
    private static final String NAME = "Cal_MainActivity";
    private final String CLASS = NAME + "@" + Integer.toHexString(hashCode());

    private TextView thisMonthTv;

    public Cal_Fragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        View rootview = inflater.inflate(R.layout.cal_activity_main, container, false);
        thisMonthTv = (TextView) rootview.findViewById(R.id.this_month_tv);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mRecycler = (RecyclerView) rootview.findViewById(R.id.messages_list);
        mRecycler.setHasFixedSize(true);

        ViewPager viewPager = (ViewPager) rootview.findViewById(R.id.viewPager);
        /** Important: Must use the child FragmentManager or you will see side effects. */
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        return rootview;
    }

    public static class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public Fragment getItem(int position) {
            Bundle args = new Bundle();
            MonthlyFragment monthlyFragment = new MonthlyFragment();
            return monthlyFragment;

            //args.putInt(ChildFragment.POSITION_KEY, position);
            //return ChildFragment.newInstance(args);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Child Fragment " + position;
        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Set up Layout Manager, reverse layout
        mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        Query postsQuery = getQuery(mDatabase);

        mAdapter = new FirebaseRecyclerAdapter<Festival, FestivalViewHolder>(Festival.class, R.layout.item_post,
                FestivalViewHolder.class, postsQuery) {
            @Override
            protected void populateViewHolder(final FestivalViewHolder viewHolder, final Festival model, final int position) {
                final DatabaseReference postRef = getRef(position);

                final String festivalKey = postRef.getKey();

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(getActivity().getApplicationContext(), "postKey:" + festivalKey, Toast.LENGTH_SHORT).show();
                        // Launch PostDetailActivity
                        Intent intent = new Intent(getActivity(), FestivalDetailActivity.class);
                        intent.putExtra(FestivalDetailActivity.EXTRA_FESTIVAL_KEY, festivalKey);
                        startActivity(intent);
                    }
                });

//                // Determine if the current user has liked this post and set UI accordingly
//                if (model.stars.containsKey(getUid())) {
//                    viewHolder.starView.setImageResource(R.drawable.ic_toggle_heart_24);
//                } else {
//                    viewHolder.starView.setImageResource(R.drawable.ic_toggle_heart_outline_24);
//                }

                viewHolder.bindToPost(model, null);/*
                        0, new View.OnClickListener() {
                    @Override
                    public void onClick(View starView) {
//                        // Need to write to both places the post is stored
//                        DatabaseReference globalPostRef = mDatabase.child("posts").child(postRef.getKey());
//                        DatabaseReference userPostRef = mDatabase.child("user-posts").child(model.uid).child(postRef.getKey());
//
//                        // Run two transactions
//                        onStarClicked(globalPostRef);
//                        onStarClicked(userPostRef);
                    }
                });*/
            }
        };
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mAdapter != null) {
            mAdapter.cleanup();
        }
    }

    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public Query getQuery(DatabaseReference databaseReference) {
        // [START my_top_posts_query]
        // My top posts by number of stars
        Query festivalQuery = databaseReference.child("festivals");
//        String myUserId = getUid();
//        Query myTopPostsQuery = databaseReference.child("user-posts").child(myUserId)
//                .orderByChild("starCount");
        // [END my_top_posts_query]

        return festivalQuery;
    }
}
