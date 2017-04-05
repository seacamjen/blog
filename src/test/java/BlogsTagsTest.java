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

public class BlogsTagsTest {

  @Rule
  public DatabaseRule databse = new DatabaseRule();

  @Test
  public void blog_instantiatesCorrectly_true() {
    Blog newBlog = new Blog("Sally", "Great Blog", "blah blah blah");
    assertTrue(newBlog instanceof Blog);
  }

  // @Test
  // public void getAuthor_instantiatesWithAuthor_true() {
  //   Blog newBlog = new Blog("Sally", "Great Blog", "blah blah blah");
  //   assertEquals("Sally", newBlog.getAuthor());
  // }
  //
  // @Test
  // public void getAuthor_instantiatesWithAuthor_true() {
  //   Blog newBlog = new Blog("Sally", "Great Blog", "blah blah blah");
  //   assertEquals("Sally", newBlog.getAuthor());
  // }
}
