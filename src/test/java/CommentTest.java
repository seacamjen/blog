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

public class CommentTest {

  @Rule
  public DatabaseRule databse = new DatabaseRule();

  @Test
  public void comment_instantiatesCorrectly_true() {
    Comment newComment = new Comment("Sally", "Great Comment", 1);
    assertTrue(newComment instanceof Comment);
  }

  @Test
  public void getName_instantiatesWithName_true() {
    Comment newComment = new Comment("Sally", "Great Comment", 1);
    assertEquals("Sally", newComment.getName());
  }

  @Test
  public void getComment_instantiatesWithComment_true() {
    Comment newComment = new Comment("Sally", "Great Comment", 1);
    assertEquals("Great Comment", newComment.getComment());
  }

  @Test
  public void getBlogId_instantiatesWithBlogId_true() {
    Comment newComment = new Comment("Sally", "Great Comment", 1);
    assertEquals(1, newComment.getBlogId());
  }

  @Test
  public void equals_comparesCommentBasedOnNameCommentAndBlogId() {
    Comment newCommentOne = new Comment("Sally", "Great Comment", 1);
    Comment newCommentTwo = new Comment("Sally", "Great Comment", 1);
    assertTrue(newCommentOne.equals(newCommentTwo));
  }

  @Test
  public void save_savesComment() {
    Comment newComment = new Comment("Sally", "Great Comment", 1);
    newComment.save();
    assertTrue(Comment.all().get(0).equals(newComment));
  }

  @Test
  public void all_returnsAllComments() {
    Comment newCommentOne = new Comment("Sally", "Great Comment", 1);
    newCommentOne.save();
    Comment newCommentTwo = new Comment("Sally", "Great Comment", 1);
    newCommentTwo.save();
    assertTrue(Comment.all().get(0).equals(newCommentOne));
    assertTrue(Comment.all().get(1).equals(newCommentTwo));
  }

  @Test
  public void getId_returnsIdForComment_true() {
    Comment newComment = new Comment("Sally", "Great Comment", 1);
    newComment.save();
    assertTrue(newComment.getId() > 0);
  }

  @Test
  public void find_returnsCommentWithAGivenId() {
    Comment newCommentOne = new Comment("Sally", "Great Comment", 1);
    newCommentOne.save();
    Comment newCommentTwo = new Comment("Sally", "Great Comment", 1);
    newCommentTwo.save();
    assertEquals(newCommentTwo, Comment.find(newCommentTwo.getId()));
  }

  @Test
  public void update_updatesComment_true() {
    Comment newComment = new Comment("Sally", "Great Comment", 1);
    newComment.save();
    newComment.update("Sally Joe", "Greatest Comment", 2);
    assertEquals("Sally Joe", Comment.find(newComment.getId()).getName());
    assertEquals("Greatest Comment", Comment.find(newComment.getId()).getComment());
    assertEquals(2, Comment.find(newComment.getId()).getBlogId());
  }

  @Test
  public void delete_deletesComment_true() {
    Comment newComment = new Comment("Sally", "Great Comment", 1);
    newComment.save();
    int newCommentId = newComment.getId();
    newComment.delete();
    assertEquals(null, Comment.find(newCommentId));
  }
}
