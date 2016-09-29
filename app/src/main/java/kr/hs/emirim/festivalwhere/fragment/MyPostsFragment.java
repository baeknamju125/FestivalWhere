package kr.hs.emirim.festivalwhere.fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class MyPostsFragment extends FestivalListFragment {

    public MyPostsFragment() {
    }

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // All my posts
        return databaseReference.child("user-festivals");
                //.child(getUid());
    }
}
