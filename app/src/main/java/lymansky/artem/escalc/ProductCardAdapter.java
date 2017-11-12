package lymansky.artem.escalc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by artem on 11/8/2017.
 */

public class ProductCardAdapter extends RecyclerView.Adapter<ProductCardAdapter.ProductViewHolder> {
  
  private TextView mGrandTotal;
  
  public ProductCardAdapter(TextView grandTotal) {
    mGrandTotal = grandTotal;
  }
  
  
  @Override
  public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card_layout, parent, false);
    return new ProductViewHolder(v);
  }
  
  @Override
  public void onBindViewHolder(ProductViewHolder holder, int position) {
    Product product = Product.getProductSet().get(position);
    holder.mIncluded.setChecked(product.getIsIncluded());
    holder.mCardName.setText(product.getName());
    holder.mCardTotal.setText(NumberFormat.getCurrencyInstance().format(product.getTotal()));
  }
  
  @Override
  public int getItemCount() {
    return Product.getProductSet().size();
  }


//    INNER CLASS
  
  public static class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    
    private CheckBox mIncluded;
    private TextView mCardName;
    private TextView mCardTotal;
    private AppCompatImageButton mCardButton;
    private Context context;
    
    public ProductViewHolder(View itemView) {
      super(itemView);
      mIncluded = itemView.findViewById(R.id.card_checkbox);
      mCardName = itemView.findViewById(R.id.card_name);
      mCardTotal = itemView.findViewById(R.id.card_price);
      mCardButton = itemView.findViewById(R.id.card_image);
      context = itemView.getContext();
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          if (context instanceof MainActivity) {
            Intent intent = new Intent(context, FullScreenListActivity.class);
            context.startActivity(intent);
          }
        }
      });
    }
    
    @Override
    public void onClick(View view) {
      switch (view.getId()) {
        case R.id.card_checkbox:
          Product.getProductSet().get(getLayoutPosition());
          TextView grand = view.findViewById(R.id.grand_total);
          grand.setText(setGrandTotal(Product.getProductSet()));
          break;
        
        case R.id.card_image:
          Product.getProductSet().remove(getLayoutPosition());
          //notifyItemRemoved ???
      }
    }
    
    private String setGrandTotal(ArrayList<Product> set) {
      double value = 0;
      for (int i = 0; i < set.size(); i++) {
        if (set.get(i).getIsIncluded()) {
          value += set.get(i).getTotal();
        }
      }
      return NumberFormat.getCurrencyInstance().format(value);
    }
  }
}
