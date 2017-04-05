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

}
