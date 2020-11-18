package io.WINGS.GarbageCore;

import java.io.*;
import java.net.*;
import java.nio.file.*;


public class HTTPDown {

	public HTTPDown(String folder, String filename, String ext, String link) {
		try {
			
			File dfolder = new File(folder);
			dfolder.mkdirs();
            File dest = new File(folder + filename + ext);

            //Connect
            URL url =
				new URL(link);

            // Creating con
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "WINGS07/GarbageCore");

            // Get input stream
            System.out.println("Downloading...");
            try (InputStream input = con.getInputStream()) {
            	Files.copy(input, dest.toPath());
			}

            System.out.println("Download success!");
        } catch (Exception ex) {
        	ex.printStackTrace();
            System.out.println("Download failed, " + ex.getMessage());
        }
	}
}
