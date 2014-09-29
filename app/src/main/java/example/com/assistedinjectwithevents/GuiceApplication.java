package example.com.assistedinjectwithevents;

import android.app.Application;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import roboguice.RoboGuice;

public class GuiceApplication extends Application {

    public static final Module MODULE = new AbstractModule() {
        @Override
        protected void configure() {

            bind(SomeOtherService.class).to(SomeOtherServiceImpl.class);

            install(new FactoryModuleBuilder()
                    .implement(SomeService.class, SomeServiceImpl.class)
                    .build(SomeServiceFactory.class));
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();

        RoboGuice.setBaseApplicationInjector(this, RoboGuice.DEFAULT_STAGE,
                RoboGuice.newDefaultRoboModule(this), MODULE
        );
    }
}
