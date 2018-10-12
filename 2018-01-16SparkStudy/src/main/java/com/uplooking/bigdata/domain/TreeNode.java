package com.uplooking.bigdata.domain;

/**
 * @ Title: 2018-01-16SparkStudy
 * @ Package:com.uplooking.bigdata.domain
 * @ description: (describe the file in one sentence)
 * @ This program is protected by copyright laws
 * @ author zhangjie
 * @ date 2018/1/29
 * @ version V1.0
 */
public class TreeNode {
    public int key;
    public String data;
    public TreeNode leftChild;
    public TreeNode rightChild;
    public boolean isVisted=false;

    public TreeNode() {
    }
    public TreeNode(int key, String data) {
        this.key = key;
        this.data = data;
    }

    public TreeNode(int key, String data, TreeNode leftChild, TreeNode rightChild, boolean isVisted) {
        this.key = key;
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.isVisted = isVisted;
    }
}
