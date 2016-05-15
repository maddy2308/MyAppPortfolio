package nanodegree.android.udacity.myappportfolio.Model;

public class PosterModel {

  private String posterPath;

  private String movieName;

  public PosterModel(String posterPath, String movieName) {
    this.posterPath = posterPath;
    this.movieName = movieName;
  }

  public String getPosterPath() {
    return posterPath;
  }

  public void setPosterPath(String posterPath) {
    this.posterPath = posterPath;
  }

  public String getMovieName() {
    return movieName;
  }

  public void setMovieName(String movieName) {
    this.movieName = movieName;
  }
}
