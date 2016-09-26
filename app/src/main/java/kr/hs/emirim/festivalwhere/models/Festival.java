package kr.hs.emirim.festivalwhere.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

// [START post_class]
@IgnoreExtraProperties
public class Festival {

    public String uid;
    public String name;
    public String content;
    public String address_new;
    public String address_old;
    public String date_end;
    public String date_start;
    public String extra_info;
    public String latitude;
    public String longtitude;
    public String org1;
    public String org2;
    public String org3;
    public String site;
    public String tel;

    public Map<String, Boolean> stars = new HashMap<>();

    public Festival() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }


    public Festival(String uid, String name, String content, String address_new, String address_old, String date_end, String date_start, String extra_info, String latitude, String longtitude, String org1, String org2, String org3, String site, String tel) {
        this.uid = uid;
        this.name = name;
        this.content = content;
        this.address_new = address_new;
        this.address_old = address_old;
        this.date_end = date_end;
        this.date_start = date_start;
        this.extra_info = extra_info;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.org1 = org1;
        this.org2 = org2;
        this.org3 = org3;
        this.site = site;
        this.tel = tel;
    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("name", name);
        result.put("content", content);
        result.put("address_new", address_new);
        result.put("address_old", address_old);
        result.put("date_end", date_end);
        result.put("date_start", date_start);
        result.put("extra_info", extra_info);
        result.put("latitude", latitude);
        result.put("longtitude", longtitude);
        result.put("org1", org1);
        result.put("org2", org2);
        result.put("org3", org3);
        result.put("site", site);
        result.put("tel", tel);

        return result;
    }
    // [END post_to_map]

}
// [END post_class]
