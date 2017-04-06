import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("blogs", Blog.most());
      model.put("tags", Tag.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String comment = request.queryParams("comment");
      int blogId = Integer.parseInt(request.queryParams("blogId"));
      Comment newComment = new Comment(name, comment, blogId);
      newComment.save();
      response.redirect(request.headers("Referer"));
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/blogs/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String blogAuthor = request.queryParams("blogAuthor");
      String blogTitle = request.queryParams("blogTitle");
      String blogInfo = request.queryParams("blogInfo");
      Blog newBlog = new Blog(blogAuthor, blogTitle, blogInfo);
      newBlog.save();
      String [] tags = request.queryParamsValues("tagsList");
      System.out.println(tags);
      for (String tag : tags) {
        newBlog.joinTags(Integer.parseInt(tag));
      }

      // model.put("tags", Tag.all());
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/blogs/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("allTags", Tag.all());
      model.put("template", "templates/blogger.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/tags/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String tagName = request.queryParams("tagName");
      Tag newTag = new Tag(tagName);
      newTag.save();
      response.redirect(request.headers("Referer"));
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


  }
}
