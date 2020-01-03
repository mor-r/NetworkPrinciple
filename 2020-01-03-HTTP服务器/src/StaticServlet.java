import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class StaticServlet extends HttpServlet {
    @Override
    // 127.0.0/hello.html
    public void doGet(Request request, Response response) {
        // 1. 取 url 的后缀
        int index = request.url.lastIndexOf('.');
        String suffix = request.url.substring(index + 1);
        if (suffix.equals("css")) {
            response.headers.put("Content-Type", "text/css; charset=UTF-8");
        }

        // 1. 根据 url 找到本地对应的文件名
        String filename = "docBase" + request.url;
        // 2. 读取文件内容并写入 response 中
        try {
            InputStream is = new FileInputStream(filename);
            Scanner scanner = new Scanner(is, "UTF-8");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                response.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
