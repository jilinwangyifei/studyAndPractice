// File generated by hadoop record compiler. Do not edit.
/**
* Licensed to the Apache Software Foundation (ASF) under one
* or more contributor license agreements.  See the NOTICE file
* distributed with this work for additional information
* regarding copyright ownership.  The ASF licenses this file
* to you under the Apache License, Version 2.0 (the
* "License"); you may not use this file except in compliance
* with the License.  You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package org.apache.zookeeper.proto;

public class ErrorResponse implements org.apache.jute.Record {
  private int err;
  public ErrorResponse() {
  }
  public ErrorResponse(
        int err) {
    this.err=err;
  }
  public int getErr() {
    return err;
  }
  public void setErr(int m_) {
    err=m_;
  }
  public void serialize(org.apache.jute.OutputArchive a_, String tag) throws java.io.IOException {
    a_.startRecord(this,tag);
    a_.writeInt(err,"err");
    a_.endRecord(this,tag);
  }
  public void deserialize(org.apache.jute.InputArchive a_, String tag) throws java.io.IOException {
    a_.startRecord(tag);
    err=a_.readInt("err");
    a_.endRecord(tag);
}
  public String toString() {
    try {
      java.io.ByteArrayOutputStream s =
        new java.io.ByteArrayOutputStream();
      org.apache.jute.OutputArchive a_ =
        new org.apache.jute.CsvOutputArchive(s);
      a_.startRecord(this,"");
    a_.writeInt(err,"err");
      a_.endRecord(this,"");
      return new String(s.toByteArray(), "UTF-8");
    } catch (Throwable ex) {
      ex.printStackTrace();
    }
    return "ERROR";
  }
  public void write(java.io.DataOutput out) throws java.io.IOException {
    org.apache.jute.BinaryOutputArchive archive = new org.apache.jute.BinaryOutputArchive(out);
    serialize(archive, "");
  }
  public void readFields(java.io.DataInput in) throws java.io.IOException {
    org.apache.jute.BinaryInputArchive archive = new org.apache.jute.BinaryInputArchive(in);
    deserialize(archive, "");
  }
  public int compareTo (Object peer_) throws ClassCastException {
    if (!(peer_ instanceof ErrorResponse)) {
      throw new ClassCastException("Comparing different types of records.");
    }
    ErrorResponse peer = (ErrorResponse) peer_;
    int ret = 0;
    ret = (err == peer.err)? 0 :((err<peer.err)?-1:1);
    if (ret != 0) return ret;
     return ret;
  }
  public boolean equals(Object peer_) {
    if (!(peer_ instanceof ErrorResponse)) {
      return false;
    }
    if (peer_ == this) {
      return true;
    }
    ErrorResponse peer = (ErrorResponse) peer_;
    boolean ret = false;
    ret = (err==peer.err);
    if (!ret) return ret;
     return ret;
  }
  public int hashCode() {
    int result = 17;
    int ret;
    ret = (int)err;
    result = 37*result + ret;
    return result;
  }
  public static String signature() {
    return "LErrorResponse(i)";
  }
}
