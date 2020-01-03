public class LoginServlet extends HttpServlet {
    @Override
    public void doGet(Request request, Response response) {
        response.println("已登录");
    }
}
