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

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

import com.google.android.gms.samples.vision.ocrreader.ui.camera.GraphicOverlay;
import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Graphic instance for rendering TextBlock position, size, and ID within an associated graphic
 * overlay view.
 */
public class OcrGraphic extends GraphicOverlay.Graphic {
    private static String bigText = "AUD AUD 1.0 AUD BRL 2.81 AUD CAD 0.91 AUD CHF 0.68 AUD CNY 4.87 AUD EUR 0.62 AUD GBP 0.55 AUD HKD 5.38 AUD INR 48.84 AUD IDR 9601.51 AUD JPY 74.31 AUD KRW 810.77 AUD MYR 2.86 AUD MXN 13.34 AUD NZD 1.08 AUD RUB 44.25 AUD SAR 2.58 AUD SGD 0.94 AUD SEK 6.61 AUD TRY 3.91 AUD USD 0.69 AUD ZAR 10.01 BRL AUD 0.36 BRL BRL 1.0 BRL CAD 0.33 BRL CHF 0.24 BRL CNY 1.73 BRL EUR 0.22 BRL GBP 0.2 BRL HKD 1.91 BRL INR 17.39 BRL IDR 3418.58 BRL JPY 26.46 BRL KRW 288.67 BRL MYR 1.02 BRL MXN 4.75 BRL NZD 0.38 BRL RUB 15.75 BRL SAR 0.92 BRL SGD 0.34 BRL SEK 2.35 BRL TRY 1.39 BRL USD 0.24 BRL ZAR 3.56 CAD AUD 1.1 CAD BRL 3.08 CAD CAD 1.0 CAD CHF 0.75 CAD CNY 5.33 CAD EUR 0.68 CAD GBP 0.6 CAD HKD 5.89 CAD INR 53.49 CAD IDR 10514.57 CAD JPY 81.38 CAD KRW 887.87 CAD MYR 3.14 CAD MXN 14.61 CAD NZD 1.18 CAD RUB 48.45 CAD SAR 2.82 CAD SGD 1.03 CAD SEK 7.23 CAD TRY 4.28 CAD USD 0.75 CAD ZAR 10.96 CHF AUD 1.47 CHF BRL 4.13 CHF CAD 1.34 CHF CHF 1.0 CHF CNY 7.15 CHF EUR 0.91 CHF GBP 0.81 CHF HKD 7.9 CHF INR 71.75 CHF IDR 14103.47 CHF JPY 109.16 CHF KRW 1190.92 CHF MYR 4.21 CHF MXN 19.6 CHF NZD 1.58 CHF RUB 64.99 CHF SAR 3.79 CHF SGD 1.39 CHF SEK 9.7 CHF TRY 5.74 CHF USD 1.01 CHF ZAR 14.71 CNY AUD 0.21 CNY BRL 0.58 CNY CAD 0.19 CNY CHF 0.14 CNY CNY 1.0 CNY EUR 0.13 CNY GBP 0.11 CNY HKD 1.1 CNY INR 10.04 CNY IDR 1972.95 CNY JPY 15.27 CNY KRW 166.6 CNY MYR 0.59 CNY MXN 2.74 CNY NZD 0.22 CNY RUB 9.09 CNY SAR 0.53 CNY SGD 0.19 CNY SEK 1.36 CNY TRY 0.8 CNY USD 0.14 CNY ZAR 2.06 EUR AUD 1.62 EUR BRL 4.54 EUR CAD 1.48 EUR CHF 1.1 EUR CNY 7.86 EUR EUR 1.0 EUR GBP 0.89 EUR HKD 8.69 EUR INR 78.91 EUR IDR 15511.97 EUR JPY 120.06 EUR KRW 1309.86 EUR MYR 4.63 EUR MXN 21.55 EUR NZD 1.74 EUR RUB 71.48 EUR SAR 4.17 EUR SGD 1.53 EUR SEK 10.67 EUR TRY 6.32 EUR USD 1.11 EUR ZAR 16.17 GBP AUD 1.82 GBP BRL 5.1 GBP CAD 1.66 GBP CHF 1.24 GBP CNY 8.84 GBP EUR 1.12 GBP GBP 1.0 GBP HKD 9.77 GBP INR 88.76 GBP IDR 17448.54 GBP JPY 135.05 GBP KRW 1473.38 GBP MYR 5.21 GBP MXN 24.24 GBP NZD 1.96 GBP RUB 80.41 GBP SAR 4.69 GBP SGD 1.72 GBP SEK 12.01 GBP TRY 7.1 GBP USD 1.25 GBP ZAR 18.19 HKD AUD 0.19 HKD BRL 0.52 HKD CAD 0.17 HKD CHF 0.13 HKD CNY 0.91 HKD EUR 0.12 HKD GBP 0.1 HKD HKD 1.0 HKD INR 9.08 HKD IDR 1785.57 HKD JPY 13.82 HKD KRW 150.78 HKD MYR 0.53 HKD MXN 2.48 HKD NZD 0.2 HKD RUB 8.23 HKD SAR 0.48 HKD SGD 0.18 HKD SEK 1.23 HKD TRY 0.73 HKD USD 0.13 HKD ZAR 1.86 INR AUD 0.02 INR BRL 0.06 INR CAD 0.02 INR CHF 0.01 INR CNY 0.1 INR EUR 0.01 INR GBP 0.01 INR HKD 0.11 INR INR 1.0 INR IDR 196.57 INR JPY 1.52 INR KRW 16.6 INR MYR 0.06 INR MXN 0.27 INR NZD 0.02 INR RUB 0.91 INR SAR 0.05 INR SGD 0.02 INR SEK 0.14 INR TRY 0.08 INR USD 0.01 INR ZAR 0.2 IDR AUD 0.0 IDR BRL 0.0 IDR CAD 0.0 IDR CHF 0.0 IDR CNY 0.0 IDR EUR 0.0 IDR GBP 0.0 IDR HKD 0.0 IDR INR 0.01 IDR IDR 1.0 IDR JPY 0.01 IDR KRW 0.08 IDR MYR 0.0 IDR MXN 0.0 IDR NZD 0.0 IDR RUB 0.0 IDR SAR 0.0 IDR SGD 0.0 IDR SEK 0.0 IDR TRY 0.0 IDR USD 0.0 IDR ZAR 0.0 JPY AUD 0.01 JPY BRL 0.04 JPY CAD 0.01 JPY CHF 0.01 JPY CNY 0.07 JPY EUR 0.01 JPY GBP 0.01 JPY HKD 0.07 JPY INR 0.66 JPY IDR 129.2 JPY JPY 1.0 JPY KRW 10.91 JPY MYR 0.04 JPY MXN 0.18 JPY NZD 0.01 JPY RUB 0.6 JPY SAR 0.03 JPY SGD 0.01 JPY SEK 0.09 JPY TRY 0.05 JPY USD 0.01 JPY ZAR 0.13 KRW AUD 0.0 KRW BRL 0.0 KRW CAD 0.0 KRW CHF 0.0 KRW CNY 0.01 KRW EUR 0.0 KRW GBP 0.0 KRW HKD 0.01 KRW INR 0.06 KRW IDR 11.84 KRW JPY 0.09 KRW KRW 1.0 KRW MYR 0.0 KRW MXN 0.02 KRW NZD 0.0 KRW RUB 0.05 KRW SAR 0.0 KRW SGD 0.0 KRW SEK 0.01 KRW TRY 0.0 KRW USD 0.0 KRW ZAR 0.01 MYR AUD 0.35 MYR BRL 0.98 MYR CAD 0.32 MYR CHF 0.24 MYR CNY 1.7 MYR EUR 0.22 MYR GBP 0.19 MYR HKD 1.88 MYR INR 17.05 MYR IDR 3352.24 MYR JPY 25.95 MYR KRW 283.07 MYR MYR 1.0 MYR MXN 4.66 MYR NZD 0.38 MYR RUB 15.45 MYR SAR 0.9 MYR SGD 0.33 MYR SEK 2.31 MYR TRY 1.36 MYR USD 0.24 MYR ZAR 3.5 MXN AUD 0.07 MXN BRL 0.21 MXN CAD 0.07 MXN CHF 0.05 MXN CNY 0.36 MXN EUR 0.05 MXN GBP 0.04 MXN HKD 0.4 MXN INR 3.66 MXN IDR 719.69 MXN JPY 5.57 MXN KRW 60.77 MXN MYR 0.21 MXN MXN 1.0 MXN NZD 0.08 MXN RUB 3.32 MXN SAR 0.19 MXN SGD 0.07 MXN SEK 0.5 MXN TRY 0.29 MXN USD 0.05 MXN ZAR 0.75 NZD AUD 0.93 NZD BRL 2.61 NZD CAD 0.85 NZD CHF 0.63 NZD CNY 4.52 NZD EUR 0.57 NZD GBP 0.51 NZD HKD 4.99 NZD INR 45.32 NZD IDR 8908.76 NZD JPY 68.95 NZD KRW 752.27 NZD MYR 2.66 NZD MXN 12.38 NZD NZD 1.0 NZD RUB 41.05 NZD SAR 2.39 NZD SGD 0.88 NZD SEK 6.13 NZD TRY 3.63 NZD USD 0.64 NZD ZAR 9.29 RUB AUD 0.02 RUB BRL 0.06 RUB CAD 0.02 RUB CHF 0.02 RUB CNY 0.11 RUB EUR 0.01 RUB GBP 0.01 RUB HKD 0.12 RUB INR 1.1 RUB IDR 217.0 RUB JPY 1.68 RUB KRW 18.32 RUB MYR 0.06 RUB MXN 0.3 RUB NZD 0.02 RUB RUB 1.0 RUB SAR 0.06 RUB SGD 0.02 RUB SEK 0.15 RUB TRY 0.09 RUB USD 0.02 RUB ZAR 0.23 SAR AUD 0.39 SAR BRL 1.09 SAR CAD 0.35 SAR CHF 0.26 SAR CNY 1.89 SAR EUR 0.24 SAR GBP 0.21 SAR HKD 2.09 SAR INR 18.94 SAR IDR 3723.93 SAR JPY 28.82 SAR KRW 314.45 SAR MYR 1.11 SAR MXN 5.17 SAR NZD 0.42 SAR RUB 17.16 SAR SAR 1.0 SAR SGD 0.37 SAR SEK 2.56 SAR TRY 1.52 SAR USD 0.27 SAR ZAR 3.88 SGD AUD 1.06 SGD BRL 2.97 SGD CAD 0.97 SGD CHF 0.72 SGD CNY 5.15 SGD EUR 0.66 SGD GBP 0.58 SGD HKD 5.69 SGD INR 51.73 SGD IDR 10167.9 SGD JPY 78.7 SGD KRW 858.59 SGD MYR 3.03 SGD MXN 14.13 SGD NZD 1.14 SGD RUB 46.86 SGD SAR 2.73 SGD SGD 1.0 SGD SEK 7.0 SGD TRY 4.14 SGD USD 0.73 SGD ZAR 10.6 SEK AUD 0.15 SEK BRL 0.43 SEK CAD 0.14 SEK CHF 0.1 SEK CNY 0.74 SEK EUR 0.09 SEK GBP 0.08 SEK HKD 0.81 SEK INR 7.39 SEK IDR 1453.34 SEK JPY 11.25 SEK KRW 122.72 SEK MYR 0.43 SEK MXN 2.02 SEK NZD 0.16 SEK RUB 6.7 SEK SAR 0.39 SEK SGD 0.14 SEK SEK 1.0 SEK TRY 0.59 SEK USD 0.1 SEK ZAR 1.52 TRY AUD 0.26 TRY BRL 0.72 TRY CAD 0.23 TRY CHF 0.17 TRY CNY 1.24 TRY EUR 0.16 TRY GBP 0.14 TRY HKD 1.38 TRY INR 12.49 TRY IDR 2455.92 TRY JPY 19.01 TRY KRW 207.38 TRY MYR 0.73 TRY MXN 3.41 TRY NZD 0.28 TRY RUB 11.32 TRY SAR 0.66 TRY SGD 0.24 TRY SEK 1.69 TRY TRY 1.0 TRY USD 0.18 TRY ZAR 2.56 USD AUD 1.45 USD BRL 4.08 USD CAD 1.33 USD CHF 0.99 USD CNY 7.08 USD EUR 0.9 USD GBP 0.8 USD HKD 7.82 USD INR 71.04 USD IDR 13964.74 USD JPY 108.08 USD KRW 1179.21 USD MYR 4.17 USD MXN 19.4 USD NZD 1.57 USD RUB 64.35 USD SAR 3.75 USD SGD 1.37 USD SEK 9.61 USD TRY 5.69 USD USD 1.0 USD ZAR 14.56 ZAR AUD 0.1 ZAR BRL 0.28 ZAR CAD 0.09 ZAR CHF 0.07 ZAR CNY 0.49 ZAR EUR 0.06 ZAR GBP 0.05 ZAR HKD 0.54 ZAR INR 4.88 ZAR IDR 959.03 ZAR JPY 7.42 ZAR KRW 80.98 ZAR MYR 0.29 ZAR MXN 1.33 ZAR NZD 0.11 ZAR RUB 4.42 ZAR SAR 0.26 ZAR SGD 0.09 ZAR SEK 0.66 ZAR TRY 0.39 ZAR USD 0.07 ZAR ZAR 1.0 ";
    private static Map<String, Map<String, Double>> exchange = new HashMap<>();
    private static boolean initialised = false;

