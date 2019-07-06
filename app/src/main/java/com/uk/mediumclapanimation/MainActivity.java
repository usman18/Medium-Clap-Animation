package com.uk.mediumclapanimation;

import android.animation.Animator;
import android.os.Handler;
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
	private boolean riseAnimationRunning = false;                 // Indicates whether the first animation of the two animations is currently running or not
	private boolean shrinkAnimationRunning = false;
	
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
				
				final String clapCountText = "+" + String.valueOf(++count);
				tvClapCount.setText(clapCountText);
				
				clapCountLayout
					.animate()
					.translationYBy(clapCountVisible ? 0 : -130)
					.scaleX(clapCountVisible ? 1.4f : 1.2f)
					.scaleY(clapCountVisible ? 1.4f : 1.2f)
					.setDuration(clapCountVisible ? 125 : 225)
					.alpha(1)
					.setListener(new Animator.AnimatorListener() {
						@Override
						public void onAnimationStart(Animator animation) {
							riseAnimationRunning = true;
							clapCountVisible = true;
						}
						
						@Override
						public void onAnimationEnd(Animator animation) {
							riseAnimationRunning = false;
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
							
							//Shrink the clap count layout back to normal
							clapCountLayout
								.animate()
								.setDuration(60)
								.scaleX(1f)
								.scaleY(1f)
								.alpha(1)
								.setListener(new Animator.AnimatorListener() {
									@Override
									public void onAnimationStart(Animator animation) {
										shrinkAnimationRunning = true;
									}
									
									@Override
									public void onAnimationEnd(Animator animation) {
										shrinkAnimationRunning = false;
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
										
										if (!riseAnimationRunning && !shrinkAnimationRunning) {
											
											new Handler().postDelayed(new Runnable() {
												@Override
												public void run() {
													
													if (!riseAnimationRunning && !shrinkAnimationRunning) {
														
														//Fade Animation
														clapCountLayout
															.animate()
															.alpha(0f)
															.translationYBy(-70)
															.setDuration(150)
															.withEndAction(new Runnable() {
																@Override
																public void run() {
																	
																	
																	clapCountLayout
																		.animate()
																		.translationYBy(70)
																		.setDuration(10)
																		.alpha(0f)
																		.start();
																	
																}
															})
															.start();
														
													}
													
												}
											}, 2200);
											
										}
										
										
										
									}
								})
								.start();
							
						}
					})
					.start();
					
				
				
				
				
			}
		});
		
		
		
		
		
		
		
		
		
		
		
	}
}
