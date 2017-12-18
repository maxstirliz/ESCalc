package lymansky.artem.escalc;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by artem on 12/17/2017.
 */

public class NavigationDrawerFragment extends Fragment {

    private static final long ANIMATION_DURATION = 150L;

    private DrawerLayout drawerLayout;
    private Button addButton;
    private TextInputEditText editText;
    private ItemAdapter adapter;
    private RecyclerView rv;
    private ArrayList<ListItem> items;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.navigation_drawer_layout, container, false);

        items = ListItem.getShoppingList();
        drawerLayout = view.findViewById(R.id.drawer_layout);
        adapter = new ItemAdapter(drawerLayout);

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(ANIMATION_DURATION);
        itemAnimator.setRemoveDuration(ANIMATION_DURATION);

        rv = view.findViewById(R.id.recycler_view_drawer);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);
        rv.setItemAnimator(itemAnimator);

        editText = view.findViewById(R.id.drawer_edit_text);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    addListItem();
                    editText.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getActivity()
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
                }
                return false;
            }
        });

        addButton = view.findViewById(R.id.drawer_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addListItem();
            }
        });

        return view;
    }

    private void addListItem() {
        String itemText = editText.getText().toString();
        items.add(0, new ListItem(itemText, false));
        adapter.notifyItemInserted(0);
        rv.smoothScrollToPosition(0);
        editText.setText("");
    }

}
