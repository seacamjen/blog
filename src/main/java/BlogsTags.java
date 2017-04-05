import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;

public class BlogsTags {
  private int tag_id;
  private int blog_id;
  private int id;

  public BlogsTags(int tag_id, int blog_id) {
    this.tag_id = tag_id;
    this.blog_id = blog_id;
  }

  public int getTagId() {
    return tag_id;
  }

  public int getBlogId() {
    return blog_id;
  }

  public int getId() {
    return id;
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO blogs_tags (tag_id, blog_id) VALUES (:tag_id, :blog_id);";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("tag_id", tag_id)
      .addParameter("blog_id", blog_id)
      .executeUpdate()
      .getKey();
    }
  }

  public static List<BlogsTags> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM blogs_tags;";
      return con.createQuery(sql)
      .executeAndFetch(BlogsTags.class);
    }
  }

  @Override
  public boolean equals(Object otherBlogsTags) {
    if (!(otherBlogsTags instanceof BlogsTags)) {
      return false;
    } else {
      BlogsTags newBlogsTags = (BlogsTags) otherBlogsTags;
      return this.getTagId() == newBlogsTags.getTagId() &&
             this.getBlogId() == newBlogsTags.getBlogId();
    }
  }

  public static BlogsTags find(int id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM blogs_tags WHERE id = :id;";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(BlogsTags.class);
    }
  }

}
