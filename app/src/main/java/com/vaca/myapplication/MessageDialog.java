package com.vaca.myapplication;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;

public class MessageDialog {
	
	/**
	 * alert 消息提示框显示
	 * @param context	上下文
	 * @param title		标题
	 * @param message	消息
	 * @param listener	监听器
	 */
	public static void showAlert(Context context, String title, String message, OnClickListener listener){
		Builder builder = new Builder(context);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton("确定", listener);
		builder.setCancelable(false);
		builder.setIcon(R.mipmap.ic_launcher);
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	/**
	 * confirm 确认提示框显示
	 * @param context			上下文
	 * @param title				标题
	 * @param message			消息
	 * @param agreeListener		确认事件监听器
	 * @param canelListener		取消事件监听器
	 */
    public static void showConfirm(Context context, String title, String message, OnClickListener agreeListener, OnClickListener canelListener){
        Builder builder = new Builder(context);
        builder.setTitle(title);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage(message);
        builder.setPositiveButton("确定",agreeListener);
        builder.setNeutralButton("取消", canelListener);
        builder.setCancelable(false);  
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    
    public static void confirm(Context context, String title, String message, OnClickListener agreeListener, OnClickListener canelListener){
        Builder builder = new Builder(context);
        builder.setTitle(title);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage(message);
        builder.setPositiveButton("同意",agreeListener);
        builder.setNeutralButton("不同意", canelListener);
        builder.setCancelable(false);  
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    
    /**
     * 多选菜单显示
     * @param context	上下文
     * @param title		标题
     * @param items		菜单标题
     * @param listener	菜单事件监听器
     */
    public static void showMenuDialog(Context context, String title, String[] items, OnClickListener listener){
		Builder builder = new Builder(context);
		builder.setTitle(title);
		builder.setIcon(R.mipmap.ic_launcher);
		builder.setItems(items, listener);
		builder.show();
    }
    
}
