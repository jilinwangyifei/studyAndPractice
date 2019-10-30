package DataStructureStudy.Practice;

import java.util.Map;

public class Day4_HashTable_String {

    /**
     * 散列表 and 字符串
     *
     * 实现一个基于链表法解决冲突问题的散列表
     *
     * 实现一个 LRU 缓存淘汰算法
     *
     *
     * 实现一个字符集，只包含 a～z 这 26 个英文字母的 Trie树
     *
     * 实现朴素的字符串匹配算法
     *
     * 一、单模式串匹配：
     * 1. BF： 简单场景，主串和模式串都不太长, O(m*n)
     * 2. RK：字符集范围不要太大且模式串不要太长， 否则hash值可能冲突，O(n)
     * 3. naive-BM：模式串最好不要太长（因为预处理较重），比如IDE编辑器里的查找场景；
     *              预处理O(m*m), 匹配O(n)， 实现较复杂，需要较多额外空间.
     * 4. KMP：适合所有场景，整体实现起来也比BM简单，O(n+m)，仅需一个next数组的O(n)额外空间；但统计意义下似乎BM更快，原因不明.
     * 5. 另外查资料的时候还看到一种比BM/KMP更快，且实现+理解起来都更容易的的Sunday算法，有兴趣的可以看这里:
     *    http://www.inf.fh-flensburg.de/lang/algorithmen/pattern/sundayen.htm
     *    https://www.jianshu.com/p/2e6eb7386cd3
     *
     * 二、多模式串匹配：
     * 1. naive-Trie: 适合多模式串公共前缀较多的匹配(O(n*k)) 或者 根据公共前缀进行查找(O(k))的场景，比如搜索框的自动补全提示.
     * 2. AC自动机: 适合大量文本中多模式串的精确匹配查找, 可以到O(n).
     *
     *
     * */

     //实现一个基于链表法解决冲突问题的散列表
    class HashMap<K,V>{
        Node<K,V> [] table;
        int length;

        void put(K key,V value){
            if ( table[hash(key) & length] == null ) {
                table[hash(key) & length] = new Node<>(hash(key),key,value,null);
            } else {
                for (Node e = table[hash(key) & length]; e.next != null ;e = e.next) {
                    if (e.hash == hash(key) && (e.key == key || e.key.equals(key))){
                        e.value = value;
                    }
                }
                addEntry(hash(key), key, value, hash(key) & length);
            }
        }

        void addEntry(int hash,K key,V value, int index) {
            Node node = table[index];
            table[index] = new Node<>(hash, key, value, node);
        }

        public V get(K key) {
            if ( table[hash(key) & length] != null ) {
                for (Node e = table[hash(key) & length]; e.next != null ;e = e.next) {
                    if (e.hash == hash(key) && (e.key == key || e.key.equals(key))){
                        //return e.getValue();
                    }
                }
            }
            return null;
        }

        int hash(K key) {
            return 0;
        }

        class Node<K,V>{
            final int hash;
            final K key;
            V value;
            Node<K,V> next;
            Node(int hash,K key,V value, Node<K,V> next){
                this.hash = hash;
                this.key = key;
                this.value = value;
                this.next = next;
            }

            public final V getValue() {
                return value;
            }

        }
    }

    //实现一个 LRU 缓存淘汰算法  力扣146题
    static class LRUCache {
        Node head,tail;
        int capacity;
        java.util.HashMap<Integer, Node> map;
        int size;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new java.util.HashMap<Integer, Node>();
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.after = tail;
            tail.before = head;
        }

        public int get(int key) {
            Node node;
            if ((node = map.get(key)) != null) {
                if (node.after != tail) {

                    node.before.after = node.after;
                    node.after.before = node.before;

                    node.after = tail;
                    node.before = tail.before;
                    tail.before.after = node;
                    tail.before = node;
                }
            }

            return -1;
        }

        public void put(int key, int value) {
            if (get(key) != -1) {
                tail.before.value = value;
                return;
            }
            if (size < capacity) {
                size ++;
            } else {
                Node node = head.after;
                map.remove(node.key);
                head.after = node.after;
                node.after.before = head;
            }
            Node node = new Node(key, value);
            map.put(key, node);
            node.after = tail;
            node.before = tail.before;
            tail.before.after = node;
            tail.before = node;
        }

        class Node {
            Node before;
            Node after;
            int key,value;
            Node(int key,int value) {
                this.key = key;
                this.value = value;
            }
        }

    }


    //实现一个字符集，只包含 a～z 这 26 个英文字母的 Trie树
    class Trie {
        TrieNode root = new TrieNode('/');

        void addNode(char[] text) {
            TrieNode p = root;
            for (int i = 0; i < text.length ; i++) {
                int pos = text[i] - 'a';
                if (p.children[pos] == null) {
                    TrieNode trieNode = new TrieNode(text[i]);
                    p.children[pos] = trieNode;
                }
                p = p.children[pos];
            }
            p.isEndingChar = true;
        }

        boolean find(char[] text) {
            TrieNode p = root;
            for (int i = 0; i < text.length ; i++) {
                int pos = text[i] - 'a';
                if (p.children[pos] == null && p.children[pos].data == text[i]) return false;
                p = p.children[pos];
            }
            return true;
        }


        class TrieNode {
            char data;
            TrieNode [] children;
            public boolean isEndingChar;
            TrieNode(char data) {
                this.data = data;
            }
        }
    }

    /**
     * 朴素字符串匹配思想  暴力比对  摘自string
     *
     */
    class BF {
        int indexOf(char[] source,int sourceOffet, int sourceCount,
                    char[] target, int targetOffet, int targetCount, int fromIndex) {

            if (fromIndex >= sourceCount) {
                return (targetCount == 0 ? sourceCount :0);
            }

            if (fromIndex < 0) {
                fromIndex = 0;
            }

            if (targetCount == 0) {
                return fromIndex;
            }

            char first = target[targetOffet];
            int max = sourceOffet + (sourceCount - targetCount);
            for (int i = sourceOffet+fromIndex; i <= max; i++) {
                if (source[i] != first) {
                    while (++i < max && source[i] != first);
                }

                if (i <= max) {
                    int j = i+1;
                    int end = j + targetCount - 1;
                    for (int k = targetOffet + 1; j < end && source[j] == target[k]; j++,k++);

                    if (j == end) {
                        return  i - sourceOffet;
                    }
                }

            }

            return -1;
        }
    }

}
