package io.WINGS.GarbageCore;

import java.util.UUID;

import io.WINGS.GarbageCore.storage.DownloaderData;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Init...");
		Thread.sleep(3000);
		System.out.println("Init... OK.");
		System.out.println("GarbageCore v.2.6, 1GB");
		System.out.println("By WINGS7 " + "\uD83D\uDC9C"); //By WINGS7 + Purple Heart Emoji
		
		System.out.println();
		System.out.println();
		System.out.println();
		   
		Thread.sleep(5000);
		System.out.println("Waiting for hosting console connect...");
		System.out.println("호스팅 콘솔 연결 대기 중 ...");
		Thread.sleep(30000);
		System.out.println("30s left");
		Thread.sleep(30000);
		
		System.out.println("Loading downloader class...");
		System.out.println("다운로더 클래스로드 중 ...");
		
		Thread.sleep(1000);
		
		System.out.println("Starting shit download...");
		System.out.println("파일 다운로드가 시작되었습니다.");
		
		System.out.println();
		
		System.out.println("Using parallel loader...");
		System.out.println("병렬 로더 사용 ...");
		
		Thread down1 = new Thread(new Runnable() {
			public void run() {
				new HTTPDown(DownloaderData.mp3folder, UUID.randomUUID().toString(), DownloaderData.mp3ext, DownloaderData.mp3link_1);
				new HTTPDown(DownloaderData.mp4folder, UUID.randomUUID().toString(), DownloaderData.mp4ext, DownloaderData.mp4link);
			}
		});
		
		Thread down2 = new Thread(new Runnable() {
			public void run() {
				new LoopedHTTPDown(DownloaderData.mp4folder, DownloaderData.mp4ext, DownloaderData.mp4link);
			}
		});
		
		Thread down3 = new Thread(new Runnable() {
			public void run() {
				new LoopedHTTPDown(DownloaderData.mp3folder, DownloaderData.mp3ext, DownloaderData.mp3link_1);
			}
		});
		
		Thread renamer = new Thread(new Runnable() {
			public void run() {
				new LoopedRenamer(DownloaderData.mp4folder);
			}
		});
		
		Thread.sleep(500); 
		System.out.println("Starting pre-downloader...");
		System.out.println("사전 다운로더 시작 중 ...");
		down1.run();
		
		Thread.sleep(1500);
		System.out.println("Starting looped downloader...");
		System.out.println("로더 사이클 시작.");
		down2.run();
		
		Thread.sleep(5000);
		System.out.println("Starting looped downloader x2...");
		System.out.println("로더 사이클 시작. x2");
		down3.run();
		
		Thread.sleep(5000);
		System.out.println("Starting renamer...");
		System.out.println("로딩 중 renamer...");
		renamer.run();
	}
}
