package cornelius.weatherapp2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.NumberFormat;


public class MainActivity extends ActionBarActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        // Get latitude and longitude from zip code
        String zipcode = "60563";
        new LocationIO().getLocation(zipcode);

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


    private void metric()
    {
      /*  final TextView temp = (TextView)findViewById(R.id.temperature);
        final TextView dew = (TextView)findViewById(R.id.dew);

        final TextView pressure = (TextView)findViewById(R.id.pressure);
        final TextView gust = (TextView)findViewById(R.id.gusts);
        final TextView wind = (TextView)findViewById(R.id.windspeed);
        final TextView visibility = (TextView)findViewById(R.id.textView12);

        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        temp.setText(nf.format((Double.parseDouble(weather.get("temp").toString())-32)*5/9) + "º C");
        dew.setText(nf.format((Double.parseDouble(weather.get("dew").toString())-32)*5/9) + "º C");
        pressure.setText(nf.format(Double.parseDouble(weather.get("pressure").toString())*25.4)  + " mmHg");
        gust.setText(nf.format(Double.parseDouble(weather.get("gust").toString())*1.6093)  + " kph");
        wind.setText(weather.get("direction").toString() + " @ " + nf.format(Double.parseDouble(weather.get("wind").toString())*1.6093)  + " kph");
        visibility.setText(nf.format(Double.parseDouble(weather.get("visibility").toString())*1.6093)  + " km");*/
    }

    private void imperial()
    {
        WeatherInfo weather = new WeatherInfo();
        String tempe = weather.location.toString();


        new AlertDialog.Builder(this)
                .setTitle("About")
                .setMessage(tempe)
                .setNeutralButton("Ok", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();


    }
    private void zipcode() {

    }

    private void current_weather() {

    }

    private void forecast_weather() {

    }

    //Allows user to select what units they would like to be displayed
    private void units(){
        new AlertDialog.Builder(this)
                .setTitle("Units")
                .setMessage("Choose what units you would like your weather displayed in:")
                .setPositiveButton("Metric", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which) {
                        //metric();
                    }
                })
                .setNegativeButton("Imperial", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which) {
                        imperial();
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        switch (item.getItemId()) {

            case R.id.zipcode:
                zipcode();
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


}
