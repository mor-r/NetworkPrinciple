import com.sun.prism.PresentableState;
import jdk.internal.org.objectweb.asm.Handle;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Response {
    String status = "200 OK";
    Map<String, String> headers = new HashMap<>();
    StringBuilder bodyBuilder = new StringBuilder();

    Response() {
        headers.put("Content-Type", "text/html; charset=UTF-8");
    }

    public void println(String s) {
        bodyBuilder.append(s);
        bodyBuilder.append("\r\n");
    }

    public void writeAndFlush(OutputStream os) throws IOException {
        StringBuilder responseBuilder = new StringBuilder();
        responseBuilder.append("HTTP/1.0 ");
        responseBuilder.append(status);
        responseBuilder.append("\r\n");

        int len = bodyBuilder.toString().getBytes("UTF-8").length;
        headers.put("Content-Length", String.valueOf(len));

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            responseBuilder.append(entry.getKey());
            responseBuilder.append(": ");
            responseBuilder.append(entry.getValue());
            responseBuilder.append("\r\n");
        }
        responseBuilder.append("\r\n");
        responseBuilder.append(bodyBuilder);
        System.out.println(responseBuilder.toString());

        os.write(responseBuilder.toString().getBytes("UTF-8"));
        os.flush();
    }
}
