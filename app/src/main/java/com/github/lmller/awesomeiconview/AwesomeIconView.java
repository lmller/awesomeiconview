/*
 * Copyright (c) 2016 Lovis Möller
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package com.github.lmller.awesomeiconview;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Displays a single FontAwesome unicode icon.
 */
public class AwesomeIconView extends View {

	private static final String FONT_PATH = "FontAwesome.otf";
	private Paint textPaint;
	private Rect textBounds = new Rect();
	private FontAwesomeIcon icon = FontAwesomeIcon.NULL;

	public AwesomeIconView(Context context) {
		this(context, null);
	}

	public AwesomeIconView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public AwesomeIconView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		int iconSize;
		int iconColor;

		TypedArray a = context.getTheme().obtainStyledAttributes(
				attrs,
				R.styleable.AwesomeIconView,
				0, 0);

		try {
			iconSize = a.getDimensionPixelSize(R.styleable.AwesomeIconView_iconSize, 12);
			iconColor = a.getColor(R.styleable.AwesomeIconView_iconColor, Color.WHITE);
			icon = FontAwesomeIcon.valueOf(a.getInt(R.styleable.AwesomeIconView_fontAwesomeIcon, 0));
		} finally {
			a.recycle();
		}

		this.textPaint = new Paint();
		this.textPaint.setTextSize(iconSize);
		this.textPaint.setColor(iconColor);
		this.textPaint.setTypeface(Typeface.createFromAsset(context.getAssets(), FONT_PATH));
		this.textPaint.setAntiAlias(true);
		this.textPaint.setTextAlign(Paint.Align.CENTER);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		float x = getWidth() / 2f;
		float y = getHeight() / 2f + textBounds.height() / 3f;
		canvas.drawText(icon.unicode, x, y, textPaint);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (textBounds.isEmpty()){
			textPaint.getTextBounds(icon.unicode, 0, 1, textBounds);
		}
		int width = MeasureSpec.makeMeasureSpec(
				getPaddingLeft() + getPaddingRight() + textBounds.width(),
				MeasureSpec.EXACTLY
		);

		int height = MeasureSpec.makeMeasureSpec(
				getPaddingTop() + getPaddingBottom() + textBounds.height(),
				MeasureSpec.EXACTLY
		);

		setMeasuredDimension(width, height);
	}

	public void setIcon(@NonNull FontAwesomeIcon icon) {
		this.icon = icon;
		textPaint.getTextBounds(icon.unicode, 0, 1, textBounds);
	}

	public void setIconColor(@ColorInt int color) {
		textPaint.setColor(color);
	}

	public void setIconSize(int dp){
		textPaint.setTextSize(
				TypedValue.applyDimension(
						TypedValue.COMPLEX_UNIT_DIP,
						dp,
						getResources().getDisplayMetrics()
				)
		);
	}

	public enum FontAwesomeIcon {
		NULL(""),
		AMAZON("\uF270"),
		AMBULANCE("\uF0F9"),
		ANDROID("\uF17B"),
		APPLE("\uF179"),
		CHROME("\uF268"),
		CODE("\uF121"),
		FACEBOOK_OFFICIAL("\uF230"),
		FIREFOX("\uF269"),
		GITHUB("\uF09B"),
		GRADUATION("\uF19D"),
		GROUP("\uF0C0"),
		HOME("\uF015"),
		REBEL("\uF1D0"),
		SPACESHUTTLE("\uF197"),
		STEAM("\uF1B6"),
		TWITTER("\uF099"),
		WIKIPEDIA("\uF266"),
		WIFI("\uF1EB"),
		USER("\uF007"),
		XING("\uF168");

		FontAwesomeIcon(String unicode){
			this.unicode = unicode;
		}

		public static FontAwesomeIcon valueOf(int ordinal){
			for (FontAwesomeIcon icon: values()) {
				if(ordinal == icon.ordinal()){
					return icon;
				}
			}

			return NULL;
		}

		public final String unicode;
	}


}