    private static void init()
    {
        if (initialised) return;
        initialised = true;

        Map<String, Double> curMap = new HashMap<>();

        String first = "";
        String second = "";

        int wordCount = 1;
        int i = 0;
        String curWord = "";
        while (i < bigText.length()) {
            if (bigText.charAt(i) == ' ') {
                if (wordCount == 1) {
                    if (first.compareTo(curWord) != 0) {
                        Log.i(TAG, "AOAOAOAO: " + first + "    " + curWord);
                        if (curMap.size() > 0) {
                            exchange.put(first, curMap);

                            Log.i(TAG, "IIMMIMIM: " + exchange.get(first).containsKey("IDR"));
                        }
                        curMap = new HashMap<>();
                        first = curWord;
                    }

                    curWord = "";
                } else if (wordCount == 2) {
                    second = curWord;
                    curWord = "";
                } else {
                    curMap.put(second, Double.parseDouble(curWord));
                    Log.i(TAG, "first: " + first + " second: " + second + " money: " + curWord);
                    curWord = "";
                }

                wordCount += 1;
                if (wordCount == 4) wordCount = 1;
            } else curWord += bigText.charAt(i);

            i++;
        }



        if (curMap.size() > 0) exchange.put(first, curMap);
    }

    private int id;

    private static final int TEXT_COLOR = Color.WHITE;

