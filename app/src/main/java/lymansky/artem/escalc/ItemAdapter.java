package lymansky.artem.escalc;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by artem on 11/25/2017.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private ArrayList<ListItem> shoppingList;
    private DrawerLayout drawerLayout;

    public ItemAdapter(DrawerLayout navigation) {
        shoppingList = ListItem.getShoppingList();
        drawerLayout = navigation;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        ListItem item = shoppingList.get(position);
        holder.name.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return shoppingList.size();
    }

//  INNER CLASS

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private AppCompatImageButton bought;
        private TextView name;
        private AppCompatImageButton delete;

        public ItemViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            bought = itemView.findViewById(R.id.check_item);

            name = itemView.findViewById(R.id.item_name);

            delete = itemView.findViewById(R.id.delete_item);
            delete.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.delete_item:
                    int position = getAdapterPosition();
                    shoppingList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, shoppingList.size());
                    break;
                case R.id.item_background:
                    if (!shoppingList.get(getAdapterPosition()).getBought()) {
//            TODO: to insert the item's name into EditText of the fragment
                    }
                    break;
            }
        }
    }
}
