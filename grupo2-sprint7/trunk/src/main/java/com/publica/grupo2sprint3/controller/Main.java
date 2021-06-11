package com.publica.grupo2sprint3.controller;

import java.util.Scanner;

import com.publica.grupo2sprint3.view.LoginView;

public class Main {

	public static final Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		LoginView.getInstance().firstDisplay();
	}
}
 