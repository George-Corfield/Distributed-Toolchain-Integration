package UoBToolchainGroup.DistributedToolchainIntegration.model;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.List;

public class BuildMultiPartRequestBody {
        //java io files
        //this allows for multiple files to be send in a POST request
        public static HttpRequest.BodyPublisher buildMultiPartRequestBody(List<java.io.File> files) throws IOException {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    
            // Start the multipart/form-data content
            String boundary = "boundary";
            String lineSeparator = "\r\n";
    
            // Add each file to the request body
            for (java.io.File file : files) {
                outputStream.write(("--" + boundary + lineSeparator).getBytes());
                outputStream.write(("Content-Disposition: form-data; name=\"file\";; filename=\"" + file.getName() + "\"" + lineSeparator).getBytes());
                outputStream.write(("Content-Type: application/octet-stream" + lineSeparator + lineSeparator).getBytes());
    
                FileInputStream inputStream = new FileInputStream(file);
                byte[] buffer = new byte[8192];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.write(lineSeparator.getBytes());
                inputStream.close();
            }
    
            // Add the closing boundary
            outputStream.write(("--" + boundary + "--" + lineSeparator).getBytes());
    
            return HttpRequest.BodyPublishers.ofByteArray(outputStream.toByteArray());
        }

        public static HttpRequest.BodyPublisher buildMultiPartRequestBodyBytes(List<byte[]> fileData) throws IOException {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    
            // Start the multipart/form-data content
            String boundary = "boundary";
            String lineSeparator = "\r\n";
    
            // Add each file to the request body
            for (byte[] data : fileData) {
                outputStream.write(("--" + boundary + lineSeparator).getBytes());
                outputStream.write(("Content-Disposition: form-data; name=\"file\"; filename=\"file.txt\"" + lineSeparator).getBytes());
                outputStream.write(("Content-Type: application/octet-stream" + lineSeparator + lineSeparator).getBytes());
                outputStream.write(data);
                outputStream.write(lineSeparator.getBytes());
            }
    
            // Add the closing boundary
            outputStream.write(("--" + boundary + "--" + lineSeparator).getBytes());
    
            return HttpRequest.BodyPublishers.ofByteArray(outputStream.toByteArray());
        }

}
