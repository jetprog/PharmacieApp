package com.applicaion.pharmacie.pharmaapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


// Activity login  user can use username/password

public class LoginActivity extends AppCompatActivity implements Animation.AnimationListener  {


    // UI references.

    private EditText userName;
    private EditText password;
    private TextView mForgotPswTextView;
    private TextView mLangTextView;
    private Button login;
    private Button mNewAccountButton;
    private ImageView ivLogo;
    private ImageView ivStaticLogo;
    private ImageView ivCover;
    private CheckBox mCbShowPwd;
    private Boolean ANIMATION_ENDED = false;
    private Boolean START_ANIMATION = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(savedInstanceState != null) {
            START_ANIMATION = savedInstanceState.getBoolean("START_ANIMATION");
        }

        ivLogo = (ImageView) findViewById(R.id.ivLogo);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)

        userName = (EditText) findViewById(R.id.edtUserName);
        password = (EditText) findViewById(R.id.edtPassword);
        login = (Button) findViewById(R.id.btLogin);
        mForgotPswTextView = (TextView) findViewById(R.id.forgotPswTextView);
        mLangTextView = (TextView) findViewById(R.id.langTextView);
        ivCover = (ImageView) findViewById(R.id.ivCover);
        mNewAccountButton = (Button) findViewById(R.id.newAccountButton);
        ivStaticLogo = (ImageView) findViewById(R.id.ivStaticLogo);
        mCbShowPwd = (CheckBox) findViewById(R.id.cbShowPwd);

        if(START_ANIMATION) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
                ivCover.setVisibility(View.GONE);
                userName.setVisibility(View.GONE);
            password.setVisibility(View.GONE);
            login.setVisibility(View.GONE);
            mForgotPswTextView.setVisibility(View.GONE);
            mLangTextView.setVisibility(View.GONE);
            mNewAccountButton.setVisibility(View.GONE);
            ivStaticLogo.setVisibility(View.GONE);
            mCbShowPwd.setVisibility(View.GONE);

            Animation moveFBLogoAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_logo);
            moveFBLogoAnimation.setFillAfter(true);
            moveFBLogoAnimation.setAnimationListener(this);
            ivLogo.startAnimation(moveFBLogoAnimation);
        }

        else {
            ivLogo.setVisibility(View.GONE);
        }

        final View activityRootView = findViewById(R.id.linearLogin);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(ANIMATION_ENDED || !START_ANIMATION) {
                    int heightDiff = activityRootView.getRootView().getHeight() - activityRootView.getHeight();
                    if (heightDiff > dpToPx(LoginActivity.this, 200)) {
                        //Soft keyboard is shown
                        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
                            ivCover.setVisibility(View.GONE);
                        mLangTextView.setVisibility(View.GONE);
                        mForgotPswTextView.setVisibility(View.GONE);
                    } else {
                        //Soft keyboard is hidden
                        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
                            ivCover.setVisibility(View.VISIBLE);
                        mLangTextView.setVisibility(View.VISIBLE);
                        mForgotPswTextView.setVisibility(View.VISIBLE);
                    }
                }
            }
        });


        userName .addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(userName.getText().toString().length() > 0 &&  password.getText().toString().length() > 0) {
                    login.setTextColor(Color.parseColor("#ffffff"));
                    login.setEnabled(true);
                }
                else {
                    login.setTextColor(Color.parseColor("#aaaaaa"));
                    login.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(userName.getText().toString().length() > 0 && password.getText().toString().length() > 0) {
                    login.setTextColor(Color.parseColor("#ffffff"));
                    login.setEnabled(true);
                }
                else {
                    login.setTextColor(Color.parseColor("#aaaaaa"));
                    login.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        mCbShowPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // checkbox status is changed from uncheck to checked.
                if (!isChecked) {
                    // show password
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });


        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(i);
            }
        });





    }






    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

        ivStaticLogo.setVisibility(View.VISIBLE);
        ivLogo.clearAnimation();
        ivLogo.setVisibility(View.GONE);

        userName.setAlpha(0f);
        userName.setVisibility(View.VISIBLE);
        password.setAlpha(0f);
        password.setVisibility(View.VISIBLE);
        mLangTextView.setAlpha(0f);
        mLangTextView.setVisibility(View.VISIBLE);
        mForgotPswTextView.setAlpha(0f);
        mForgotPswTextView.setVisibility(View.VISIBLE);
        login.setAlpha(0f);
        login.setVisibility(View.VISIBLE);
        mCbShowPwd.setAlpha(0f);
        mCbShowPwd.setVisibility(View.VISIBLE);
        mNewAccountButton.setAlpha(0f);
        mNewAccountButton.setVisibility(View.VISIBLE);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            ivCover.setAlpha(0f);
            ivCover.setVisibility(View.VISIBLE);
        }

        int mediumAnimationTime = getResources().getInteger(android.R.integer.config_mediumAnimTime);

        userName.animate()
                .alpha(1f)
                .setDuration(mediumAnimationTime)
                .setListener(null);

        password.animate()
                .alpha(1f)
                .setDuration(mediumAnimationTime)
                .setListener(null);

        mLangTextView.animate()
                .alpha(1f)
                .setDuration(mediumAnimationTime)
                .setListener(null);

        mForgotPswTextView.animate()
                .alpha(1f)
                .setDuration(mediumAnimationTime)
                .setListener(null);

        login.animate()
                .alpha(1f)
                .setDuration(mediumAnimationTime)
                .setListener(null);

        mNewAccountButton.animate()
                .alpha(1f)
                .setDuration(mediumAnimationTime)
                .setListener(null);

        mCbShowPwd.animate()
                .alpha(1f)
                .setDuration(mediumAnimationTime)
                .setListener(null);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            ivCover.animate()
                    .alpha(1f)
                    .setDuration(mediumAnimationTime)
                    .setListener(null);
        }

        ANIMATION_ENDED = true;
    }



    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public static float dpToPx(Context context, float valueInDp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, metrics);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean("START_ANIMATION", false);
    }
}

