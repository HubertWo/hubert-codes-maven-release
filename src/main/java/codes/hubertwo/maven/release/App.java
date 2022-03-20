package codes.hubertwo.maven.release;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.jar.Manifest;

import static java.lang.System.out;

public class App {
    private static final String NO_MANIFEST_DETAILS_MESSAGE = "No manifest file found. Did you build the package using Maven?";

    public static void main(String[] args) throws Exception {
        out.println("Hello! Application release details:");
        out.println(getManifest());
    }

    private static String getManifest() throws Exception {
        try (InputStream manifestStream = App.class.getClassLoader().getResourceAsStream("META-INF/MANIFEST.MF")) {
            if (manifestStream != null) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                new Manifest(manifestStream).write(out);
                String manifestDetails = out.toString();
                return manifestDetails.isBlank() ? NO_MANIFEST_DETAILS_MESSAGE : manifestDetails;
            }
        }

        return NO_MANIFEST_DETAILS_MESSAGE;
    }
}