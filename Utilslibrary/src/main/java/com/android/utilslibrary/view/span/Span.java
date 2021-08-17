 package com.android.utilslibrary.view.span;
 import android.graphics.Typeface;
 import android.graphics.drawable.Drawable;
 import android.os.Build;
 import android.text.Editable;
 import android.text.InputFilter;
 import android.text.Spannable;
 import android.text.SpannableString;
 import android.text.SpannableStringBuilder;
 import android.text.Spanned;
 import android.text.TextUtils;
 import android.text.style.AbsoluteSizeSpan;
 import android.text.style.BackgroundColorSpan;
 import android.text.style.ImageSpan;
 import android.text.style.ScaleXSpan;
 import android.text.style.StrikethroughSpan;
 import android.text.style.StyleSpan;
 import android.text.style.SubscriptSpan;
 import android.text.style.SuperscriptSpan;
 import android.text.style.TypefaceSpan;

 import androidx.annotation.NonNull;
 import androidx.annotation.RequiresApi;

 /**
 * Created by lizhichao on 8/17/21
 */
 public class Span implements Spannable, Editable,CharSequence{



     @Override
     public Editable replace(int st, int en, CharSequence source, int start, int end) {
         return builder.replace(st,en,source,start,end);
     }

     @Override
     public Editable replace(int st, int en, CharSequence text) {
         return builder.replace(st,en,text);
     }

     @Override
     public Editable insert(int where, CharSequence text, int start, int end) {
         return builder.insert(where,text,start,end);
     }

     @Override
     public Editable insert(int where, CharSequence text) {
         return builder.insert(where,text);
     }

     @Override
     public Editable delete(int st, int en) {
         return builder.delete(st,en);
     }

     @Override
     public Editable append(CharSequence text) {
         return builder.append(text);
     }

     @Override
     public Editable append(CharSequence text, int start, int end) {
         return builder.append(text,start,end);
     }

     @Override
     public Editable append(char text) {
         return builder.append(text);
     }

     @Override
     public void clear() {
           builder.clear();
     }

     @Override
     public void clearSpans() {
          builder.clearSpans();
     }

     @Override
     public void setFilters(InputFilter[] filters) {
          builder.setFilters(filters);
     }

     @Override
     public InputFilter[] getFilters() {
         return builder.getFilters();
     }

     @Override
     public void getChars(int start, int end, char[] dest, int destoff) {
           builder.getChars(start,end,dest,destoff);
     }

     @Override
     public void setSpan(Object what, int start, int end, int flags) {
           builder.setSpan(what,start,end,flags);
     }

     @Override
     public void removeSpan(Object what) {
           builder.removeSpan(what);
     }

     @Override
     public <T> T[] getSpans(int start, int end, Class<T> type) {
         return builder.getSpans(start,end,type);
     }

     @Override
     public String toString() {
         return builder.toString();
     }

     @Override
     public int getSpanStart(Object tag) {
         return builder.getSpanStart(tag);
     }

     @Override
     public int getSpanEnd(Object tag) {
         return builder.getSpanEnd(tag);
     }

     @Override
     public int getSpanFlags(Object tag) {
         return builder.getSpanFlags(tag);
     }

     @Override
     public int nextSpanTransition(int start, int limit, Class type) {
         return builder.nextSpanTransition(start,limit,type);
     }

     @Override
     public int length() {
         return builder.length();
     }

     @Override
     public char charAt(int index) {
         return builder.charAt(index);
     }

     @NonNull
     @Override
     public CharSequence subSequence(int start, int end) {
         return builder.subSequence(start,end);
     }



     SpannableStringBuilder builder;
     public Span(String initString) {
         this.builder = new SpannableStringBuilder(initString);
     }
     public Span() {
         this.builder = new SpannableStringBuilder();
     }


     CharSequence getCharSequence(){
         return builder;
     }


     /**
      * 对当前字符修改
      * */
     public SpanBuilder describeSpan(int start,int count){
         return new SpanBuilder(builder,start,count);
     }

     public SpanBuilder describeSpan(String compareString){
         int start = builder.toString().indexOf(compareString);
         return describeSpan(start,compareString.length());
     }

     public SpanBuilder addSpanString(String addString){
         builder.append(addString);
         return describeSpan(builder.length()-addString.length(),addString.length());
     }

  //块配置
  public static class SpanBuilder {
      int start;
      int count;
      Spannable spannable;
      public SpanBuilder(Spannable builder,int start, int count) {
          this.start = start;
          this.count = count;
          this.spannable = builder;
      }

      private void inflateSpan(Spannable spannableString, int start, int count, Object what){
          spannableString.setSpan(what,start,start+count, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      }

      private void inflateSpan(Spannable spannableString, int start, int count, Object what,int type){
          spannableString.setSpan(what,start,start+count, type);
      }

      private  void inflateSpan(Object span){
          inflateSpan(spannable,start,count,span);
      }

      public SpanBuilder subscript(){
           inflateSpan(new SubscriptSpan());
           return this;
      }

      public SpanBuilder scaleSpanX(float scale){
          inflateSpan(new ScaleXSpan(scale));
          return this;
      }

      public SpanBuilder absoluteSizeSpan(int px){
          inflateSpan(new AbsoluteSizeSpan(px,false));
         return this;
      }

      public SpanBuilder absoluteSizeSpan(int value,boolean isDip){
          inflateSpan(new AbsoluteSizeSpan(value,isDip));
          return this;
      }

      public SpanBuilder backGroundColorSpan(int color){
          inflateSpan(new BackgroundColorSpan(color));
          return this;
      }
      public SpanBuilder deleteLine(){
          inflateSpan(new StrikethroughSpan());
          return this;
      }

      public SpanBuilder typeFaceSpan(String family ){
              inflateSpan(new TypefaceSpan(family));
          return this;
      }
      @RequiresApi(api = Build.VERSION_CODES.P)
      public SpanBuilder typeFaceSpan(Typeface typeface){
              inflateSpan(new TypefaceSpan(typeface));
              return this;
      }
      public SpanBuilder imageSpan(Drawable drawable){
             inflateSpan(new ImageSpan(drawable));
             return this;
      }

      public SpanBuilder styleSpan(int style){
          inflateSpan(new StyleSpan(style));
          return this;
      }
      public SpanBuilder superScriptSpan(){
          inflateSpan(new SuperscriptSpan());
          return this;
      }
      

  }


}