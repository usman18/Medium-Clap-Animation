package com.uk.mediumclapanimation;

import android.animation.Animator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
	
	private int count = 0;
	
	private ImageView imgClap;
	private FrameLayout clapCountLayout;
	private TextView tvClapCount;
	
	private boolean clapCountVisible = false;                  //Boolean which indicate whether the clap count layout ("clap count layout")
	private boolean firstAnimationRunning = false;                 // Indicates whether the first animation of the two animations is currently running or not
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		imgClap = findViewById(R.id.imgClap);
		clapCountLayout = findViewById(R.id.circleLayout);
		tvClapCount = findViewById(R.id.tvClapCount);
		
		imgClap.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				String clapCountText = "+" + String.valueOf(++count);
				tvClapCount.setText(clapCountText);
				
				clapCountLayout
					.animate()
					.translationYBy(clapCountVisible ? 0 : -120)
					.scaleX(clapCountVisible ? 1.4f : 1.2f)
					.scaleY(clapCountVisible ? 1.4f : 1.2f)
					.setDuration(125)
					.alpha(1)
					.setListener(new Animator.AnimatorListener() {
						@Override
						public void onAnimationStart(Animator animation) {
							firstAnimationRunning = true;
							clapCountVisible = true;
						}
						
						@Override
						public void onAnimationEnd(Animator animation) {
							firstAnimationRunning = false;
						}
						
						@Override
						public void onAnimationCancel(Animator animation) {
						
						}
						
						@Override
						public void onAnimationRepeat(Animator animation) {
						
						}
					})
					.withEndAction(new Runnable() {
						@Override
						public void run() {
							
							
							clapCountLayout
								.animate()
								.setDuration(60)
								.scaleX(1f)
								.scaleY(1f)
								.alpha(1)
								.start();
							
						}
					})
					.start();
					
				
				
				
				
			}
		});
		
		
		
		
		
		
		
		
		
		
		
	}
}
