package com.seldon.news.common.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.seldon.news.R;

import java.util.EnumMap;
import java.util.Map;

public final class FontUtils {

	public static final int CASE_NORMAL = 0;
	public static final int CASE_LOWER = 1;
	public static final int CASE_UPPER = 2;
	
	private FontUtils() {}

	public enum FontType {
		/**
		 * roboto
		 */
		ROBOTO_LIGHT("fonts/Roboto-Light.ttf"),
		ROBOTO_REGULAR("fonts/Roboto-Regular.ttf"),
        ROBOTO_MEDIUM("fonts/Roboto-Medium.ttf"),
		ROBOTO_BOLD("fonts/Roboto-Bold.ttf");

		private final String path;

		FontType(String path) {
			this.path = path;
		}

		public String getPath() {
			return path;
		}
	}
	
	private static Map<FontType, Typeface> typefaceCache = new EnumMap<>(FontType.class);

	public static Typeface getTypeface(Context context, FontType fontType) {
		String fontPath = fontType.getPath();
		Typeface typeFace = typefaceCache.get(fontType);
		if (typeFace == null) {
			typeFace = Typeface.createFromAsset(context.getAssets(), fontPath);
			typefaceCache.put(fontType, typeFace);
		}
		return typeFace;
	}

	public static void setTypeface(TextView textView, int n) {
		if (n == 0) {
			textView.setTypeface(FontUtils.getTypeface(textView.getContext(), FontType.ROBOTO_REGULAR));
		} else if (n == 1) {
			textView.setTypeface(FontUtils.getTypeface(textView.getContext(), FontType.ROBOTO_MEDIUM));
		} else if (n == 2) {
			textView.setTypeface(FontUtils.getTypeface(textView.getContext(), FontType.ROBOTO_BOLD));
		} else {
			textView.setTypeface(FontUtils.getTypeface(textView.getContext(), FontType.ROBOTO_LIGHT));
		}
	}

	public static void setTypeface(TextView textView, FontType type) {
		textView.setTypeface(FontUtils.getTypeface(textView.getContext(), type));
	}

	public static void setCaseType(TextView textView, final int type) {
		if (type != CASE_NORMAL) {
			textView.setTransformationMethod(new TransformationMethod() {

				@Override
				public void onFocusChanged(View view, CharSequence sourceText,
										   boolean focused, int direction, Rect previouslyFocusedRect) {

				}

				@Override
				public CharSequence getTransformation(CharSequence source, View view) {
					if (source != null) {
						if (type == CASE_UPPER) {
							return source.toString().toUpperCase();
						} else if (type == CASE_LOWER) {
							return source.toString().toLowerCase();
						} else {
							return source;
						}
					}
					return null;
				}
			});
		}
	}

	public static void applyFontStyleAttrs(TextView view, AttributeSet attrs) {
		TypedArray a = view.getContext().obtainStyledAttributes(attrs, R.styleable.SeldonFont);
		int fontNumber = 0;
		int caseType = CASE_NORMAL;
		try {
			fontNumber = a.getInteger(R.styleable.SeldonFont_font, 0);
			caseType = a.getInteger(R.styleable.SeldonFont_cases, CASE_NORMAL);
		} finally {
			a.recycle();
		}
		setTypeface(view, fontNumber);
		setCaseType(view, caseType);
	}
}
