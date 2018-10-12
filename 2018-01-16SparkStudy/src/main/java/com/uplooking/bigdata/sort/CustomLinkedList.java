package com.uplooking.bigdata.sort;

import java.util.Collection;

/**
 * @ Title: 2018-01-16SparkStudy
 * @ Package:com.uplooking.bigdata.sort
 * @ description: 自定义LinkedList
 * @ This program is protected by copyright laws
 * @ author zhangjie
 * @ date 2018/1/24
 * @ version V1.0
 */
public class CustomLinkedList <E>{
    private Node<E> head;
    private Node<E> tail;
    private int size;

    /**
     * 内部节点数据
     * @param <E>
     */

    class Node<E> {
        E data;
        Node<E> prev;
        Node<E> next;
    public Node(Node<E> prev, E data, Node<E> next) {
        this.prev = prev;
        this.data = data;
        this.next = next;
    }

    }

    public boolean addAll(Collection<? extends E> c){
        return true;
    }

    /**
     *
     * @param e
     * @return
     */
    public boolean add(E e){
        Node<E> cNode = new Node<E>(null, e, null);
        if(isEmpty()) {
            head = tail = cNode;
        } else {
            Node<E> last = tail;
//            Node<E> cNode = new Node<E>(last, e, null);
            cNode.prev = last;
            last.next = cNode;
            tail = cNode;
        }
        size++;
        return true;
    }

    /**
     *
     * @param index
     * @param element
     */
    public void add(int index, E element){
        assertIndex(index);
        Node<E> cNode = new Node<E>(null, element, null);
        if(index == 0) {//首部插入
            Node<E> first = head;
            head.prev = cNode;
            cNode.next = first;
            head = cNode;
        }else if(index == size - 1) {//尾部插入
            Node<E> last = tail;
            last.next = cNode;
            cNode.prev = last;
            tail = cNode;
        } else {//普通索引的插入

            Node<E> oldNode = node(index);
            cNode = new Node<E>(oldNode.prev, element, oldNode);
            cNode.prev.next = cNode;
            cNode.next.prev = cNode;
        }
        size++;
    }

    /**
     * 根据索引找元素
     * @param index
     * @return
     */
    private Node<E> node(int index) {
        Node<E> cNode = null;
        if(index < (size >> 1)) {
            cNode = head;
            for (int i = 0; i < index; i++) {
                cNode = cNode.next;
            }
        } else {
            cNode = tail;
            for(int i = size - 1; i > index; i--) {
                cNode = cNode.prev;
            }
        }
        return cNode;
    }

    public void addFirst(E e){
        add(0, e);
    }
    public void addLast(E e){
        add(size - 1, e);
    }

    public E get(int index){
        assertIndex(index);
        return node(index).data;
    }
    public E getFirst(){
        return head == null ? null : head.data;
    }
    public E getLast(){
        return tail == null ? null : tail.data;
    }

    /**
     * 获取该数据在链表中出现的第一次的位置
     * @param o
     * @return
     */
    public int indexOf(Object o){
        Node<E> cNode = head;
        for (int i = 0; i < size; i++) {
            if(cNode.data.equals(o)) {
                return i;
            }
            cNode = cNode.next;
        }
        return -1;
    }

    /**
     * 从尾部删除一个元素
     * @return
     */
    public E remove(){
        if(tail == null) {
            return null;
        }
        //集合不为空
        Node<E> last = tail;
        tail = last.prev;
        tail.next = null;
        size--;
        return last.data;
    }
    public E remove(int index){
        assertIndex(index);
        Node<E> cNode = node(index);
        if(index == 0) {//首部插入
            head = cNode.next;
            head.prev = null;
        }else if(index == size - 1) {//尾部插入
            tail = cNode.prev;
            tail.next = null;
        } else {
            cNode.prev.next = cNode.next;
            cNode.next.prev = cNode.prev;
        }
        size--;
        return cNode.data;
    }
    public boolean remove(Object o){
        return true;
    }
    public E removeLast(){
        return null;
    }

    public E set(int index, E element){
        assertIndex(index);
        Node<E> node = node(index);
        E oldValue = node.data;
        node.data = element;
        return oldValue;
    }

    public boolean contains(Object o){
        return indexOf(o) != -1;
    }

    public void clear(){
        head = tail = null;
        size = 0;
    }

    /**
     * 检验索引是否越界
     * @param index
     */
    private void assertIndex(int index) {
        if(index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("size: " + size + ", index: " + index);
        }
    }


    public boolean isEmpty() {
        return size == 0;
    }
    public int size(){
        return size;
    }

    @Override
    public String toString() {
        if(this.isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        Node<E> cNode = head;
        while (cNode.next != null) {
            sb.append(cNode.data).append(", ");
            cNode = cNode.next;
        }
        sb.append(cNode.data).append("]");
        return sb.toString();
    }
}
