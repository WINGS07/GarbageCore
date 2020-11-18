package io.WINGS.GarbageCore;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

public class LoopedHTTPDown
{
	public LoopedHTTPDown(String folder, String ext, String link) {
		long downloads = 0;
		long copyoperations = 0;
		long uuidgens = 0;
		long gcoperations = 0;
		
		try {
			UUID uuid = UUID.randomUUID();
			File filefolder = new File(folder + uuid + "/");
			filefolder.mkdirs();
            File dest = new File(folder + uuid + "/" + uuid + ext);
			downloads ++;
            //Connect
            URL url =
				new URL(link);

            // Creating con
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "WINGS07/GarbageCore");

            // Get input stream
            System.out.println("Downloading x" + downloads + "...");
            try (InputStream input = con.getInputStream()) {
            	Files.copy(input, dest.toPath());
				System.out.println("Download x" + downloads + " success!");
				for(; ;) {
				UUID cuuid = UUID.randomUUID();
				uuidgens ++;
				System.out.println(uuidgens + " UUIDv4 created");
				File cfolder = new File(folder + cuuid + "/");
				cfolder.mkdirs();
				File cfile = new File(folder + cuuid + "/" + cuuid + ext);
				Files.copy(dest.toPath(), cfile.toPath());
				copyoperations ++;
				System.out.println(copyoperations + " Copy Operations");
				System.out.println();
				
				System.gc();
				gcoperations ++;
				System.out.println(gcoperations +
						" JAVAGC operations, " +
						Runtime.getRuntime().maxMemory() / 1073741824 +
						"GB mem MAX, " +
						Runtime.getRuntime().freeMemory() / 1073741824 +
						"GB mem free");
				}
			}
        } catch (Exception ex) {
        	ex.printStackTrace();
            System.out.println("Download failed, " + ex.getMessage());
            
            System.out.println();
            
            System.out.println(gcoperations + " GC operations");
			System.out.println(Runtime.getRuntime().freeMemory() / 1073741824 + "GB mem free");
			System.out.println(Runtime.getRuntime().maxMemory() / 1073741824 + "GB mem MAX");
        }
	}
}
