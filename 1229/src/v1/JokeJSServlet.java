package v1;

import v1.HttpServlet;

public class JokeJSServlet extends HttpServlet {
    @Override
    public void doGet(Request req, Response resp) {
        resp.setStatus("200 OK");
        resp.setHeader("Content-Type", "application/javascript; charset=utf-8");
        resp.println("alert('很好很好');");
    }
}
