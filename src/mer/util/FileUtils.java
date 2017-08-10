package mer.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtils {

    public String readFileToString(File file) throws IOException {
        StringBuilder content = new StringBuilder();
        InputStream in = getClass().getClassLoader().getResourceAsStream(file.getPath());
        BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
        String line;
        while ((line = buffer.readLine()) != null) {
            content.append(line);
        }
        in.close();
        buffer.close();
        return content.toString();
    }
}
