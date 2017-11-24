package lymansky.artem.escalc;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
  
  private DrawerLayout mDrawerLayout;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    mDrawerLayout = findViewById(R.id.drawer_layout);
    
    FragmentManager fragmentManager = getFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    
    MainActivityFragment mainViewFragment = new MainActivityFragment();
    fragmentTransaction.add(R.id.main_activity_fragment_holder, mainViewFragment);
    fragmentTransaction.commit();
  }
}
