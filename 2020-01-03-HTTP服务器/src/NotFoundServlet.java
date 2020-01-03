public class NotFoundServlet extends HttpServlet {
    @Override
    public void doGet(Request request, Response response) {
        response.status = "404 Not Found";
        response.println("<h1>没有这个页面</h1>");
    }
}
