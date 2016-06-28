package jp.bglb.bonboru.flux.dto;

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

  public TestDataBuilder setI(int i) {
    this.data.setI(i);
    return this;
  }

  public TestDataBuilder setL(long l) {
    this.data.setL(l);
    return this;
  }

  public TestDataBuilder setF(float f) {
    this.data.setF(f);
    return this;
  }

  public TestDataBuilder setD(double d) {
    this.data.setD(d);
    return this;
  }

  public TestDataBuilder setB(boolean b) {
    this.data.setB(b);
    return this;
  }

  public TestDataBuilder setS(short s) {
    this.data.setS(s);
    return this;
  }

  public TestDataBuilder setBb(byte bb) {
    this.data.setBb(bb);
    return this;
  }

  public TestDataBuilder setC(char c) {
    this.data.setC(c);
    return this;
  }

  public TestDataBuilder setIa(int[] ia) {
    this.data.setIa(ia);
    return this;
  }

  public TestDataBuilder setLa(long[] la) {
    this.data.setLa(la);
    return this;
  }

  public TestDataBuilder setFa(float[] fa) {
    this.data.setFa(fa);
    return this;
  }

}
