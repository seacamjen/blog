import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;

public class Comment {
  private String name;
  private String comment;
  private int blog_id;
  private int id;

  public Comment(String name, String comment, int blog_id) {
    this.name = name;
    this.comment = comment;
    this.blog_id = blog_id;
  }

  public String getName() {
    return name;
  }

  public String getComment() {
    return comment;
  }

  public int getBlogId() {
    return blog_id;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object otherComment) {
    if (!(otherComment instanceof Comment)) {
      return false;
    } else {
      Comment newComment = (Comment) otherComment;
      return this.getName().equals(newComment.getName()) &&
             this.getComment().equals(newComment.getComment()) &&
             this.getBlogId() == newComment.getBlogId();
    }
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sqlsave = "INSERT INTO comments (name, comment, blog_id) VALUES (:name, :comment, :blog_id);";
      this.id = (int) con.createQuery(sqlsave, true)
        .addParameter("name", name)
        .addParameter("comment", comment)
        .addParameter("blog_id", blog_id)
        .executeUpdate()
        .getKey();

      String sqlupdate = "UPDATE blogs SET (comment_counter) = (comment_counter + 1) WHERE id = :blog_id;";
      System.out.println(this.getBlogId());
      con.createQuery(sqlupdate)
        .addParameter("blog_id", this.getBlogId())
        .executeUpdate();
    }
  }

  public static List<Comment> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM comments;";
      return con.createQuery(sql)
        .executeAndFetch(Comment.class);
    }
  }

  public static Comment find(int id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM comments WHERE id = :id;";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Comment.class);
    }
  }

  public void update(String name, String comment, int blog_id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "UPDATE comments SET (name, comment, blog_id) = (:name, :comment, :blog_id);";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("comment", comment)
        .addParameter("blog_id", blog_id)
        .executeUpdate();
    }
  }

  public void delete() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM comments WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

}
