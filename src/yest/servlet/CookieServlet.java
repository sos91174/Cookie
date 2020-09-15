package yest.servlet;

import yest.util.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieServlet extends BaseServlet {


    protected void testPath(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("path1", "path1");
        // getContextPath() ===>>>>  得到工程路径
        cookie.setPath( req.getContextPath() + "/abc" ); // ===>>>>  /工程路径/abc
        resp.addCookie(cookie);
        resp.getWriter().write("創建了一个带有Path路徑的Cookie");
    }


    protected void updateCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        方案一：
//        1、先創建一个要修改的同名的Cookie對象
//         2、在構造器，同時賦予新的Cookie值。
//        Cookie cookie = new Cookie("key1","newValue1");
//        3、調用response.addCookie( Cookie ); 通知客戶端 保存修改
//        resp.addCookie(cookie);

//        方案二：
//        1、先查找到需要修改的Cookie對象
        Cookie cookie = CookieUtils.findCookie("key2", req.getCookies());
        if (cookie != null) {
//            2、調用setValue()方法賦予新的Cookie值。
            cookie.setValue("newValue2");
//        3、調用response.addCookie()通知客戶端保存修改
            resp.addCookie(cookie);
        }


        resp.getWriter().write("key1的Cookie已經修改好");

    }

    /**
     * 設置存活半小時的Cookie
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void life3600(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie cookie = new Cookie("life3600", "life3600");
        cookie.setMaxAge(30 * 60); // 設置Cookie一小時之後被删除。
        resp.addCookie(cookie);
        resp.getWriter().write("已經創建了一個存活半小時的Cookie");

    }

    /**
     * 馬上删除一個Cookie
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteNow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 先找到你要删除的Cookie對象
        Cookie cookie = CookieUtils.findCookie("key4", req.getCookies());
        if (cookie != null) {
            // 調用setMaxAge(0);
            cookie.setMaxAge(0); // 表示馬上删除，都不需要等待瀏覽器關閉
            // 調用response.addCookie(cookie);
            resp.addCookie(cookie);

            resp.getWriter().write("key4的Cookie已經被刪除");
        }

    }

    /**
     * 默認的會話级别的Cookie
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void defaultLife(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("defalutLife","defaultLife");
        cookie.setMaxAge(-1);//設置存活时間
        resp.addCookie(cookie);
    }

    protected void getCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie[] cookies = req.getCookies();



        for (Cookie cookie : cookies) {
            // getName方法返回Cookie的key（名）
            // getValue方法返回Cookie的value值
            resp.getWriter().write("Cookie[" + cookie.getName() + "=" + cookie.getValue() + "] <br/>");
        }


        Cookie iWantCookie = CookieUtils.findCookie("key1", cookies);

//        for (Cookie cookie : cookies) {
//            if ("key2".equals(cookie.getName())) {
//                iWantCookie = cookie;
//                break;
//            }
//        }
        // 如果不等於null，說明賦予過，也就是找到了需要的Cookie
        if (iWantCookie != null) {
            resp.getWriter().write("找到了需要的Cookie");
        }


    }

    protected void createCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 創建Cookie對象
        Cookie cookie = new Cookie("key4", "value4");
        //2 通知客戶端保存Cookie
        resp.addCookie(cookie);
        //1 創建Cookie對象
        Cookie cookie1 = new Cookie("key5", "value5");
        //2 通知客戶端保存Cookie
        resp.addCookie(cookie1);

        resp.getWriter().write("Cookie創建成功");
    }
}
