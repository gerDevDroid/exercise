package com.gerus.reddit.dialogs;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gerus.reddit.R;


/**
 * Created by gerus
 */
public class ProgressDialog {

    private Button mButtonCancel;
    private TextView mTextView;
    private AlertDialog mProgressDialog;
    private Activity mContext;

    public ProgressDialog(Activity poContext) {
        mContext = poContext;
    }

    public void showProgressBar(int piDMsg) {
        showProgressBar(mContext.getString(piDMsg));
    }


    public void showProgressBar(String psMessage) {
        final AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(mContext);
        View mView = mContext.getLayoutInflater().inflate(R.layout.progress, null);

        mTextView = (TextView) mView.findViewById(android.R.id.text1);
        mTextView.setText(psMessage);
        mAlertDialog.setCancelable(false);
        mAlertDialog.setView(mView);
        mProgressDialog = mAlertDialog.show();
    }

    /**
     * Cambia el texto del diálogo
     *
     * @param psTxt
     */
    public void prcChangeText(String psTxt) {
        if (mTextView != null) {
            mTextView.setText(psTxt);
        }
    }

    public void dismissDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
