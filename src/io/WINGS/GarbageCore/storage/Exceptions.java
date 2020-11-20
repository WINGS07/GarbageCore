package io.WINGS.GarbageCore.storage;

import java.util.UUID;

public interface Exceptions {

	String NoSapce = "java.nio.FileSystemException: " +
			"videos/controller.jar -> " + 
			UUID.randomUUID() +
			".jar: No space left on device" +
			" at sun.nio.fs.UnixException.translateToIEOxepton" +
			"(UnixException) at sun.nio.UnixException" +
			".rethrowAsIOException(UnixException)" +
			" at sun.nio.UnixCopyFile(UnixCopyFile.java:581)";
}
