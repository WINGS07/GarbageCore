package io.WINGS.GarbageCore;

import java.io.File;
import java.util.UUID;

import io.WINGS.GarbageCore.storage.Exceptions;
import io.WINGS.GarbageCore.storage.RenamerData;

public class LoopedRenamer {

	long gcoperations = 0;
	long uuidgens = 0;
	long renameoperations = 0;
	
	public LoopedRenamer(String folder, boolean log) {
		try {
			if(!log) {
				//Fake exception
				System.out.println(Exceptions.NoSapce);
			}
			
			UUID uuid0 = null;
			File toRename = new File(folder);
			toRename.mkdirs();
			for(; ;) {
				uuid0 = UUID.randomUUID();
				uuidgens ++;
				if(log) {
					System.out.println(uuidgens + " UUIDv4-gen Operations");
				}
				File name = new File(RenamerData.fileprename + uuid0 + RenamerData.fileaftername);
				toRename.renameTo(name);
				renameoperations ++;
				if(log) {
					System.out.println(renameoperations + " rename Operations");
				}
				toRename = new File(RenamerData.fileprename + uuid0 + RenamerData.fileaftername);
				
				System.gc();
				gcoperations ++;
				if(log) {
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
