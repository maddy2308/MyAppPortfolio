package nanodegree.android.udacity.myappportfolio.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class ResultPage {

  @SerializedName("page")
  @Expose
  private Integer page;

  @SerializedName("results")
  @Expose
  private List<Movie> movies = new ArrayList<Movie>();

  @SerializedName("total_results")
  @Expose
  private Integer totalResults;

  @SerializedName("total_pages")
  @Expose
  private Integer totalPages;

  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public List<Movie> getResults() {
    return movies;
  }

  public void setResults(List<Movie> movies) {
    this.movies = movies;
  }

  public Integer getTotalResults() {
    return totalResults;
  }

  public void setTotalResults(Integer totalResults) {
    this.totalResults = totalResults;
  }

  public Integer getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }
}