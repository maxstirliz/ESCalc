package lymansky.artem.escalc;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.design.widget.TextInputEditText;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
  
  private static final long ITEM_ANIMATION = 200L;
  
  private DrawerLayout mDrawerLayout;
  private Button addButton;
  private TextInputEditText editText;
  private ArrayList<ListItem> items;
  private ItemAdapter adapter;
  private RecyclerView rv;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    items = ListItem.getItems();
    mDrawerLayout = findViewById(R.id.drawer_layout);
    adapter = new ItemAdapter();
    
    RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
    itemAnimator.setAddDuration(ITEM_ANIMATION);
    itemAnimator.setRemoveDuration(ITEM_ANIMATION);
    
    rv = findViewById(R.id.recycler_view_drawer);
    rv.setHasFixedSize(true);
    rv.setLayoutManager(new LinearLayoutManager(this));
    rv.setAdapter(adapter);
    rv.setItemAnimator(itemAnimator);
    
    FragmentManager fragmentManager = getFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    
    MainActivityFragment mainViewFragment = new MainActivityFragment();
    fragmentTransaction.add(R.id.main_activity_fragment_holder, mainViewFragment);
    fragmentTransaction.commit();
    
    editText = findViewById(R.id.drawer_edit_text);
    addButton = findViewById(R.id.drawer_button);
    addButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String itemText = editText.getText().toString();
        items.add(0, new ListItem(itemText));
        adapter.notifyItemInserted(0);
        rv.smoothScrollToPosition(0);
        editText.setText("");
      }
    });
  }
}
