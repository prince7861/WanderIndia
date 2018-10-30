package apps.prince.wanderindia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.LocationManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.tomtom.online.sdk.search.SearchApi;

import java.util.Timer;
import java.util.TimerTask;


public class HomeActivity extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mtoggle;
    private static int indexCity;
    private static  int indexState;
    public static String cityname;
    public static String statename;
    AlertDialog alertDialog=null;
    AppLocationService appLocservice;
    ArrayAdapter<String> arrayAdapterCity;
    ArrayAdapter<String> arrayAdapterState;
    public String cityParsed="Select City";
    public String[] citylist;
    public String[] stateid;
    ImageView listBtlocation;
    Intent locatorService;
    String mPermission="android.permission.ACCESS_FINE_LOCATION";
    public String stateParsed="Select State";
    public String stateParsedID="";
    String [] stateID;
    public String[] statelist;
    RelativeLayout bestplaces;

    private BottomNavigationView mMain_nav;
    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mDrawer=(DrawerLayout) findViewById(R.id.drawer);
        mtoggle=new ActionBarDrawerToggle(this,mDrawer,R.string.open,R.string.close);
        mDrawer.addDrawerListener(mtoggle);
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       Spinner spinner=(Spinner)findViewById(R.id.spnstate);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.india_states,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        Spinner spinner2=(Spinner)findViewById(R.id.spncity);
        ArrayAdapter<CharSequence>adapter2=ArrayAdapter.createFromResource(this,R.array.india_cities,R.layout.support_simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        mMain_nav=(BottomNavigationView)findViewById(R.id.nav_feature);
        mMain_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.nav_home:


                        return true;
                    case R.id.nav_loc:
                        Intent intmap=new Intent(getApplicationContext(),MapActivity.class);
                        startActivity(intmap);

                        return true;
                    case R.id.nav_best:
                        Intent category=new Intent(getApplicationContext(), Categories.class);
                        startActivity(category);

                        return true;
                    default:return false;
                }
            }
        });

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        sliderDotspanel = (LinearLayout) findViewById(R.id.slider);

        ImageAdapter viewPagerAdapter = new ImageAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);

        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for(int i = 0; i < dotscount; i++){

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 10000);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mtoggle.onOptionsItemSelected(item))
        {
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }
    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {

            HomeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if(viewPager.getCurrentItem() == 0){
                        viewPager.setCurrentItem(1);
                    } else if(viewPager.getCurrentItem() == 1){
                        viewPager.setCurrentItem(2);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });

        }

    }
}
