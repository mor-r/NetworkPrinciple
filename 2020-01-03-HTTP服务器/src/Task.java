import java.io.File;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Task implements Runnable {
    private Socket socket;
    private Map<String, HttpServlet> urlMap = new HashMap<>();
    private HttpServlet notFoundServlet = new NotFoundServlet();
    private HttpServlet staticServlet = new StaticServlet();

    public Task(Socket socket) {
        this.socket = socket;
        urlMap.put("/login", new LoginServlet());
    }

    @Override
    public void run() {
        try {
            Request request = Request.parse(socket.getInputStream());
            System.out.println(request);
            Response response = new Response();
            HttpServlet servlet = urlMap.get(request.url);
            if (servlet == null) {
                String filename = "docBase" + request.url;
                File file = new File(filename);
                if (!file.exists()) {
                    servlet = notFoundServlet;
                } else {
                    servlet = staticServlet;
                }
            }
            servlet.doGet(request, response);
            response.writeAndFlush(socket.getOutputStream());
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
