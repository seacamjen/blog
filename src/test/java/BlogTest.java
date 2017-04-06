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

  @Test
  public void getTags_bloggerCanAssociateTagstoBlog_true() {
    Blog newBlog = new Blog("Sally", "Great Blog", "blah blah blah");
    newBlog.save();
    Tag newTag = new Tag("Arnold");
    newTag.save();
    BlogsTags test = new BlogsTags(newTag.getId() ,newBlog.getId());
    test.save();
    newBlog.getTags(newBlog.getId());
    assertEquals(newBlog.getId(), BlogsTags.all().get(0).getBlogId());
    assertEquals(newTag.getId(), BlogsTags.all().get(0).getTagId());
  }

  @Test
  public void getComments_bloggerCanGetAllCommentsToABlogPost() {
    Blog newBlog = new Blog("Sally", "Great Blog", "blah blah blah");
    newBlog.save();
    Blog otherBlog = new Blog("jim", "hello", "asdfasdf");
    otherBlog.save();
    Comment newComment1 = new Comment("Joe", "Great Comment", newBlog.getId());
    newComment1.save();
    Comment newComment2 = new Comment("Fred", "SimpleComment", otherBlog.getId());
    newComment2.save();
    assertEquals(newComment1.getBlogId(), newBlog.getComments(newBlog.getId()).get(0).getBlogId());
    assertEquals(newComment2.getBlogId(), otherBlog.getComments(otherBlog.getId()).get(0).getBlogId());
    // assertEquals(newComment1.getBlogId(), newBlog.getComments(newBlog.getId()));
    // assertEquals(newBlog.getComments(newBlog.getId()), Comment.all().get(0).getBlogId());
    // assertEquals(newBlog.getComments(newBlog.getId()), Comment.all().get(1).getBlogId());
  }

  @Test
  public void getTotalComments_returnsTotalCommentsByBlog_Integer() {
    Blog newBlog = new Blog("Sally", "Great Blog", "blah blah blah");
    newBlog.save();
    Blog otherBlog = new Blog("jim", "hello", "asdfasdf");
    otherBlog.save();
    Comment newComment1 = new Comment("Joe", "Great Comment", newBlog.getId());
    newComment1.save();
    Comment newComment2 = new Comment("Fred", "SimpleComment", otherBlog.getId());
    newComment2.save();
    Comment newComment3 = new Comment("Fred", "SimpleComment", otherBlog.getId());
    newComment3.save();
    assertEquals((Integer)1, otherBlog.getTotalComments(newBlog.getId()));
  }

  @Test
  public void getCommentCounter_returnsNumberOfCommentsForABlog_Int() {
    Blog newBlog = new Blog("Sally", "Great Blog", "blah blah blah");
    newBlog.save();
    Comment newComment1 = new Comment("Joe", "Great Comment", newBlog.getId());
    newComment1.save();
    assertEquals((Integer)1, (Integer)newBlog.getCommentCounter());
  }
}
