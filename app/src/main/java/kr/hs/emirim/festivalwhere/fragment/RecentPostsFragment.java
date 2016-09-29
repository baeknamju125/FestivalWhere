package kr.hs.emirim.festivalwhere.fragment;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import kr.hs.emirim.festivalwhere.BaseActivity;
import kr.hs.emirim.festivalwhere.models.Festival;
import kr.hs.emirim.festivalwhere.models.User;

public class RecentPostsFragment extends FestivalListFragment {
    HashMap<String, Boolean> festivals = new HashMap<>();
    public RecentPostsFragment() {
    }

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // [START recent_posts_query]
        // Last 100 posts, these are automatically the 100 most recent
        // due to sorting by push() keys
        Query recentPostsQuery = databaseReference.child("users-festivals").child(((BaseActivity)getActivity()).getUid()).child( "/2016/10");
        Toast.makeText(getActivity(), "users-festivals/"+ ((BaseActivity)getActivity()).getUid() + "/2016/10", Toast.LENGTH_SHORT).show();;

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        final String userId = ((BaseActivity)getActivity()).getUid();
        mDatabase.child("users").child(userId).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        User user = dataSnapshot.getValue(User.class);
                        Log.d("축제", "으아으아" + user.username );
                        // ...
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("축제", "getUser:onCancelled", databaseError.toException());
                        // ...
                    }
                });
        Log.d("축제", "users-festivals/" + ((BaseActivity)getActivity()).getUid() +  "/2016/10");

        mDatabase.child("users-festivals").child(((BaseActivity)getActivity()).getUid()).child( "2016").child("10")
                .addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        for (DataSnapshot festivalSnapshot : dataSnapshot.getChildren()) {
                            // TODO: handle the post


                            Festival festival = festivalSnapshot.getValue(Festival.class);
                            if (festival == null) return;
                            Log.d("축제", festival.name);
                            festivals.put(festival.date_start, true);
                            festivals.put(festival.date_end, true);
                            int startYear = Integer.parseInt(festival.date_start.substring(0,4));
                            int endYear = Integer.parseInt(festival.date_end.substring(0,4));
                            int startMon = Integer.parseInt(festival.date_start.substring(5,7));
                            int startDay = Integer.parseInt(festival.date_start.substring(8,10));
                            int endMon = Integer.parseInt(festival.date_end.substring(5,7));
                            int endDay = Integer.parseInt(festival.date_end.substring(8,10));
                            if(startMon < endMon){
                                Log.d("축제", "여러달에 걸친 일정!:" + startMon + "월에서 " + endMon + "월까지!");
                                switch(startMon){
                                    case 1: // 31일인 달들
                                    case 3:
                                    case 5:
                                    case 7:
                                    case 9:
                                    case 11:
                                        for(int i = startDay; i <= 31; i++){

                                            festivals.put( "" + startYear + "-" +  ( (startMon < 10)? "0" : "") + startMon + "-" + ( (i < 10)? "0" : "") + i , true);
                                        }
                                        for(int i = 1; i <= endDay; i++){
                                            festivals.put( "" + startYear + "-" +  ( (startMon < 10)? "0" : "") + endMon + "-" + ( (i < 10)? "0" : "") + i , true);
                                        }
                                        break;
                                    case 2:  // 28일까지로 가정
                                        for(int i = startDay; i <= 28; i++){

                                            festivals.put( "" + startYear + "-" +  ( (startMon < 10)? "0" : "") + startMon + "-" + ( (i < 10)? "0" : "") + i , true);
                                        }
                                        for(int i = 1; i <= endDay; i++){
                                            festivals.put( "" + startYear + "-" +  ( (startMon < 10)? "0" : "") + endMon + "-" + ( (i < 10)? "0" : "") + i , true);
                                        }
                                        break;
                                    case 4:  // 30일까지인 달들
                                    case 6:
                                    case 8:
                                    case 10:
                                    case 12:
                                        for(int i = startDay; i <= 30; i++){

                                            festivals.put( "" + startYear + "-" +  ( (startMon < 10)? "0" : "") + startMon + "-" + ( (i < 10)? "0" : "") + i , true);
                                        }
                                        for(int i = 1; i <= endDay; i++){
                                            festivals.put( "" + startYear + "-" +  ( (startMon < 10)? "0" : "") + endMon + "-" + ( (i < 10)? "0" : "") + i , true);
                                        }
                                        break;
                                }


                            }

                            Log.d("축제", "========================================");
                            Log.d("축제", festivals.toString());
                            Log.d("축제", festival.date_start + " ~ " + festival.date_end );
                            Log.d("출제", "========================================");

                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("축제", "getUser:onCancelled", databaseError.toException());
                        // ...
                    }
                });


        //        .limitToFirst(100);
        // [END recent_posts_query]

        return recentPostsQuery;
    }
}
