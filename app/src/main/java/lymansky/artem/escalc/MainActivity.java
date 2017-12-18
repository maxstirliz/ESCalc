package lymansky.artem.escalc;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();

        MainActivityFragment mainViewFragment = new MainActivityFragment();
        NavigationDrawerFragment navigationFragment = new NavigationDrawerFragment();
        fragmentManager.beginTransaction()
                .add(R.id.main_activity_fragment_holder, mainViewFragment)
                .commit();

        fragmentManager.beginTransaction()
                .add(R.id.navigation_drawer_fragment_holder, navigationFragment)
                .commit();
    }
}
