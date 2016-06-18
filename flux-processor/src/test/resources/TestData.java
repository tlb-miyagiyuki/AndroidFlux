package jp.bglb.bonboru.flux.dto;

import java.util.List;
import java.util.Map;
import jp.bglb.bonboru.flux.compiler.annotation.ObservableClass;
import jp.bglb.bonboru.flux.compiler.annotation.ObservableField;
import jp.bglb.bonboru.flux.compiler.type.CheckType;

@ObservableClass public class TestData {
  @ObservableField private String nonNullName;

  @ObservableField(checkType = CheckType.NULLABLE) private String name;

  @ObservableField(checkType = CheckType.PASS) private String passName;

  @ObservableField(checkType = CheckType.PASS, hasDefaultValue = true) private String defaultName;

  @ObservableField(checkType = CheckType.PASS) private List<String> messages;

  @ObservableField(checkType = CheckType.STRICT, hasDefaultValue = true) private Map<String, Integer> map;

  public String getNonNullName() {
    return nonNullName;
  }

  public void setNonNullName(String nonNullName) {
    this.nonNullName = nonNullName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassName() {
    return passName;
  }

  public void setPassName(String passName) {
    this.passName = passName;
  }

  public String getDefaultName() {
    return defaultName;
  }

  public void setDefaultName(String defaultName) {
    this.defaultName = defaultName;
  }

  public List<String> getMessages() {
    return messages;
  }

  public void setMessages(List<String> messages) {
    this.messages = messages;
  }

  public Map<String, Integer> getMap() {
    return map;
  }

  public void setMap(Map<String, Integer> map) {
    this.map = map;
  }
}