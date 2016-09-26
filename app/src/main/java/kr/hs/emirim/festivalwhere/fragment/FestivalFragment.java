package kr.hs.emirim.festivalwhere.fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class FestivalFragment extends FestivalListFragment {

    public FestivalFragment() {
    }

    @Override
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
