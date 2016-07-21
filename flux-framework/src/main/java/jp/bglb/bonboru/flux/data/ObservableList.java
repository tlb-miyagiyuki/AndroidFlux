package jp.bglb.bonboru.flux.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by tmasuda on 2016/06/16.
 */
public class ObservableList<E> {

  private List<E> list;

  // Listに対して行われた操作
  private Operation operation = Operation.INVALID;

  // 変更がある開始位置
  private long fromOperatedPosition = 0;

  // 変更がある終了位置
  private long toOperatedPosition = 0;

  public ObservableList() {
    this.list = new ArrayList<>();
  }

  public ObservableList(List<E> list) {
    this.list = new ArrayList<>();
    this.list.addAll(list);
  }

  public void swap(int p1, int p2) {
    operation = Operation.SWAP;
    fromOperatedPosition = p1;
    toOperatedPosition = p2;
    E o1 = list.get(p1);
    E o2 = list.get(p2);
    list.remove(o1);
    list.remove(o2);
    list.add(p1, o2);
    list.add(p2, o1);
  }

  public void replace(int p, E o) {
    operation = Operation.UPDATE;
    fromOperatedPosition = p;
    toOperatedPosition = p;
    E r = list.get(p);
    list.remove(r);
    list.add(p, o);
  }

  public boolean add(E e) {
    operation = Operation.INSERT;
    fromOperatedPosition = list.size();
    toOperatedPosition = list.size();
    return list.add(e);
  }

  public void add(int index, E element) {
    operation = Operation.INSERT;
    fromOperatedPosition = index;
    toOperatedPosition = index;
    list.add(index, element);
  }

  public boolean addAll(Collection<? extends E> c) {
    operation = Operation.INSERT;
    fromOperatedPosition = list.size();
    toOperatedPosition = list.size() - 1 + c.size();
    return list.addAll(c);
  }

  public boolean remove(E o) {
    operation = Operation.DELETE;
    fromOperatedPosition = list.indexOf(o);
    toOperatedPosition = fromOperatedPosition;
    return list.remove(o);
  }

  public E remove(int index) {
    operation = Operation.DELETE;
    fromOperatedPosition = index;
    toOperatedPosition = fromOperatedPosition;
    return list.remove(index);
  }

  public Operation getOperation() {
    return operation;
  }

  public long getFromOperatedPosition() {
    return fromOperatedPosition;
  }

  public long getToOperatedPosition() {
    return toOperatedPosition;
  }

  public List<E> getList() {
    return list;
  }

  @Override public boolean equals(Object o) {
    boolean sameClass = getClass() == o.getClass();
    boolean sameOperated = operation == ObservableList.class.cast(o).operation;
    boolean sameSize = list.size() == ObservableList.class.cast(o).list.size();
    return sameClass && sameOperated && sameSize;
  }
}
