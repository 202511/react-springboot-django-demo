package com.example.demo;

import java.lang.*;
import java.util.Scanner;;
public class WeekA {
	public static void main(String[] args) {
		int year = 0;
		int month = 0;
		int day = 0;
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.print("请输入日期（格式xxxx xx xx）：");
			year = scan.nextInt();
			month = scan.nextInt();
			day = scan.nextInt();
			int weekday = getWeekday(year, month, day);
			System.out.println("输入日期为：星期" + weekday);
		}
	}
	//当返回值小于等于0时则函数执行失败 
	//0代表当year、month、day中有负数 
	//-1代表year大于10000（在此处应为4位数） 
	//-2代表month大于12 
	//-3 代表day超过当月最大天数
	public static int getWeekday(int year, int month, int day) {
		// 验证输入合法性
		System.out.print("s");
		int[] MonthDays = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		System.out.print("a");
		if (((year % 4 == 0) && (year % 100 != 0)) || year % 400 == 0) {
			System.out.print("b");
			MonthDays[1] = 29;
		}


		System.out.print("c");
		if (year < 0 || month < 0 || day < 0) {
			return 0;
		}
		System.out.print("d");
		if (year > 10000) {
			return -1;
		}
		System.out.print("e");
		if (month > 12) {
			return -2;
		}
		System.out.print("f");
		if (day > MonthDays[month - 1]) {
			return -3;
		}


		// 开始计算
		int totalDays = 0;
		for (int i = 1; i < year; i++) {
			System.out.print("g");
			System.out.print("h");
			if (((i % 4 == 0) && (i % 100 != 0)) || i % 400 == 0) {
				System.out.print("i");
				totalDays += 366;
			} else {
				System.out.print("j");
				totalDays += 365;
			}
		}

		for (int i = 1; i < month; i++) {
			System.out.print("l");
			System.out.print("m");
			if (i == 2) {
				System.out.print("n");
				if (((year % 4 == 0) && (year % 100 != 0)) || year % 400 == 0) {
					System.out.print("o");
					MonthDays[1] = 29;
				}
			}
			System.out.print("p");
			totalDays += MonthDays[i - 1];
		}
		totalDays += day;
		int tempDay = totalDays % 7;
		System.out.print("q");
		if (tempDay == 0) {
			System.out.print("r");
			tempDay = 7;
		}
		return tempDay;
	}
}
