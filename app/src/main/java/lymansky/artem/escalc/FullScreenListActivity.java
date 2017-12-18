package lymansky.artem.escalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;

public class FullScreenListActivity extends AppCompatActivity {

    private static final long ANIMATION_DURATION = 200L;

    private TextView grandTotal;
    private ArrayList<Product> productSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_list);

        productSet = Product.getProductSet();
        grandTotal = findViewById(R.id.grand_total_fullscreen);
        ProductCardAdapter adapter = new ProductCardAdapter(grandTotal);
        RecyclerView rv = findViewById(R.id.recycler_view_fullscreen);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setRemoveDuration(ANIMATION_DURATION);
        itemAnimator.setAddDuration(ANIMATION_DURATION);
        rv.setItemAnimator(itemAnimator);
        rv.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
                setGrandTotal();
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                setGrandTotal();
            }
        });

        setGrandTotal();
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
}
