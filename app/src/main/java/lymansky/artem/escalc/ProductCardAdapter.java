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

    private TextView grandTotal;
    private ArrayList<Product> productSet;

    public ProductCardAdapter(TextView grandTotal) {
        this.grandTotal = grandTotal;
        productSet = Product.getProductSet();
    }


    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card_layout, parent, false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = productSet.get(position);
        holder.name.setText(product.getName());
        holder.total.setText(NumberFormat.getCurrencyInstance().format(product.getTotal()));
        holder.isIncluded.setChecked(product.getIsIncluded());
    }

    @Override
    public int getItemCount() {
        return productSet.size();
    }

    private void setGrandTotal() {
        double value = 0;
        for (int i = 0; i < productSet.size(); i++) {
            if (productSet.get(i).getIsIncluded()) {
                value += productSet.get(i).getTotal();
            }
        }
        grandTotal.setText(NumberFormat.getCurrencyInstance().format(value));
    }

//    INNER CLASS

    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CheckBox isIncluded;
        private TextView name;
        private TextView total;
        private AppCompatImageButton delete;
        private Context context;

        public ProductViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            isIncluded = itemView.findViewById(R.id.card_checkbox);
            isIncluded.setOnClickListener(this);

            name = itemView.findViewById(R.id.card_name);

            total = itemView.findViewById(R.id.card_price);

            delete = itemView.findViewById(R.id.delete_product);
            delete.setOnClickListener(this);

            context = itemView.getContext();
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.card_checkbox:
                    boolean isChecked = productSet.get(getAdapterPosition()).getIsIncluded();
                    productSet.get(getAdapterPosition()).setIsIncluded(!isChecked);
                    setGrandTotal();
                    break;
                case R.id.delete_product:
                    int position = getAdapterPosition();
                    productSet.remove(position);
                    setGrandTotal();
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, productSet.size());
                    break;
                case R.id.card_view:
                    if (context instanceof MainActivity) {
                        Intent intent = new Intent(context, FullScreenListActivity.class);
                        context.startActivity(intent);
                    }
                    break;
            }
        }
    }
}