    private static Paint rectPaint;
    private static Paint textPaint;
    private final TextBlock textBlock;
    private static final String TAG = "MyActivity";
    public static final String FirstCurrency = "FirstCurrency";
    public static final String SecondCurrency = "SecondCurrency";

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

        init();

        // Draws the bounding box around the TextBlock.
//        RectF rect = new RectF(textBlock.getBoundingBox());
//        rect = translateRect(rect);
//        canvas.drawRect(rect, rectPaint);

        // Break the text into multiple lines and draw each one according to its own bounding box.
        List<? extends Text> textComponents = textBlock.getComponents();
        for(Text currentText : textComponents) {
            float left = translateX(currentText.getBoundingBox().left);
            float bottom = translateY(currentText.getBoundingBox().bottom);

            String firstCurrency = MainActivity.getFirstCurrency();
            String secondCurrency = MainActivity.getSecondCurrency();

            Log.i(TAG, "draw: " + firstCurrency);
            Log.i(TAG, "draw: Oneifniew" + exchange.containsKey(firstCurrency));
            Log.i(TAG, "draw: " + secondCurrency);
            Log.i(TAG, "draw: XXXXXXXXXXX  " + exchange.get(firstCurrency).containsKey(secondCurrency));

            Double rate = new Double(1);
            if (exchange.containsKey(firstCurrency) && exchange.get(firstCurrency).containsKey(secondCurrency))
                rate = exchange.get(firstCurrency).get(secondCurrency);

           // String firstCurrency = ((Activity) this).getIntent();
//            boolean useFlash = getIntent().getBooleanExtra(UseFlash, false);

            Log.i(TAG, "draw: " + isNumeric(currentText.getValue()));
            if (!isNumeric(currentText.getValue()) && isNumeric(keepNumbers(currentText.getValue()))) {
                double original = Double.parseDouble(keepNumbers(currentText.getValue()));
                original *= rate;
                String result = String.format("%.2f", original);

                canvas.drawText(result, left, bottom, textPaint);
            }
            else if (NumberUtils.isCreatable(currentText.getValue())) {
                double original = Double.parseDouble(currentText.getValue());
                original *= rate;
                String result = String.format("%.2f", original);

                canvas.drawText(result, left, bottom, textPaint);
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
