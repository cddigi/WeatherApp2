package cornelius.weatherapp2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;


public class MainActivity extends ActionBarActivity
        implements CurrentWeatherFragment.OnFragmentInteractionListener,
        ForecastFragment.OnFragmentInteractionListener
{
    private static final int NUM_PAGES = 7;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    protected GestureDetectorCompat mDetector;
    static String zipcode = null;

    GestureDetector.OnGestureListener glistener = new GestureDetector.SimpleOnGestureListener()
    {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            //swapFragments();
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    };

    protected void swapFragments()
    {
        android.support.v4.app.FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.container, new ForecastFragment());
        trans.addToBackStack(null);
        trans.commit();
    }

    @Override
    public void onBackPressed()
    {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    public void showMap(Uri geoLocation)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter
    {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return ForecastFragment.newInstance("","");
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        //this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        // Get latitude and longitude from zip code
        zipcode = "60563";
        // Sends zip code in order to get latitude and longitude
        LocationIO loc = new LocationIO();
        loc.getLocation(zipcode);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Displays about message
    private void about() {
        new AlertDialog.Builder(this)
                .setTitle("About")
                .setMessage("This is a weather app. Created by Cory and Tessa." +
                        "Data from www.weather.gov")
                .setNeutralButton("Ok", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }





    private void zipcode() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("zipcode")
        .setMessage("Enter Zipcode of Desired Location");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        zipcode = input.getText().toString();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        builder.show();
    }

    private void recent_zipcodes() {

    }

    private void current_weather() {

    }

    private void forecast_weather() {

    }

    //Allows user to select what units they would like to be displayed
    private void units()
    {
        final CurrentWeatherFragment fragment = CurrentWeatherFragment.newInstance();
        new AlertDialog.Builder(this)
                .setTitle("Units")
                .setMessage("Choose what units you would like your weather displayed in:")
                .setPositiveButton("Metric", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which) {
                        fragment.metric();
                    }
                })
                .setNegativeButton("Imperial", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which) {
                        fragment.imperial();
                    }
                })
                .show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()) {

            case R.id.zipcode:
                zipcode();
                return true;

            case R.id.recent_zipcode:
                recent_zipcodes();
                return true;

            case R.id.current_weather:
                current_weather();
                return true;

            case R.id.forecast:
                forecast_weather();
                return true;

            case R.id.units:
                units();
                return true;

            case R.id.about:
                about();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri)
    {

    }
}
