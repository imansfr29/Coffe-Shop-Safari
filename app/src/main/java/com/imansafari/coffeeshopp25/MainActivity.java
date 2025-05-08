package com.imansafari.coffeeshopp25;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.bumptech.glide.Glide;
import com.imansafari.coffeeshopp25.ViewModel.MainViewModel;
import com.imansafari.coffeeshopp25.databinding.ActivityMainBinding;
import com.imansafari.coffeeshopp25.Domain.BannerModel; // Import BannerModel

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Inisialisasi ViewModel menggunakan ViewModelProvider
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        initBanner(); // Panggil initBanner di onCreate
        binding.startBtn.setOnClickListener(new View.OnClickListener() {  // Tambahkan OnClickListener ke startBtn
            @Override
            public void onClick(View v) {
                // Intent untuk memulai Activity1
                Intent intent = new Intent(MainActivity.this, Activity1_main_xml.class); // Pastikan Activity1.class ada
                startActivity(intent);
            }
        });
    }

    private void initBanner() {
        viewModel.loadBanner().observe(this, new Observer<List<BannerModel>>() { // Gunakan BannerModel
            @Override
            public void onChanged(List<BannerModel> banners) {
                if (banners != null && !banners.isEmpty() && banners.get(0) != null) {
                    runOnUiThread(() -> {
                        try {
                            Glide.with(MainActivity.this)
                                    .load(banners.get(0).getUrl())
                                    .into(binding.imageView2);
                            binding.startBtn.setVisibility(View.VISIBLE);
                        } catch (Exception e) {
                            Log.e("MainActivity", "Error loading image: ", e);
                            Toast.makeText(MainActivity.this, "Failed to load banner image.", Toast.LENGTH_SHORT).show();
                            binding.startBtn.setVisibility(View.VISIBLE);
                        }
                    });
                } else {
                    runOnUiThread(() -> {
                        String errorMessage = "Banner data is not available.";
                        Log.w("MainActivity", errorMessage);
                        Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                        binding.startBtn.setVisibility(View.VISIBLE);
                    });
                }
            }
        });
    }
}
