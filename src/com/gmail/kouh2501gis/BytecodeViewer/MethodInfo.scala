package com.gmail.kouh2501gis.BytecodeViewer

import scala.collection.mutable._

/**
 * Created with IntelliJ IDEA.
 * User: kouhama
 * Date: 13/05/19
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
class MethodInfo {

  /**
   * 各メンバーに値を設定する
   * @param bytes
   * @param offset
   * @return
   */
  def setData(bytes : Array[Byte], offset : Int) : Int = {
    var currentPosition = offset

    println("bytes length = " + bytes.length + ", currentPosition = " + currentPosition)

    accessFlag_ = bytes(currentPosition) << 8 | bytes(currentPosition + 1)
    currentPosition += 2

    nameIndex_ = bytes(currentPosition) << 8 | bytes(currentPosition + 1)
    currentPosition += 2

    descriptorIndex_ = bytes(currentPosition) << 8 | bytes(currentPosition + 1)
    currentPosition += 2

    attributesCount_ = bytes(currentPosition) << 8 | bytes(currentPosition + 1)
    currentPosition += 2

    println("attribute count = " + attributesCount)
    var count = 0
    if (attributesCount > 0) {
      while(count < attributesCount) {

        var attribute = new AttributeInfo()
        currentPosition = attribute.setData(bytes, currentPosition)
        attributes_ += attribute
        count += 1
      }
    }

    return currentPosition
  }

  def accessFlag = accessFlag_
  def nameIndex = nameIndex_
  def descriptorIndex  = descriptorIndex_
  def attributesCount = attributesCount_
  def attributes = attributes_

  private var accessFlag_ : Int = _
  private var nameIndex_ : Int = _
  private var descriptorIndex_ : Int = _
  private var attributesCount_ : Int = _
  private var attributes_ = ListBuffer[AttributeInfo]()
}

object MethodInfo {
  val kACC_PUBLIC = 0x0001
  val kACC_PRIVATE = 0x0002
  val kACC_PROTECTED = 0x0004
  val kACC_STATIC = 0x0008
  val kACC_FINAL = 0x0010
  val kACC_SYNCHRONIZED = 0x0020
  val kACC_NATIVE = 0x0100
  val kACC_ABSTRACT = 0x0400
  val kACC_STRICT = 0x0800
}

/*
Format
method_info {
u2 access_flags;
u2 name_index;
u2 descriptor_index;
u2 attributes_count;
attribute_info attributes[attributes_count];
}
*/