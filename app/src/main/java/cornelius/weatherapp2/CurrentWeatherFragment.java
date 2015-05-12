package cornelius.weatherapp2;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.NumberFormat;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CurrentWeatherFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CurrentWeatherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrentWeatherFragment extends android.support.v4.app.Fragment
{
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CurrentWeatherFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CurrentWeatherFragment newInstance()
    {
        CurrentWeatherFragment fragment = new CurrentWeatherFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public CurrentWeatherFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri)
    {
        if (mListener != null)
        {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try
        {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    public void metric()
    {
        final TextView temp = (TextView)getActivity().findViewById(R.id.temperature);
        final TextView dew = (TextView)getActivity().findViewById(R.id.dew);
        final TextView pressure = (TextView)getActivity().findViewById(R.id.pressure);
        final TextView gust = (TextView)getActivity().findViewById(R.id.gusts);
        final TextView wind = (TextView)getActivity().findViewById(R.id.windspeed);
        final TextView visibility = (TextView)getActivity().findViewById(R.id.textView12);
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
//        temp.setText(nf.format((Double.parseDouble(weather.get("temp").toString())-32)*5/9) + "º C");
//        dew.setText(nf.format((Double.parseDouble(weather.get("dew").toString())-32)*5/9) + "º C");
//        pressure.setText(nf.format(Double.parseDouble(weather.get("pressure").toString())*25.4)  + " mmHg");
//        gust.setText(nf.format(Double.parseDouble(weather.get("gust").toString())*1.6093)  + " kph");
//        wind.setText(weather.get("direction").toString() + " @ " + nf.format(Double.parseDouble(weather.get("wind").toString())*1.6093)  + " kph");
//        visibility.setText(nf.format(Double.parseDouble(weather.get("visibility").toString())*1.6093)  + " km");
    }

    public void imperial()
    {
        final TextView temp = (TextView)getActivity().findViewById(R.id.temperature);
        final TextView dew = (TextView)getActivity().findViewById(R.id.dew);
        final TextView pressure = (TextView)getActivity().findViewById(R.id.pressure);
        final TextView gust = (TextView)getActivity().findViewById(R.id.gusts);
        final TextView wind = (TextView)getActivity().findViewById(R.id.windspeed);
        final TextView visibility = (TextView)getActivity().findViewById(R.id.textView12);

        temp.setText(LocationIO.weatherInfo.current.temperature + "º F");
        dew.setText(LocationIO.weatherInfo.current.dewPoint + "º F");
        pressure.setText(LocationIO.weatherInfo.current.pressure + " inHg");
        gust.setText(LocationIO.weatherInfo.current.gusts + " mph");
        wind.setText(LocationIO.weatherInfo.current.windDirectionStr() + " @ " + LocationIO.weatherInfo.current.windSpeed + " mph");
        visibility.setText(LocationIO.weatherInfo.current.visibility + " mi");
    }
}
