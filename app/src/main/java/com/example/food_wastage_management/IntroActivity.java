package com.example.food_wastage_management;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private ViewPager2 screenPager;
    private IntroViewPagerAdapter introViewPagerAdapter;
    private Button nextButton;

    private int currentItem = 0; // To keep track of the current item index

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Connect with Purpose", "At SustainABite, we're dedicated to connecting people who have surplus food with those who need it the most. Whether you're a generous donor or someone seeking assistance, our app brings together a community driven by a shared goal – to combat food waste and foster compassion.", R.drawable.img1));
        mList.add(new ScreenItem("Hassle-Free Donations", "With SustainABite, making a difference has never been easier. Donors can effortlessly create donation listings, upload images, and provide details about the available food items. Our intuitive platform ensures that your generosity reaches those who truly benefit, all while reducing food waste.", R.drawable.img2));
        mList.add(new ScreenItem("Swift Support for All", "For individuals and families in need, SustainABite provides a swift and effective way to access nourishing meals. Browse through donations, send requests, and receive food with the click of a button. Our app streamlines the process, making sure that help arrives when it's needed most.", R.drawable.img3));

        // Setup ViewPager2
        screenPager = findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mList);
        screenPager.setAdapter(introViewPagerAdapter);

        // Initialize the next button
        nextButton = findViewById(R.id.nextbutton);
        updateButtonText(); // Update the text content initially

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentItem++;
                if (currentItem < introViewPagerAdapter.getItemCount()) {
                    screenPager.setCurrentItem(currentItem);
                } else {
                    // Handle "Get Started" logic here
                    nextButton.setText("Get Started");
                    // You can perform any action you want for "Get Started"
                    Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                    startActivity(intent);                }
                updateButtonText(); // Update the button text again after click
            }
        });
    }

    // Update the button text based on the current item index
    private void updateButtonText() {
        if (currentItem < introViewPagerAdapter.getItemCount() - 1) {
            nextButton.setText("Next");
        } else {
            nextButton.setText("Get Started");
        }
    }
}
