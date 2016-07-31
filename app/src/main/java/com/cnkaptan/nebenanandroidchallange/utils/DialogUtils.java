package com.cnkaptan.nebenanandroidchallange.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.cnkaptan.nebenanandroidchallange.service.CustomErrorModel;

/**
 * Created by cnkaptan on 30/07/16.
 */
public class DialogUtils {

    public static void showGeneralErrorDialog(Context context, CustomErrorModel customErrorModel){
        Dialog dialog = new AlertDialog.Builder(context)
                .setTitle(customErrorModel.getErrorType())
                .setMessage(customErrorModel.getErrorDesc())
                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }
}