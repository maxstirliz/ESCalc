package lymansky.artem.escalc;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
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
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by artem on 11/2/2017.
 */

public class MainActivityFragment extends Fragment {
  
  private static final long ANIMATION_DURATION = 150L;
  
  private Button addButton;
  private Button clearButton;
  private TextInputEditText nameEdit;
  private TextInputEditText priceEdit;
  private TextInputEditText numberEdit;
  private TextView grandTotal;
  private ProductCardAdapter adapter;
  private RecyclerView rv;
  private ArrayList<Product> products;
  private Toast toastEmptyField;
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_main, container, false);
    
//    Fields initializing
    products = Product.getProductSet();
    addButton = view.findViewById(R.id.add_button);
    grandTotal = view.findViewById(R.id.grand_total);
    adapter = new ProductCardAdapter(grandTotal);
    clearButton = view.findViewById(R.id.clear_button);
    nameEdit = view.findViewById(R.id.input_name);
    priceEdit = view.findViewById(R.id.input_price);
    numberEdit = view.findViewById(R.id.input_number);
    
    rv = view.findViewById(R.id.recycler_view);
    rv.setHasFixedSize(true);
    rv.setLayoutManager(new LinearLayoutManager(getActivity()));
    rv.setAdapter(adapter);
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
    
//    Animation settings
    RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
    itemAnimator.setAddDuration(ANIMATION_DURATION);
    itemAnimator.setRemoveDuration(ANIMATION_DURATION);
    rv.setItemAnimator(itemAnimator);
  
    nameEdit.setSingleLine();
    grandTotal.setSingleLine();
    
    priceEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId == EditorInfo.IME_ACTION_DONE) {
          calculate();
        }
        return false;
      }
    });
    numberEdit.setImeOptions(EditorInfo.IME_ACTION_DONE);
    numberEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId == EditorInfo.IME_ACTION_DONE) {
          calculate();
        }
        
        return false;
      }
    });
  
    addButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick (View v) {
        String name = nameEdit.getText().toString();
        if(name.trim().equals("")) {
          name = "Product";
        }
        try {
          double price = priceEdit.getText().length() == 0 ? 0 :
                  Double.parseDouble(priceEdit.getText().toString());
          double number = numberEdit.getText().length() == 0 ? 0 :
                  Double.parseDouble(numberEdit.getText().toString());
          if(price == 0 || number == 0) {
            if(toastEmptyField != null) {
              toastEmptyField.cancel();
            }
            toastEmptyField = Toast.makeText(getActivity(), "At leas Price and Number fields must be full", Toast.LENGTH_SHORT);
            toastEmptyField.show();
            return;
          }
          Product.getProductSet().add(0, new Product(name, price, number, true));
          clearInput();
          setGrandTotal();
        } catch (NumberFormatException e) {
          e.printStackTrace();
        }
        adapter.notifyItemInserted(0);
        rv.smoothScrollToPosition(0);
        addButton.setText("ADD");
        nameEditFocus();
      }
    });
    
    clearButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view ) {
        clearInput();
        addButton.setText("ADD");
        nameEditFocus();
      }
    });
    
    return view;
  }
  
  private void setGrandTotal() {
    double value = 0;
    for (int i = 0; i < products.size(); i++) {
      if(products.get(i).getIsIncluded()) {
        value += products.get(i).getTotal();
      }
    }
    grandTotal.setText(NumberFormat.getCurrencyInstance().format(value));
  }
  
  private void clearInput() {
    nameEdit.setText("");
    priceEdit.setText("");
    numberEdit.setText("");
  }
  
  private void nameEditFocus() {
    nameEdit.requestFocus();
    InputMethodManager imm = (InputMethodManager) getActivity()
            .getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.showSoftInput(nameEdit, InputMethodManager.SHOW_IMPLICIT);
  }
  
  private void calculate() {
    try {
      double price = priceEdit.getText().length() == 0 ? 0 :
              Double.parseDouble(priceEdit.getText().toString());
      double number = numberEdit.getText().length() == 0 ? 0 :
              Double.parseDouble(numberEdit.getText().toString());
      String result = NumberFormat.getCurrencyInstance().format(price * number);
      String check = NumberFormat.getCurrencyInstance().format(0);
      if (!result.equals(check)) {
        addButton.setText("ADD " + result);
      }
    } catch (NumberFormatException e) {
      Toast.makeText(getActivity(), "Incorrect number format", Toast.LENGTH_SHORT).show();
    }
  }
}
