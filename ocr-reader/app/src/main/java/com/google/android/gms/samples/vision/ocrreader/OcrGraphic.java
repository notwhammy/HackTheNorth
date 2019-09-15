/*
 * Copyright (C) The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.gms.samples.vision.ocrreader;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

import com.google.android.gms.samples.vision.ocrreader.ui.camera.GraphicOverlay;
import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;

/**
 * Graphic instance for rendering TextBlock position, size, and ID within an associated graphic
 * overlay view.
 */
public class OcrGraphic extends GraphicOverlay.Graphic {

    private int id;

    private static final int TEXT_COLOR = Color.WHITE;

    private static Paint rectPaint;
    private static Paint textPaint;
    private final TextBlock textBlock;
    private static final String TAG = "MyActivity";

    OcrGraphic(GraphicOverlay overlay, TextBlock text) {
        super(overlay);

        textBlock = text;

        if (rectPaint == null) {
            rectPaint = new Paint();
            rectPaint.setColor(TEXT_COLOR);
            rectPaint.setStyle(Paint.Style.STROKE);
            rectPaint.setStrokeWidth(4.0f);
        }

        if (textPaint == null) {
            textPaint = new Paint();
            textPaint.setColor(TEXT_COLOR);
            textPaint.setTextSize(54.0f);
        }
        // Redraw the overlay, as this graphic has been added.
        postInvalidate();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TextBlock getTextBlock() {
        return textBlock;
    }

    /**
     * Checks whether a point is within the bounding box of this graphic.
     * The provided point should be relative to this graphic's containing overlay.
     * @param x An x parameter in the relative context of the canvas.
     * @param y A y parameter in the relative context of the canvas.
     * @return True if the provided point is contained within this graphic's bounding box.
     */
    public boolean contains(float x, float y) {
        if (textBlock == null) {
            return false;
        }
        RectF rect = new RectF(textBlock.getBoundingBox());
     //   rect = translateRect(rect);
        return rect.contains(x, y);
    }

    public static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);

        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Draws the text block annotations for position, size, and raw value on the supplied canvas.
     */
    @Override
    public void draw(Canvas canvas) {
        if (textBlock == null) {
            return;
        }

        // Draws the bounding box around the TextBlock.
//        RectF rect = new RectF(textBlock.getBoundingBox());
//        rect = translateRect(rect);
//        canvas.drawRect(rect, rectPaint);

        // Break the text into multiple lines and draw each one according to its own bounding box.
        List<? extends Text> textComponents = textBlock.getComponents();
        for(Text currentText : textComponents) {
            float left = translateX(currentText.getBoundingBox().left);
            float bottom = translateY(currentText.getBoundingBox().bottom);

            Log.i(TAG, "draw: " + isNumeric(currentText.getValue()));
            if (!isNumeric(currentText.getValue()) && isNumeric(keepNumbers(currentText.getValue()))) {
                Log.i(TAG, "Huyu: " + keepNumbers(currentText.getValue().substring(1)));
                canvas.drawText(keepNumbers(currentText.getValue()), left, bottom, textPaint);
            }
            else if (NumberUtils.isCreatable(currentText.getValue())) {
                Log.i(TAG, "Huyu: " + currentText.getValue());
                canvas.drawText(currentText.getValue(), left, bottom, textPaint);
            }
            else {
                Log.i(TAG, "Not Huyu: " + currentText.getValue());
            }

        }
    }

    private String keepNumbers(String x)
    {
        boolean found = false;
        boolean stringFound = false;
        boolean stillOk = true;

        String res = "";
        for (int i = 0; i < x.length(); i++) {
            if (x.charAt(i) >= '0' && x.charAt(i) <= '9' || x.charAt(i) == '.' || x.charAt(i) == ',') {
                res += x.charAt(i);
                found = true;

                if (stringFound) stillOk = false;
            }
            else if (found) {
                stringFound = true;
            }

        }

        if (res.length() == 0 || res.length() > 5 || !stillOk) res = "fail";

        return res;
    }
}
