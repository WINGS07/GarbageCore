package io.WINGS.GarbageCore;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.UUID;

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
					System.out.println();
					UUID cuuid = UUID.randomUUID();
					uuidgens ++;
					System.out.println(uuidgens + " UUIDv4-gen Operations");
					File cfolder = new File(folder + cuuid + "/");
					cfolder.mkdirs();
					File cfile = new File(folder + cuuid + "/" + cuuid + ext);
					Files.copy(dest.toPath(), cfile.toPath());
					copyoperations ++;
					System.out.println(copyoperations + " Copy Operations");
				
					System.gc();
					gcoperations ++;
					System.out.println(gcoperations +
							" JAVAGC Operations, " +
							Runtime.getRuntime().maxMemory() / 1048576 +
							"MB mem MAX, " +
							(Runtime.getRuntime().maxMemory() - Runtime.getRuntime().freeMemory()) / 1048576 +
							"MB mem USED, " +
							Runtime.getRuntime().freeMemory() / 1048576 +
							"MB mem FREE");
				}
			}
        } catch (Exception ex) {
        	ex.printStackTrace();
            System.out.println("Copy or Download operation failed, " + ex.getMessage());
            
            System.out.println();
            
            System.out.println(gcoperations + " GC operations");
            System.out.println(Runtime.getRuntime().maxMemory() / 1048576 + "MB mem MAX");
            System.out.println((Runtime.getRuntime().maxMemory() - Runtime.getRuntime().freeMemory()) / 1048576 + "MB mem USED");
			System.out.println(Runtime.getRuntime().freeMemory() / 1048576 + "MB mem FREE");
			System.gc();
        }
	}
}
