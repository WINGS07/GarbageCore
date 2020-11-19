package io.WINGS.GarbageCore;

import java.io.File;
import java.util.UUID;

public class LoopedRenamer {

	long gcoperations = 0;
	long uuidgens = 0;
	long renameoperations = 0;
	
	public LoopedRenamer(String folder) {
		try {
			UUID uuid0 = null;
			File toRename = new File(folder);
			for(; ;) {
				uuid0 = UUID.randomUUID();
				uuidgens ++;
				System.out.println(uuidgens + " UUIDv4-gen Operations");
				File name = new File("." + uuid0 + "/");
				toRename.renameTo(name);
				renameoperations ++;
				System.out.println(renameoperations + " rename Operations");
				toRename = new File("." + uuid0 + "/");
				
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
		} catch (Exception ex) {
			ex.printStackTrace();
            System.out.println("Rename or UUIDGEN operation failed, " + ex.getMessage());
            
            System.out.println();
            
            System.out.println(gcoperations + " GC operations");
            System.out.println(Runtime.getRuntime().maxMemory() / 1048576 + "MB mem MAX");
            System.out.println((Runtime.getRuntime().maxMemory() - Runtime.getRuntime().freeMemory()) / 1048576 + "MB mem USED");
			System.out.println(Runtime.getRuntime().freeMemory() / 1048576 + "MB mem FREE");
			System.gc();
		}
	}
}
