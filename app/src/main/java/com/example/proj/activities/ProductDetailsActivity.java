package com.example.proj.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.proj.R;
import com.example.proj.databinding.ActivityProductDetailsBinding;
import com.example.proj.model.Product;
import com.hishd.tinycart.model.Cart;
import com.hishd.tinycart.util.TinyCartHelper;

public class ProductDetailsActivity extends AppCompatActivity {

    TextView productDescription,phoneNumber;

    ActivityProductDetailsBinding binding;
    Product currentProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String name = getIntent().getStringExtra("name");
        String image = getIntent().getStringExtra("image");
        int id = getIntent().getIntExtra("id",0);
        int stock= getIntent().getIntExtra("stock",5);
        double price = getIntent().getDoubleExtra("price",0);
        int phoneNumber = getIntent().getIntExtra("phoneNumber",0);
        double discount = getIntent().getDoubleExtra("discount",0);
        String status = getIntent().getStringExtra("status");
        String category = getIntent().getStringExtra("category");

        getSupportActionBar().setTitle(name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        productDescription = findViewById(R.id.productDescription);


        productDescription.setText(status);

        binding.productPrice.setText("Price: Dt " + price);

        binding.phoneN.setText("Phone Number :" + phoneNumber);

        Glide.with(this).load(image).into(binding.productImage);

        currentProduct = new Product(name,image,status,category,price,phoneNumber,discount,stock,id);


        Cart cart = TinyCartHelper.getCart();


        binding.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart.addItem(currentProduct,1);
                binding.addToCartBtn.setEnabled(false);
                binding.addToCartBtn.setText("Product Saved");
                Toast.makeText(ProductDetailsActivity.this, "Product saved", Toast.LENGTH_SHORT).show();
            }
        });


    }


}