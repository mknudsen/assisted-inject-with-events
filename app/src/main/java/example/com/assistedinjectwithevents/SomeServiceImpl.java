package example.com.assistedinjectwithevents;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import roboguice.event.Observes;

public class SomeServiceImpl implements SomeService {

    private String eventName = "";

    @Inject
    public SomeServiceImpl(@Assisted final String someDependency) {
        // someDependency is not really important here
    }

    @Override
    public String computeTheFoo(final String input) {
        return input + eventName;
    }

    public void receiveEvent(@Observes SomeEvent event){
        eventName = event.name;
    }
}
