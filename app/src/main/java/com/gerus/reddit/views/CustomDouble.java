package com.gerus.reddit.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gerus.reddit.R;

/**
 * Created by gerus-mac on 15/03/17.
 */

public class CustomDouble extends LinearLayout {
    private Context mContext;
    private FloatingActionButton mButton;
    private TextView mText1, mText2;

    public CustomDouble(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public CustomDouble(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        int viColor, viIcon;
        String psTxt1,psTxt2;
        TypedArray voStyle = context.obtainStyledAttributes(attrs, R.styleable.CustomDouble, 0, 0);
        try {
            viColor = voStyle.getColor(R.styleable.CustomDouble_cd_color, ContextCompat.getColor(mContext,R.color.colorAccent));
            psTxt1 = voStyle.getString(R.styleable.CustomDouble_cd_text1);
            psTxt2 = voStyle.getString(R.styleable.CustomDouble_cd_text2);
            viIcon = voStyle.getResourceId(R.styleable.CustomDouble_cd_icon, R.drawable.vc_account);
        } finally {
            voStyle.recycle();
        }
        init();
        prcInit(viColor, psTxt1, psTxt2, viIcon);
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View voView = inflater.inflate(R.layout.item_detail, this, true);
        mButton = (FloatingActionButton) voView.findViewById(R.id.floating);
        mText1 = (TextView) voView.findViewById(R.id.text1);
        mText2 = (TextView) voView.findViewById(R.id.text2);
    }

    private void prcInit(int mColor, String psTxt1, String psTxt2, int piDrawable) {
        mButton.setBackgroundTintList(ColorStateList.valueOf(mColor));
        mButton.setImageResource(piDrawable);
        mText1.setText(psTxt1);
        mText2.setText(psTxt2);
    }
}
