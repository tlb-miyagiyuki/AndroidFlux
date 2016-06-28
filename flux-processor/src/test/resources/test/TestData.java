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

  @ObservableField(checkType = CheckType.STRICT, hasDefaultValue = true)
  private Map<String, Integer> map;

  @ObservableField(checkType = CheckType.PASS) private int i;

  @ObservableField private long l;

  @ObservableField(checkType = CheckType.PASS) private float f;

  @ObservableField private double d;

  @ObservableField private boolean b;

  @ObservableField(checkType = CheckType.PASS) private short s;

  @ObservableField private byte bb;

  @ObservableField private char c;

  @ObservableField(checkType = CheckType.PASS) private int[] ia;

  @ObservableField(checkType = CheckType.STRICT) private long[] la;

  @ObservableField(checkType = CheckType.NULLABLE) private float[] fa;

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

  public int getI() {
    return i;
  }

  public void setI(int i) {
    this.i = i;
  }

  public long getL() {
    return l;
  }

  public void setL(long l) {
    this.l = l;
  }

  public float getF() {
    return f;
  }

  public void setF(float f) {
    this.f = f;
  }

  public double getD() {
    return d;
  }

  public void setD(double d) {
    this.d = d;
  }

  public boolean isB() {
    return b;
  }

  public void setB(boolean b) {
    this.b = b;
  }

  public short getS() {
    return s;
  }

  public void setS(short s) {
    this.s = s;
  }

  public byte getBb() {
    return bb;
  }

  public void setBb(byte bb) {
    this.bb = bb;
  }

  public char getC() {
    return c;
  }

  public void setC(char c) {
    this.c = c;
  }

  public int[] getIa() {
    return ia;
  }

  public void setIa(int[] ia) {
    this.ia = ia;
  }

  public long[] getLa() {
    return la;
  }

  public void setLa(long[] la) {
    this.la = la;
  }

  public float[] getFa() {
    return fa;
  }

  public void setFa(float[] fa) {
    this.fa = fa;
  }
}