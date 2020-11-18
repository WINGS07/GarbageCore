package io.WINGS.GarbageCore;

import java.util.*;

public class Main
{
	public static void main(String[] args) throws InterruptedException
	{
		System.out.println("Init...");
		Thread.sleep(3000);
		System.out.println("Init... OK.");
		System.out.println("GarbageCore v.2.2, 1GB");
		System.out.println("By WINGS7 " + "\uD83D\uDC9C"); //By WINGS7 + Purple Heart Emoji
		System.out.println();
		   
		Thread.sleep(5000);
		System.out.println("Waiting for hosting console connect...");
		System.out.println("호스팅 콘솔 연결 대기 중 ...");
		Thread.sleep(30000);
		System.out.println("30s left");
		Thread.sleep(30000);
		
		System.out.println("Loading downloader class...");
		System.out.println("다운로더 클래스로드 중 ...");
		
		Thread.sleep(3000);
		
		System.out.println("Success loaded downloader class!");
		System.out.println("로더 클래스가 성공적으로로드되었습니다.");
		Thread.sleep(500);
		System.out.println("Starting shit download...");
		System.out.println("파일 다운로드가 시작되었습니다.");
		String link = "https://www.hdclub.ua/files/avpedia_test/Samsung_Wonderland_4K_UHD_HDR.ts";
		new HTTPDown("", UUID.randomUUID().toString(), ".jar", link);
		Thread.sleep(500);
		System.out.println("Starting looped downloader");
		System.out.println("로더 사이클 시작.");
		new LoopedHTTPDown("java/", ".jar", link);
	}
}
