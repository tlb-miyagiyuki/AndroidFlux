import jp.bglb.bonboru.flux.dto;

import java.lang.Integer;
import java.lang.String;
import java.util.List;
import java.util.Map;

public final class TestDataBuilder {
  private TestData data;

  public TestDataBuilder() {
    this.data = new TestData();
  }

  public TestData build() {
    return this.data;
  }

  public TestDataBuilder setNonNullName(String nonNullName) {
    this.data.setNonNullName(nonNullName);
    return this;
  }

  public TestDataBuilder setName(String name) {
    this.data.setName(name);
    return this;
  }

  public TestDataBuilder setPassName(String passName) {
    this.data.setPassName(passName);
    return this;
  }

  public TestDataBuilder setDefaultName(String defaultName) {
    this.data.setDefaultName(defaultName);
    return this;
  }

  public TestDataBuilder setMessages(List<String> messages) {
    this.data.setMessages(messages);
    return this;
  }

  public TestDataBuilder setMap(Map<String, Integer> map) {
    this.data.setMap(map);
    return this;
  }
}
