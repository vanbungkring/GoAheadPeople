package com.raafstudio.goahead.people;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.raaf.rActivityLog;
import com.raaf.rDate;
import com.raaf.rUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;

public class rExceptionHandler implements
		java.lang.Thread.UncaughtExceptionHandler {
	private final Activity myContext;
	private final String LINE_SEPARATOR = "\n";
	private final Class<?> myCls;

	public rExceptionHandler(Activity context, Class<?> cls) {
		myContext = context;
		myCls = cls;
	}

	@SuppressWarnings("deprecation")
	public void uncaughtException(Thread thread, Throwable exception) {
		StringWriter stackTrace = new StringWriter();
		exception.printStackTrace(new PrintWriter(stackTrace));
		StringBuilder errorReport = new StringBuilder();
		errorReport.append("************ CAUSE OF ERROR ************\n\n");
		errorReport.append(stackTrace.toString());

		errorReport.append("\n************ DEVICE INFORMATION ***********\n");
		errorReport.append("Brand: ");
		errorReport.append(Build.BRAND);
		errorReport.append(LINE_SEPARATOR);
		errorReport.append("Device: ");
		errorReport.append(Build.DEVICE);
		errorReport.append(LINE_SEPARATOR);
		errorReport.append("Model: ");
		errorReport.append(Build.MODEL);
		errorReport.append(LINE_SEPARATOR);
		errorReport.append("Id: ");
		errorReport.append(Build.ID);
		errorReport.append(LINE_SEPARATOR);
		errorReport.append("Product: ");
		errorReport.append(Build.PRODUCT);
		errorReport.append(LINE_SEPARATOR);
		errorReport.append("\n************ FIRMWARE ************\n");
		errorReport.append("SDK: ");
		errorReport.append(Build.VERSION.SDK);
		errorReport.append(LINE_SEPARATOR);
		errorReport.append("Release: ");
		errorReport.append(Build.VERSION.RELEASE);
		errorReport.append(LINE_SEPARATOR);
		errorReport.append("Incremental: ");
		errorReport.append(Build.VERSION.INCREMENTAL);
		errorReport.append(LINE_SEPARATOR);
		errorReport.append("\n************ APP ************\n");
		errorReport.append("APP Name: ");
		errorReport.append(rUtil.getAppName(myContext));
		errorReport.append(LINE_SEPARATOR);
		errorReport.append("APP Version: ");
		errorReport.append(rUtil.getAppVersion(myContext));
		errorReport.append(LINE_SEPARATOR);

		errorReport.append("\n************ log ************\n");
		for (int i = 0; i < rActivityLog.Size(); i++) {
			errorReport.append((i + 1) + ". ");
			errorReport.append(rActivityLog.getLogs().get(i).getLogTime());
			errorReport.append(" - ");
			errorReport.append(rActivityLog.getLogs().get(i).getLogActivity());
			errorReport.append(LINE_SEPARATOR);
		}
		errorReport.append(LINE_SEPARATOR);
		errorReport.append(rDate.getCurrentDateTime());
		errorReport.append(LINE_SEPARATOR);

		Intent intent = new Intent(myContext, myCls);
		intent.putExtra("error", errorReport.toString());
		myContext.startActivity(intent);

		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(10);
	}

}