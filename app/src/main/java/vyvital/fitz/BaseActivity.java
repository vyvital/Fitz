package vyvital.fitz;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;



import de.hdodenhof.circleimageview.CircleImageView;


public class BaseActivity extends AppCompatActivity implements MenuItem.OnMenuItemClickListener{

    public static final String TAG = "Nope";
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private FrameLayout view_stub; //This is the framelayout to keep your content view
    private NavigationView navigation_view; // The new navigation view from Android Design Library. Can inflate menu resources. Easy
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Menu drawerMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // [END config_signin]
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        view_stub =  findViewById(R.id.view_stub);
        navigation_view =  findViewById(R.id.navigation_view);
        mDrawerLayout =  findViewById(R.id.drawer);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, 0, 0);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerMenu = navigation_view.getMenu();
        for(int i = 0; i < drawerMenu.size(); i++) {
            drawerMenu.getItem(i).setOnMenuItemClickListener(this);
        }
        NavigationView navigationView =  findViewById(R.id.navigation_view);

        View header=navigationView.getHeaderView(0);
/*View view=navigationView.inflateHeaderView(R.layout.nav_header_main);*/
        TextView name =  header.findViewById(R.id.userN);
        TextView email = header.findViewById(R.id.userE);
        CircleImageView img = header.findViewById(R.id.profile_image);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        for (UserInfo userInfo : user.getProviderData()) {
            String providerId = userInfo.getProviderId();
            Log.d(TAG,"providerId = "+userInfo.getProviderId());
            if (providerId.equals("google.com")) {
                email.setText(user.getEmail());
                name.setText(user.getDisplayName());
                String photo = user.getPhotoUrl().toString();
                Glide.with(this).load(photo).into(img);

            } else {
                String shorty = mAuth.getCurrentUser().getEmail();
                String shorty2 = shorty.substring(0, shorty.indexOf("@"));
                name.setText(shorty2);
                email.setText(mAuth.getCurrentUser().getEmail());
                }
            }
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /* Override all setContentView methods to put the content view to the FrameLayout view_stub
     * so that, we can make other activity implementations looks like normal activity subclasses.
     */
    @Override
    public void setContentView(int layoutResID) {
        if (view_stub != null) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            View stubView = inflater.inflate(layoutResID, view_stub, false);
            view_stub.addView(stubView, lp);
        }
    }

    @Override
    public void setContentView(View view) {
        if (view_stub != null) {
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            view_stub.addView(view, lp);
        }
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        if (view_stub != null) {
            view_stub.addView(view, params);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                mDrawerLayout.closeDrawers();
                break;
            case R.id.workout:
                Intent intent2 = new Intent(this,BuilderActivity.class);
                startActivity(intent2);
                mDrawerLayout.closeDrawers();
                // do whatever
                break;
            case R.id.logout:
                signOut();
                finish();
            // and so on...
        }
        return false;
    }
    private void signOut() {
        // Firebase sign out
        mAuth.signOut();
        //  Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(BaseActivity.this,LoginActivity.class);
                        Toast.makeText(BaseActivity.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
                        startActivity(intent);


                    }
                });
    }
}
