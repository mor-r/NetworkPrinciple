import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Request {
    String method;
    String url;
    Map<String, String> parameters = new HashMap<>();
    Map<String, String> headers = new HashMap<>();

    @Override
    public String toString() {
        return "Request{" +
                "method='" + method + '\'' +
                ", url='" + url + '\'' +
                ", parameters=" + parameters +
                ", headers=" + headers +
                '}';
    }

    public static Request parse(InputStream is) {
        Request request = new Request();

        Scanner scanner = new Scanner(is, "UTF-8");
        String line = scanner.nextLine();
        String[] group = line.split(" ");
        request.method = group[0];
        String[] group2 = group[1].split("\\?");
        request.url = group2[0];

        while (!(line = scanner.nextLine()).isEmpty()) {
            String[] kv = line.split(":");
            String key = kv[0].trim();
            String value = kv[1].trim();
            request.headers.put(key, value);
        }

        return request;
    }
}
