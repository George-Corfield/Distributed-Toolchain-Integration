package UoBToolchainGroup.DistributedToolchainIntegration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

public class PythonCaller {

    public static String call(String filePath, String[] args) {
        try {

            //build the command
            List<String> command = new ArrayList<String>();
            command.add("py");
            command.add(filePath);
            for(String elem : args){
                command.add(elem);
            }

            // Build the command to execute the Python script
            ProcessBuilder processBuilder = new ProcessBuilder(command);

            // Start the process
            Process process = processBuilder.start();

            // Read the output of the Python script
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder resultBuilder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                resultBuilder.append(line);
            }

            // Wait for the process to finish
            int exitCode = process.waitFor();

            int len;
            if ((len = process.getErrorStream().available()) > 0) {
            byte[] buf = new byte[len];
            process.getErrorStream().read(buf);
            System.err.println("Command error:\t\""+new String(buf)+"\"");
}

            // Check if the process terminated successfully
            if (exitCode == 0) {
                // Parse the result and use it in your Java code
                String resultString = resultBuilder.toString();
                return resultString;
            } else {
                // Handle errors
                System.err.println("Error executing Python script: " + exitCode);
                return "Error";
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error";
        }
    }
}
