package jp.bglb.bonboru.flux.dto;

import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
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

  public TestDataStore() {
    this.data = new TestData();
    this.nonNullName = BehaviorSubject.create();
    this.name = BehaviorSubject.create();
    this.passName = BehaviorSubject.create();
    this.defaultName = BehaviorSubject.create(this.data.getDefaultName());
    this.messages = BehaviorSubject.create();
    this.map = BehaviorSubject.create(this.data.getMap());
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
  }
}
