package com.uplooking.bigdata;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Shop implements Writable,DBWritable {
    private  String type;//商品名称
    private Integer count;//商品数量
     //序列化  把数据序列化为二进制的字节流
    public void write(DataOutput out) throws IOException {
          out.writeUTF(type);
          out.writeInt(count);

    }
      //反序列化   把二进制的字节流反序列化为普通数据
    public void readFields(DataInput in) throws IOException {
        this.type=in.readUTF();
        this.count=in.readInt();

    }
      //写入数据库
    public void write(PreparedStatement statement) throws SQLException {
        statement.setString(1,this.type);
        statement.setInt(2,this.count);

    }

    public void readFields(ResultSet resultSet) throws SQLException {

    }

    public Shop() {
    }

    public Shop(String type, Integer count) {
        this.type = type;
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
