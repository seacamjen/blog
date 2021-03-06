import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;

public class Blog implements DatabaseManagement {
  private String author;
  private String title;
  private String info;
  private int id;
  private int comment_counter;

  public Blog(String author, String title, String info) {
    this.author = author;
    this.title = title;
    this.info = info;
    this.comment_counter = 0;
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

  public Integer getCommentCounter() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "Select comment_counter From blogs WHERE id = :id;";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeScalar(Integer.class);
    }
  }

  public void joinTags(int tagId) {
    BlogsTags newBlogsTags = new BlogsTags(tagId, this.id);
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

  @Override
  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO blogs (author, title, info, comment_counter) VALUES (:author, :title, :info, :comment_counter);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("author", author)
        .addParameter("title", title)
        .addParameter("info", info)
        .addParameter("comment_counter", comment_counter)
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

  public static List<Blog> most() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM blogs ORDER BY comment_counter DESC;";
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

  @Override
  public void delete() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM blogs WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();

        String sql1 = "DELETE FROM blogs_tags USING blogs WHERE blogs_tags.blog_id = :id;";
        con.createQuery(sql1)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public static List<Tag> getTags(int id) {
   try (Connection con = DB.sql2o.open()) {
     String sql = "SELECT tags.name FROM tags INNER JOIN blogs_tags ON blogs_tags.tag_id = tags.id INNER JOIN blogs ON blogs.id = blogs_tags.blog_id WHERE blogs.id = :id;";
     return con.createQuery(sql)
       .addParameter("id", id)
       .executeAndFetch(Tag.class);
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

  public static Integer getTotalComments(int id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT COUNT (*) FROM comments WHERE blog_id = :id;";
      return con.createQuery(sql)
       .addParameter("id", id)
       .executeScalar(Integer.class);
     }
  }

}
