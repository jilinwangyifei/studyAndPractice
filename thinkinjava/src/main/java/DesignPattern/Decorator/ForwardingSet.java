package DesignPattern.Decorator;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by wangbo on 2018/9/1.
 */

/**
 * 装饰器模式
 * @param <E>
 */
public class ForwardingSet<E> extends HashSet<E> {

    private final Set<E> s;

    ForwardingSet(Set<E> set) {
        this.s = set;
    }

    public void clear()               { s.clear();            }
    public boolean contains(Object o) { return s.contains(o); }
    public boolean isEmpty()          { return s.isEmpty();   }
    public int size()                 { return s.size();      }
    public Iterator<E> iterator()     { return s.iterator();  }
    public boolean add(E e)           { return s.add(e);      }
    public boolean remove(Object o)   { return s.remove(o);   }
    public boolean containsAll(Collection<?> c)
    { return s.containsAll(c); }
    public boolean addAll(Collection<? extends E> c)
    { return s.addAll(c);      }
    public boolean removeAll(Collection<?> c)
    { return s.removeAll(c);   }
    public boolean retainAll(Collection<?> c)
    { return s.retainAll(c);   }
    public Object[] toArray()          { return s.toArray();  }
    public <T> T[] toArray(T[] a)      { return s.toArray(a); }
    @Override public boolean equals(Object o)
    { return s.equals(o);  }
    @Override public int hashCode()    { return s.hashCode(); }
    @Override public String toString() { return s.toString(); }

}
