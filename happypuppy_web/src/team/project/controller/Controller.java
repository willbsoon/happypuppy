package team.project.controller;

import team.project.service.NextPage;
import team.project.service.UserService;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Servlet implementation class Controller
 */
//java class를 servlet으로 바꿔주는 컨트롤러
@WebServlet(
//		value = "*.puppy",
		urlPatterns = "*.puppy",
		initParams = {
			@WebInitParam(name = "url", value = "/team/project/util/url.properties") })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Map<String, UserService> map = new HashMap<>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stu
		System.out.println("1. Controller In");
		System.out.println("config.getInitParameter('url') : " + config.getInitParameter("url"));
		Properties prop = new Properties();
		try {
			prop.load(getClass().getResourceAsStream(config.getInitParameter("url")));
			System.out.println("prop : " + prop);
			Iterator<Object> it = prop.keySet().iterator();

			while (it.hasNext()) {
				String key = (String) it.next();
				System.out.println("key : " + key);
				Class clazz = Class.forName(prop.getProperty(key));
				System.out.println("Class.forName(prop.getProperty(key) : " + clazz);
				UserService service;
				service = (UserService) clazz.newInstance();
				System.out.println("clazz.newInstance() : " + service);
				System.out.println();
				map.put(key, service);
			}
			/*
			 * for( Object key : prop.keySet() ) { }
			 */

		} catch (IOException | InstantiationException | ClassNotFoundException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/*
	 * protected void service(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { // TODO Auto-generated
	 * method stub }
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		String url = request.getRequestURI();
		System.out.println("request.getRequestURI() : " + url);
		int cmdIdx = url.lastIndexOf("/") + 1;
		String cmd = url.substring(cmdIdx);
		System.out.println("cmd = " + cmd+" / "+map.get(cmd).toString());
		NextPage nextPage = map.get(cmd).execute(request, response);
		// System.out.println(nextPage.getPageName());

		// 2. nextPage 체크
		if (nextPage == null) {
			System.out.println("null 입니다.");
		} else {
			if (nextPage.isRedirect()) {
				System.out.println("cmd = redirect "+nextPage.getPageName());
				response.sendRedirect(nextPage.getPageName());
			} else {
				System.out.println("cmd = forward");
				request.getRequestDispatcher(nextPage.getPageName()).forward(request, response);

			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);

	}

}
