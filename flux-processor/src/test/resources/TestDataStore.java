package jp.bglb.bonboru.flux.dto;

import java.lang.Boolean;
import java.lang.Byte;
import java.lang.Character;
import java.lang.Double;
import java.lang.Float;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Override;
import java.lang.Short;
import java.lang.String;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import jp.bglb.bonboru.flux.store.Store;
import rx.subjects.BehaviorSubject;

public final class TestDataStore extends Store<TestData> {
  public final BehaviorSubject<String> nonNullName;

  public final BehaviorSubject<String> name;

  public final BehaviorSubject<String> passName;

  public final BehaviorSubject<String> defaultName;

  public final BehaviorSubject<List<String>> messages;

  public final BehaviorSubject<Map<String, Integer>> map;

  public final BehaviorSubject<Integer> i;

  public final BehaviorSubject<Long> l;

  public final BehaviorSubject<Float> f;

  public final BehaviorSubject<Double> d;

  public final BehaviorSubject<Boolean> b;

  public final BehaviorSubject<Short> s;

  public final BehaviorSubject<Byte> bb;

  public final BehaviorSubject<Character> c;

  public final BehaviorSubject<int[]> ia;

  public final BehaviorSubject<long[]> la;

  public final BehaviorSubject<float[]> fa;

  public TestDataStore() {
    this.data = new TestData();
    this.nonNullName = BehaviorSubject.create();
    this.name = BehaviorSubject.create();
    this.passName = BehaviorSubject.create();
    this.defaultName = BehaviorSubject.create(this.data.getDefaultName());
    this.messages = BehaviorSubject.create();
    this.map = BehaviorSubject.create(this.data.getMap());
    this.i = BehaviorSubject.create();
    this.l = BehaviorSubject.create();
    this.f = BehaviorSubject.create();
    this.d = BehaviorSubject.create();
    this.b = BehaviorSubject.create();
    this.s = BehaviorSubject.create();
    this.bb = BehaviorSubject.create();
    this.c = BehaviorSubject.create();
    this.ia = BehaviorSubject.create();
    this.la = BehaviorSubject.create();
    this.fa = BehaviorSubject.create();
  }

  @Override
  public void setData(TestData data) {
    if (data.getNonNullName() != null && (this.data.getNonNullName() == null || !this.data.getNonNullName().equals(data.getNonNullName()))) {
      this.data.setNonNullName(data.getNonNullName());
      this.nonNullName.onNext(data.getNonNullName());
    }
    if ((this.data.getName() == null || !this.data.getName().equals(data.getName()))) {
      this.data.setName(data.getName());
      this.name.onNext(data.getName());
    }
    if (1 == 1) {
      this.data.setPassName(data.getPassName());
      this.passName.onNext(data.getPassName());
    }
    if (1 == 1) {
      this.data.setDefaultName(data.getDefaultName());
      this.defaultName.onNext(data.getDefaultName());
    }
    if (1 == 1) {
      this.data.setMessages(data.getMessages());
      this.messages.onNext(data.getMessages());
    }
    if (data.getMap() != null && (this.data.getMap() == null || !this.data.getMap().equals(data.getMap()))) {
      this.data.setMap(data.getMap());
      this.map.onNext(data.getMap());
    }
    if (1 == 1) {
      this.data.setI(data.getI());
      this.i.onNext(data.getI());
    }
    if (this.data.getL() != data.getL()) {
      this.data.setL(data.getL());
      this.l.onNext(data.getL());
    }
    if (1 == 1) {
      this.data.setF(data.getF());
      this.f.onNext(data.getF());
    }
    if (this.data.getD() != data.getD()) {
      this.data.setD(data.getD());
      this.d.onNext(data.getD());
    }
    if (this.data.isB() != data.isB()) {
      this.data.setB(data.isB());
      this.b.onNext(data.isB());
    }
    if (1 == 1) {
      this.data.setS(data.getS());
      this.s.onNext(data.getS());
    }
    if (this.data.getBb() != data.getBb()) {
      this.data.setBb(data.getBb());
      this.bb.onNext(data.getBb());
    }
    if (this.data.getC() != data.getC()) {
      this.data.setC(data.getC());
      this.c.onNext(data.getC());
    }
    if (1 == 1) {
      this.data.setIa(data.getIa());
      this.ia.onNext(data.getIa());
    }
    if (data.getLa() != null && (this.data.getLa() == null || !Arrays.equals(this.data.getLa(), data.getLa()))) {
      this.data.setLa(data.getLa());
      this.la.onNext(data.getLa());
    }
    if ((this.data.getFa() == null || !Arrays.equals(this.data.getFa(), data.getFa()))) {
      this.data.setFa(data.getFa());
      this.fa.onNext(data.getFa());
    }
  }

  @Override
  public TestData copyCurrentState() {
    TestData newObject = new TestData();
    newObject.setNonNullName(this.data.getNonNullName());
    newObject.setName(this.data.getName());
    newObject.setPassName(this.data.getPassName());
    newObject.setDefaultName(this.data.getDefaultName());
    newObject.setMessages(this.data.getMessages());
    newObject.setMap(this.data.getMap());
    newObject.setI(this.data.getI());
    newObject.setL(this.data.getL());
    newObject.setF(this.data.getF());
    newObject.setD(this.data.getD());
    newObject.setB(this.data.isB());
    newObject.setS(this.data.getS());
    newObject.setBb(this.data.getBb());
    newObject.setC(this.data.getC());
    newObject.setIa(this.data.getIa());
    newObject.setLa(this.data.getLa());
    newObject.setFa(this.data.getFa());
    return newObject;
  }
}
