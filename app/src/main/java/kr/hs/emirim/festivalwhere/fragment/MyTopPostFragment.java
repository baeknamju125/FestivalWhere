package kr.hs.emirim.festivalwhere.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import kr.hs.emirim.festivalwhere.R;
import kr.hs.emirim.festivalwhere.SignInActivity;


public class MyTopPostFragment extends Fragment {
    private ListView lvMore;
    private ArrayList<String> moreList;
    private ArrayAdapter<String> moreAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_morepage, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvMore = (ListView) view.findViewById(R.id.fragment_more);
        moreList = new ArrayList<>();
        moreAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, moreList);
        lvMore.setAdapter(moreAdapter);
        moreList.add("계정 변경");
        moreList.add("앱 정보");
        moreList.add("스토어 평점");
        moreAdapter.notifyDataSetChanged();

        lvMore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String more = moreList.get(position);

                if ("계정 변경".equals(more)) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getActivity(), SignInActivity.class));
                } else if ("앱 정보".equals(more)) {
//                    Intent intent = new Intent(getActivity(), NoticeActivity.class);
//                    startActivity(intent);
                } else if ("스토어 평점".equals(more)) {
//                    Intent intent = new Intent(getActivity(), FindIdActivity.class);
//                    startActivity(intent);
                }
            }
        });
    }
}