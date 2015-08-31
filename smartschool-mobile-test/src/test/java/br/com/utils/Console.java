package br.com.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

import br.com.async.Logger;
import br.com.async.server.ServerRunnable;

public class Console {

//	static List<String> commandList = new ArrayList<String>();
//
//	public void addCommand(String command) {
//		commandList.add(command);
//	}

//	public void run() throws IOException {
//		StringBuilder commands = new StringBuilder();
//		for (String command : commandList) {
//			commands.append(command + " && ");
//		}
//		String finalCommand = commands.toString().substring(0,
//				commands.toString().length() - 3);
//		finalCommand = finalCommand.trim();
//		executeCommand(finalCommand);
//
//		commandList.clear();
//	}

	public synchronized void executeCommand(String command) throws IOException {
		final ArrayList<String> commands = new ArrayList<String>();
		commands.add("/bin/bash");
		commands.add("-l");
		commands.add("-c");
		commands.add(command);
		final ProcessBuilder p = new ProcessBuilder(commands);
		final Process process = p.start();
		
		Map<String, String> env = p.environment();
		for (String key : env.keySet())
			Logger.logInfo(key + ": " + env.get(key));
		
		InputStream input = process.getInputStream();
		final InputStreamReader reader = new InputStreamReader(input);
		final BufferedReader bufferReader = new BufferedReader(reader);

		String line;
		while ((line = bufferReader.readLine()) != null) {
			ServerRunnable.line += line;
			Logger.logInfo(line);
		}
		
		InputStream inputError = process.getErrorStream();
		final InputStreamReader readerError = new InputStreamReader(inputError);
		final BufferedReader bufferReaderError = new BufferedReader(readerError);
		
		while ((line = bufferReaderError.readLine()) != null) {
			ServerRunnable.line += line; 
			Logger.logSevere(line);
		}
		
		input.close();
		reader.close();
		bufferReader.close();
		inputError.close();
		readerError.close();
		bufferReaderError.close();
		process.destroy();

	}


//	public static void runSafe(final Runnable runnable) {
//		Objects.requireNonNull(runnable, "runnable");
//		if (Platform.isFxApplicationThread()) {
//			runnable.run();
//		} else {
//			Platform.runLater(runnable);
//		}
//	}
	
}