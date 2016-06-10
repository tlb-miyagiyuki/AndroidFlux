package jp.bglb.bonboru.flux.example.action.data;

/**
 * Created by tmasuda on 2016/04/24.
 */
public class MainActionResult {
  public final String text;

  public final String message;

  public MainActionResult(String text) {
    this.text = text;
    this.message = null;
  }

  public MainActionResult(String text, String message) {
    this.text = text;
    this.message = message;
  }

  public static class Builder {
    String text;

    String message;

    public Builder setText(String text) {
      return this;
    }
  }
}
