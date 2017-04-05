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
}
