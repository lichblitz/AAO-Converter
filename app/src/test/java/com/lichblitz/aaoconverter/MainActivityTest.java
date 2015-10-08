package com.lichblitz.aaoconverter;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.mikephil.charting.charts.BarChart;
import com.lichblitz.aaoconverter.app.AppConverter;
import com.lichblitz.aaoconverter.domain.ResultCurrency;
import com.lichblitz.aaoconverter.io.CurrencyResponse;
import com.lichblitz.aaoconverter.io.FixerApiAdapter;
import com.lichblitz.aaoconverter.io.FixerApiService;
import com.lichblitz.aaoconverter.io.JsonKeys;
import com.lichblitz.aaoconverter.ui.adapter.ResultAdapter;
import com.lichblitz.aaoconverter.ui.fragment.MainFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;
import org.robolectric.util.ActivityController;

/**
 * Created by lichblitz on 8/10/15.
 */

import java.util.ArrayList;
import java.util.HashMap;

import retrofit.Callback;

import static org.junit.Assert.*;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,manifest = "src/main/AndroidManifest.xml", sdk = 21)
public class MainActivityTest {


    private MainFragment mFragment;

    @Mock
    private FixerApiService apiService;

    @Captor
    private ArgumentCaptor<Callback<CurrencyResponse>> captor;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        FixerApiAdapter.setApiService(apiService);
        mFragment = new MainFragment();
        SupportFragmentTestUtil.startFragment(mFragment);

    }

    @Test
    public void testNotBeNull() {
        assertNotNull(mFragment);

        Button button = (Button) mFragment.getView().findViewById(R.id.button_search);
        assertNotNull(button);

        EditText input = (EditText)  mFragment.getView().findViewById(R.id.input);
        assertNotNull(input);

        BarChart chart = (BarChart)  mFragment.getView().findViewById(R.id.chart);
        assertNotNull(chart);
    }


    @Test
    public void testApiCallSuccess(){
        EditText input = (EditText)  mFragment.getView().findViewById(R.id.input);
        input.setText("1000000");

        BarChart chart = (BarChart)  mFragment.getView().findViewById(R.id.chart);

        Button button = (Button)  mFragment.getView().findViewById(R.id.button_search);
        button.performClick();

        Mockito.verify(apiService).getBaseCurrency(Mockito.anyString(), captor.capture());

        //data obtained from the fixer.io api.
        HashMap<String, String> dataResponse = new HashMap<>();
        dataResponse.put("BRL", "3.8379");  //ok
        dataResponse.put("JPY", "120.1");   //ok
        dataResponse.put("EUR", "0.88763"); //ok
        dataResponse.put("GBP", "0.65312"); //ok
        dataResponse.put("EEE", "0.244");
        dataResponse.put("AAA", "0.33");

        CurrencyResponse currencyResponse = new CurrencyResponse();
        currencyResponse.setCurrencyResult(dataResponse);

        captor.getValue().success(currencyResponse, null);

        ResultAdapter adapter = mFragment.getmResultAdapter();
        ArrayList<ResultCurrency> currencyList = adapter.getCurrencyList();

        assertEquals(3837900,currencyList.get(0).getValue()*1000000,0);
        //test if adapter have only the 4 necessary currencies
        assertEquals(4, adapter.getItemCount());
        //test if the chart is loaded with data
        assertEquals(View.VISIBLE, chart.getVisibility());
        assertNotNull(chart.getBarData());


    }

    @Test
    public void testMessageNoUsdInput(){
        EditText input = (EditText)  mFragment.getView().findViewById(R.id.input);

        Button button = (Button)  mFragment.getView().findViewById(R.id.button_search);
        button.performClick();

        assertEquals("",input.getText().toString());
        assertEquals(AppConverter.getStringFromId(R.string.no_text_message),ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void testApiCallFailure(){
        EditText input = (EditText)  mFragment.getView().findViewById(R.id.input);
        input.setText("1000000");

        BarChart chart = (BarChart)  mFragment.getView().findViewById(R.id.chart);

        Button button = (Button)  mFragment.getView().findViewById(R.id.button_search);
        button.performClick();

        Mockito.verify(apiService).getBaseCurrency(Mockito.anyString(), captor.capture());

        captor.getValue().failure(null);
        ResultAdapter adapter = mFragment.getmResultAdapter();

        assertEquals(AppConverter.getStringFromId(R.string.api_error_message), ShadowToast.getTextOfLatestToast());
        //test if adapter is empty
        assertEquals(0, adapter.getItemCount());
        //test if the chart is GONE
        assertEquals(View.GONE, chart.getVisibility());
        assertNull(chart.getBarData());


    }



}
