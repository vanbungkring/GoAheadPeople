package com.raafstudio.goahead.people.helper;

import java.io.File;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;

public class Util {

	public static String getDir() {
		String sdir = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/" + so.getAppName();
		File dir = new File(sdir);
		if (!dir.exists())
			dir.mkdir();
		return sdir;
	}

	public static Boolean CheckFile(String filepath) {
		File file = new File(filepath);
		return file.exists();
	}

	public static Bitmap getBlurBitmap(Bitmap src) {

		final int widthKernal = 5;
		final int heightKernal = 5;

		int w = src.getWidth();
		int h = src.getHeight();

		Bitmap blurBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {

				int r = 0;
				int g = 0;
				int b = 0;
				int a = 0;

				for (int xk = 0; xk < widthKernal; xk++) {
					for (int yk = 0; yk < heightKernal; yk++) {
						int px = x + xk - 2;
						int py = y + yk - 2;

						if (px < 0) {
							px = 0;
						} else if (px >= w) {
							px = w - 1;
						}

						if (py < 0) {
							py = 0;
						} else if (py >= h) {
							py = h - 1;
						}

						int intColor = src.getPixel(px, py);
						r += Color.red(intColor);
						g += Color.green(intColor);
						b += Color.blue(intColor);
						a += Color.alpha(intColor);

					}
				}
				blurBitmap.setPixel(x, y,
						Color.argb(a / 25, r / 25, g / 25, b / 25));

			}
		}

		return blurBitmap;
	}

	public static String getFirstWordUpper(String str) {
		str = str.trim();
		String[] data = str.split(" ");
		str = "";
		for (int i = 0; i < data.length; i++) {
			str += data[i].substring(0, 1).toUpperCase()
					+ data[i].substring(1, data[i].length()).toLowerCase()
					+ " ";
		}
		return str.substring(0, str.length() - 1);
	}

}
