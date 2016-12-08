package bumblebees.hobee;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import bumblebees.hobee.hobbycategories.HobbiesChoiceActivity;
import bumblebees.hobee.objects.User;
import bumblebees.hobee.utilities.CropSquareTransformation;
import bumblebees.hobee.utilities.Profile;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;


public class UserProfileActivity extends AppCompatActivity {
    private Gson gson = new Gson();
    private ImageView userImage, userGender;
    private TextView userName, userAge, userDateSince, userBiography;
    private User user;
    Toolbar appToolbar;

    boolean hideMenu = false;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(hideMenu){
            menu.findItem(R.id.menuEditHobbies).setVisible(false);
            menu.findItem(R.id.menuEditProfile).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuEditHobbies:
                Intent intent = new Intent(this, HobbiesChoiceActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                this.startActivity(intent);
                return true;
            case R.id.menuEditProfile:
                //TODO: open edit profile activity here
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        String extra = getIntent().getStringExtra("User");

        userName = (TextView) findViewById(R.id.userName);

        userAge = (TextView) findViewById(R.id.userAge);
        userGender = (ImageView) findViewById(R.id.userGender);
        //userDateSince = (TextView) findViewById(R.id.userDateSince);
        userBiography = (TextView) findViewById(R.id.userBiography);
        userImage = (ImageView) findViewById(R.id.userImage);

        appToolbar = (Toolbar) findViewById(R.id.profileToolbar);
        setSupportActionBar(appToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // If user wants to see his own profile

        if (extra==null) {
            try {
                userName.setText(Profile.getInstance().getFirstName() + " " + Profile.getInstance().getLastName());
                userAge.setText("" + Profile.getInstance().getAge());
                if (Profile.getInstance().getGender().equals("male")) {
                    userGender.setImageResource(R.drawable.male);
                } else {
                    userGender.setImageResource(R.drawable.female);
                }
                //userDateSince.setText("Member since " + Profile.getInstance().userSince());
                userBiography.setText(Profile.getInstance().getBio());
                Picasso.with(this).load(Profile.getInstance().getPicUrl()).transform(new CropSquareTransformation()).into(userImage);
            } catch (NullPointerException e) {
                Toast toast = Toast.makeText(getApplicationContext(), "Error seeing profile", Toast.LENGTH_LONG);
                toast.show();
                finish();
            }

        }

        else if(extra.equals("error")){
            Toast toast = Toast.makeText(getApplicationContext(), "Error seeing profile", Toast.LENGTH_LONG);
            toast.show();
            finish();
        }
        else {
            hideMenu = true;
            invalidateOptionsMenu();
            user = gson.fromJson(getIntent().getStringExtra("User"), User.class);
            try {
                userName.setText(user.getFirstName() + " " + user.getLastName());
                userAge.setText("" + user.getAge());
                if (user.getGender().equals("male")) {
                    userGender.setImageResource(R.drawable.male);
                } else {
                    userGender.setImageResource(R.drawable.female);
                }
                //userDateSince.setText("Member since " + Profile.getInstance().userSince());
                userBiography.setText(user.getBio());
                Picasso.with(this).load(user.getPicUrl()).transform(new CropSquareTransformation()).into(userImage);
            } catch (NullPointerException e) {
                Toast toast = Toast.makeText(getApplicationContext(), "Error seeing profile", Toast.LENGTH_LONG);
                toast.show();
                finish();
            }


        }
    }


}



