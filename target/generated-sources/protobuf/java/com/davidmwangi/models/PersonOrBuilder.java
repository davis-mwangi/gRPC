// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: person.proto

package com.davidmwangi.models;

public interface PersonOrBuilder extends
    // @@protoc_insertion_point(interface_extends:Person)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *Use 1-&gt;15 for frequently used fields (1 byte)
   *Use 16 -&gt;2047  for fields not frequently used (2 bytes)
   *1=David
   *2=24
   * </pre>
   *
   * <code>string name = 1;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <pre>
   *Use 1-&gt;15 for frequently used fields (1 byte)
   *Use 16 -&gt;2047  for fields not frequently used (2 bytes)
   *1=David
   *2=24
   * </pre>
   *
   * <code>string name = 1;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>.google.protobuf.Int32Value age = 2;</code>
   * @return Whether the age field is set.
   */
  boolean hasAge();
  /**
   * <code>.google.protobuf.Int32Value age = 2;</code>
   * @return The age.
   */
  com.google.protobuf.Int32Value getAge();
  /**
   * <code>.google.protobuf.Int32Value age = 2;</code>
   */
  com.google.protobuf.Int32ValueOrBuilder getAgeOrBuilder();

  /**
   * <code>.common.Address address = 3;</code>
   * @return Whether the address field is set.
   */
  boolean hasAddress();
  /**
   * <code>.common.Address address = 3;</code>
   * @return The address.
   */
  com.davidmwangi.models.Address getAddress();
  /**
   * <code>.common.Address address = 3;</code>
   */
  com.davidmwangi.models.AddressOrBuilder getAddressOrBuilder();

  /**
   * <code>repeated .common.Car car = 4;</code>
   */
  java.util.List<com.davidmwangi.models.Car> 
      getCarList();
  /**
   * <code>repeated .common.Car car = 4;</code>
   */
  com.davidmwangi.models.Car getCar(int index);
  /**
   * <code>repeated .common.Car car = 4;</code>
   */
  int getCarCount();
  /**
   * <code>repeated .common.Car car = 4;</code>
   */
  java.util.List<? extends com.davidmwangi.models.CarOrBuilder> 
      getCarOrBuilderList();
  /**
   * <code>repeated .common.Car car = 4;</code>
   */
  com.davidmwangi.models.CarOrBuilder getCarOrBuilder(
      int index);
}
