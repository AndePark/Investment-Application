//package persistence;
//
//
//import model.Portfolio;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.stream.Stream;
//
//public class JsonReader {
//    private String source;
//
//    public JsonReader(String source) {
//        this.source = source;
//    }
//
//    public Portfolio read() throws IOException {
//        String jsonData = readFile(source);
//        JSONObject jsonObject = new JSONObject(jsonData);
//        return parsedPortfolio(jsonObject);
//    }
//
//    private String readFile(String source) throws IOException {
//        StringBuilder builder = new StringBuilder();
//
//        try (Stream<String> stream = Files.lines( Paths.get(source), StandardCharsets.UTF_8)) {
//            stream.forEach(s -> builder.append(s));
//        }
//        return builder.toString();
//    }
//
//    }

