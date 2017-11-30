package lymansky.artem.escalc;

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
  
  private ArrayList<ListItem> items;
  
  public ItemAdapter() {
    items = ListItem.getItems();
  }
  
  @Override
  public ItemViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
    return new ItemViewHolder(v);
  }
  
  @Override
  public void onBindViewHolder(ItemViewHolder holder, int position) {
    ListItem item = items.get(position);
    holder.name.setText(item.getName());
  }
  
  @Override
  public int getItemCount() {
    return items.size();
  }
  
//  INNER CLASS
  
  public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private AppCompatImageButton bought;
    private TextView name;
    private AppCompatImageButton delete;
    
    public ItemViewHolder (View itemView) {
      super(itemView);
      itemView.setOnClickListener(this);
      
      bought = itemView.findViewById(R.id.check_item);
      
      name = itemView.findViewById(R.id.item_name);
      
      delete = itemView.findViewById(R.id.delete_item);
      delete.setOnClickListener(this);
    }
    
    @Override
    public void onClick (View view) {
      switch (view.getId()) {
        case R.id.delete_item:
          int position = getAdapterPosition();
          items.remove(position);
          notifyItemRemoved(position);
          notifyItemRangeChanged(position, items.size());
          break;
        case R.id.item_background:
          if (!items.get(getAdapterPosition()).getBought()) {
//            TODO: Add name of the item to name edit text field
          }
          break;
      }
    }
  }
}
