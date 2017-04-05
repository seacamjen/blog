import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;

public class BlogTest {

  @Rule
  public DatabaseRule databse = new DatabaseRule();

  @Test
  public void blog_instantiatesCorrectly_true() {
    Blog newBlog = new Blog("Sally", "Great Blog", "blah blah blah");
    assertTrue(newBlog instanceof Blog);
  }

  @Test
  public void getAuthor_instantiatesWithAuthor_true() {
    Blog newBlog = new Blog("Sally", "Great Blog", "blah blah blah");
    assertEquals("Sally", newBlog.getAuthor());
  }

  @Test
  public void getTitle_instantiatesWithTitle_true() {
    Blog newBlog = new Blog("Sally", "Great Blog", "blah blah blah");
    assertEquals("Great Blog", newBlog.getTitle());
  }

  @Test
  public void getInfo_instantiatesWithInfo_true() {
    Blog newBlog = new Blog("Sally", "Great Blog", "blah blah blah");
    assertEquals("blah blah blah", newBlog.getInfo());
  }

  @Test
  public void equals_comparesBlogBasedOnAuthorTitleAndInfo() {
    Blog newBlogOne = new Blog("Sally", "Great Blog", "blah blah blah");
    Blog newBlogTwo = new Blog("Sally", "Great Blog", "blah blah blah");
    assertTrue(newBlogOne.equals(newBlogTwo));
  }

  @Test
  public void save_savesBlog() {
    Blog newBlog = new Blog("Sally", "Great Blog", "blah blah blah");
    newBlog.save();
    assertTrue(Blog.all().get(0).equals(newBlog));
  }

  @Test
  public void all_returnsAllBlogs() {
    Blog newBlogOne = new Blog("Sally", "Great Blog", "blah blah blah");
    newBlogOne.save();
    Blog newBlogTwo = new Blog("Sally", "Great Blog", "blah blah blah");
    newBlogTwo.save();
    assertTrue(Blog.all().get(0).equals(newBlogOne));
    assertTrue(Blog.all().get(1).equals(newBlogTwo));
  }

  @Test
  public void getId_returnsIdForBlog_true() {
    Blog newBlog = new Blog("Sally", "Great Blog", "blah blah blah");
    newBlog.save();
    assertTrue(newBlog.getId() > 0);
  }

  @Test
  public void find_returnsBlogWithAGivenId() {
    Blog newBlogOne = new Blog("Sally", "Great Blog", "blah blah blah");
    newBlogOne.save();
    Blog newBlogTwo = new Blog("Sally", "Great Blog", "blah blah blah");
    newBlogTwo.save();
    assertEquals(newBlogTwo, Blog.find(newBlogTwo.getId()));
  }

  @Test
  public void update_updatesBlog_true() {
    Blog newBlog = new Blog("Sally", "Great Blog", "blah blah blah");
    newBlog.save();
    newBlog.update("Sally Joe", "Greatest Blog", "blah blah blee");
    assertEquals("Sally Joe", Blog.find(newBlog.getId()).getAuthor());
    assertEquals("Greatest Blog", Blog.find(newBlog.getId()).getTitle());
    assertEquals("blah blah blee", Blog.find(newBlog.getId()).getInfo());
  }

  @Test
  public void delete_deletesBlog_true() {
    Blog newBlog = new Blog("Sally", "Great Blog", "blah blah blah");
    newBlog.save();
    int newBlogId = newBlog.getId();
    newBlog.delete();
    assertEquals(null, Blog.find(newBlogId));
  }

  // @Test
  // public void getTags_returnsTagsBasedOnId_true() {
  //
  // }
}
