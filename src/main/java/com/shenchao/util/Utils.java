package com.shenchao.util;

import java.awt.Component;

import javax.swing.JOptionPane;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.shenchao.Config;

/**
 * Created by shenchao on 2017/4/5.
 */
public class Utils {
	private static ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
	public static <T> T getBean(Class<T> clazz){
		return context.getBean(clazz);
	}
	
	public static void showSuccessDialog(Component component){
		JOptionPane.showMessageDialog(component, "操作成功","提示",JOptionPane.INFORMATION_MESSAGE);
	}
	public static void showErrorDialog(Component component){
		JOptionPane.showMessageDialog(component, "操作失败","提示",JOptionPane.ERROR_MESSAGE);
	}
	public static void showSuccessDialog(Component component,String content){
		JOptionPane.showMessageDialog(component, content,"提示",JOptionPane.INFORMATION_MESSAGE);
	}
	public static void showErrorDialog(Component component,String content){
		JOptionPane.showMessageDialog(component, content,"提示",JOptionPane.ERROR_MESSAGE);
	}
	
	
	
}
