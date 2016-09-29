package kr.hs.emirim.festivalwhere.models;

import com.google.firebase.database.IgnoreExtraProperties;

// [START blog_user_class]
@IgnoreExtraProperties
public class User {

    public String username;
    public String email;
    public String photo_url;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email, String url) {
        this.username = username;
        this.email = email;
        this.photo_url = url;
    }

}
// [END blog_user_class]
