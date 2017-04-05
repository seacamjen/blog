import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;

public class Blog {
  private String author;
  private String title;
  private String info;
  private int id;

  public Blog(String author, String title, String info) {
    this.author = author;
    this.title = title;
    this.info = info;
  }

  public String getAuthor() {
    return author;
  }

  public String getTitle() {
    return title;
  }

  public String getInfo() {
    return info;
  }

  public int getId() {
    return id;
  }

  public void joinTags(int tagId) {
    BlogsTags newBlogsTags = new BlogsTags(this.id, tagId);
    newBlogsTags.save();
  }

  @Override
  public boolean equals(Object otherBlog) {
    if (!(otherBlog instanceof Blog)) {
      return false;
    } else {
      Blog newBlog = (Blog) otherBlog;
      return this.getAuthor().equals(newBlog.getAuthor()) &&
             this.getTitle().equals(newBlog.getTitle()) &&
             this.getInfo().equals(newBlog.getInfo());
    }
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO blogs (author, title, info) VALUES (:author, :title, :info);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("author", author)
        .addParameter("title", title)
        .addParameter("info", info)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Blog> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM blogs;";
      return con.createQuery(sql)
        .executeAndFetch(Blog.class);
    }
  }

  public static Blog find(int id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM blogs WHERE id = :id;";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Blog.class);
    }
  }

  public void update(String author, String title, String info) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "UPDATE blogs SET (author, title, info) = (:author, :title, :info);";
      con.createQuery(sql)
        .addParameter("author", author)
        .addParameter("title", title)
        .addParameter("info", info)
        .executeUpdate();
    }
  }

  public void delete() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM blogs WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public static List<Blog> getTags(int id) {
   try (Connection con = DB.sql2o.open()) {
     String sql = "SELECT tags.name FROM blogs INNER JOIN blogs_tags ON :id = blogs_tags.blog_id INNER JOIN tags ON blogs_tags.tag_id = tags.id;";
     return con.createQuery(sql)
       .addParameter("id", id)
       .executeAndFetch(Blog.class);
   }
 }

 public static List<Comment> getComments(int id) {
  try (Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM comments WHERE blog_id = :id;";
    return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Comment.class);
  }
}

}
