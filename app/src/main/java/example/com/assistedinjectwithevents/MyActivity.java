package example.com.assistedinjectwithevents;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.inject.Inject;

import roboguice.activity.RoboActivity;
import roboguice.event.EventManager;
import roboguice.inject.InjectView;


public class MyActivity extends RoboActivity {

    @InjectView(R.id.send_event)
    Button sendEvent;

    @InjectView(R.id.show_results)
    Button showResult;

    @InjectView(R.id.output)
    TextView output;

    @Inject
    EventManager eventManager;

    @Inject
    SomeServiceFactory someServiceFactory;

    SomeService someService;

    @Inject
    SomeOtherService someOtherService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);


        sendEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                eventManager.fire(new SomeEvent("helloFromEvent "));
            }
        });

        showResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                MyActivity.this.output.setText(
                        String.format(
                                "factory-built dependency returned\n" +
                                        "%s,\n" +
                                        "regular dependency returned\n" +
                                        "%s",
                                someService.computeTheFoo("bingo"),
                                someOtherService.computeTheFoo("bingo")));
            }
        });

        someService = someServiceFactory.createService("factoryDependency");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
