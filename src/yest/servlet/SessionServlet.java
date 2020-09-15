package yest.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionServlet extends BaseServlet {
    /**
     * 往Session中保存數據
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void setAttribute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("key1", "value1");
        resp.getWriter().write("已經往Session中保存了數據");

    }

    protected void defaultLife(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 獲取了Session的默認超時時間
        int maxInactiveInterval = req.getSession().getMaxInactiveInterval();

        resp.getWriter().write("Session的默認超時時間為：" + maxInactiveInterval + " 秒 ");

    }

    protected void life3(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 先獲取Session對象
        HttpSession session = req.getSession();
        // 設置當前Session3秒後超時
        session.setMaxInactiveInterval(3);

        resp.getWriter().write("當前Session已經設置3秒後超時");
    }

    protected void deleteNow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 先獲取Session對象
        HttpSession session = req.getSession();
        // 讓Session馬上超時
        session.invalidate();

        resp.getWriter().write("Session已经設置為超時（無效）");
    }

    /**
     * 獲取Session域中的數據
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getAttribute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object attribute = req.getSession().getAttribute("key1");
        resp.getWriter().write("從Session中獲取出key1的數據是：" + attribute);
    }



    protected void createOrGetSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 創建和獲取Session會話對象
        HttpSession session = req.getSession();
        // 判断當前Session會话，是否是新創建出来的
        boolean isNew = session.isNew();
        // 獲取Session繪畫的唯一標示 id
        String id = session.getId();

        resp.getWriter().write("得到的Session，它的id是：" + id + " <br /> ");
        resp.getWriter().write("這個Session是否是新創建的：" + isNew + " <br /> ");


    }
}
