package org.tyaa.javaee.gae.objectify.pasd2817;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tyaa.javaee.gae.objectify.pasd2817.model.Category;
import org.tyaa.javaee.gae.objectify.pasd2817.model.RestApiResponse;

import com.google.gson.Gson;

import static com.googlecode.objectify.ObjectifyService.ofy;

@WebServlet(name = "HelloAppEngine", urlPatterns = { "/category" })
public class HelloAppEngine extends HttpServlet {

	private Gson gson;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		gson = new Gson();
	}
	
	public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// response.setContentType("text/plain");
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		// response.getWriter().print("Hello App Engine!\r\n");
		
		try (PrintWriter out = resp.getWriter()) {
			if (req.getParameterMap().containsKey("action")) {
				switch (req.getParameter("action")) {
				case "create": {
					Category c = new Category();
					c.name = req.getParameter("name");
					
					/*
					 BufferedReader reader = req.getReader();
					Category category = gson.fromJson(reader, Category.class);
					 */
					
					ofy().save().entity(c).now();
					// out.printf("Category %s was created with id %d", c.name, c.id);
					out.print(
						gson.toJson(
							new RestApiResponse(
								"success"
								, String.format("Category %s was created with id %d", c.name, c.id)
								, null
							)
						)
					);
					break;
				}
				case "get-all": {
					try {
						List<Category> categories = ofy().load().type(Category.class).list();
						System.out.println(categories);
						out.print(gson.toJson(new RestApiResponse("success", null, categories)));
						System.out.println(gson.toJson(new RestApiResponse("success", null, categories)));
					} catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
					
					/* for (Category c : categories) {
						out.printf("Category %d : %s<br>", c.id, c.name);
					} */
					break;
				}
				default:
					break;
				}
			}
		} catch (Exception ex) {
			try (PrintWriter out = resp.getWriter()) {
				if (ex.getMessage() != null) {
					out.printf("Error: %s", ex.getMessage());
				} else {
					out.printf("Unknown server error");
				}
			}
		}
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		System.out.println(req.getParameter("action"));
		processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(req, resp);
	}
}