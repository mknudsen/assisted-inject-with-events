package example.com.assistedinjectwithevents;

import roboguice.event.Observes;

public class SomeOtherServiceImpl implements SomeOtherService {

    private String eventName = "";

    @Override
    public String computeTheFoo(final String input) {
        return input + eventName;
    }

    public void receiveEvent(@Observes SomeEvent event){
        eventName = event.name;
    }
}
