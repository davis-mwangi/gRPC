// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: person.proto

package com.davidmwangi.models;

public interface AddressOrBuilder extends
    // @@protoc_insertion_point(interface_extends:Address)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 postbox = 1;</code>
   * @return The postbox.
   */
  int getPostbox();

  /**
   * <code>string street = 2;</code>
   * @return The street.
   */
  java.lang.String getStreet();
  /**
   * <code>string street = 2;</code>
   * @return The bytes for street.
   */
  com.google.protobuf.ByteString
      getStreetBytes();

  /**
   * <code>string city = 3;</code>
   * @return The city.
   */
  java.lang.String getCity();
  /**
   * <code>string city = 3;</code>
   * @return The bytes for city.
   */
  com.google.protobuf.ByteString
      getCityBytes();
}