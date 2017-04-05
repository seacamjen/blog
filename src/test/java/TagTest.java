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

public class TagTest {

  @Rule
  public DatabaseRule databse = new DatabaseRule();

  @Test
  public void tag_instantiatesCorrectly_true() {
    Tag newTag = new Tag("Travel");
    assertTrue(newTag instanceof Tag);
  }

  @Test
  public void getName_returnsName_true() {
    Tag newTag = new Tag("Arnold");
    assertEquals("Arnold", newTag.getName());
  }

  @Test
  public void save_savestag_true() {
    Tag newTag = new Tag("Arnold");
    newTag.save();
    assertTrue(Tag.all().get(0).equals(newTag));
  }

  @Test
  public void all_returnsAllTag_true() {
    Tag newTag1 = new Tag("Arnold");
    newTag1.save();
    Tag newTag2 = new Tag("BArnold");
    newTag2.save();
    assertEquals(Tag.all().get(0), newTag1);
    assertEquals(Tag.all().get(1), newTag2);
  }

  @Test
  public void equals_comparesTagBasedOnName_true() {
    Tag newtag = new Tag("Arnold");
    Tag newtag1 = new Tag("Arnold");
    assertTrue(newtag.equals(newtag1));
  }

  @Test
  public void getId_returnsIdForTag_true() {
    Tag newtag = new Tag("Arnold");
    newtag.save();
    assertTrue(newtag.getId() > 0);
  }

  @Test
  public void find_returnsTagWithGivenId_true() {
    Tag newTag = new Tag("arnold");
    newTag.save();
    Tag otherTag = new Tag("arnold");
    otherTag.save();
    assertEquals(otherTag, Tag.find(otherTag.getId()));
    assertEquals(newTag, Tag.find(newTag.getId()));
  }

  @Test
  public void delete_deletesTag_true() {
    Tag newTag = new Tag("Sally");
    newTag.save();
    int newTagId = newTag.getId();
    newTag.delete();
    assertEquals(null, Tag.find(newTagId));
  }

  @Test
  public void update_updatesTag_true() {
    Tag newTag = new Tag("Sally");
    newTag.save();
    newTag.update("Sally Joe");
    assertEquals("Sally Joe", Tag.find(newTag.getId()).getName());
  }
}
