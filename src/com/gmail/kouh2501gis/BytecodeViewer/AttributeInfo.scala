package com.gmail.kouh2501gis.BytecodeViewer

import scala.collection.mutable._
import com.gmail.kouh2501gis.BytecodeViewer.util._

/**
 * Created with IntelliJ IDEA.
 * User: kouhama
 * Date: 13/05/19
 * Time: 13:40
 * To change this template use File | Settings | File Templates.
 */
class AttributeInfo {

  /**
   * 各メンバーに値を設定する
   * @param bytes
   * @param offset
   * @return
   */
  def setData(bytes : Array[Byte], offset : Int) : Int = {
    var currentPosition = offset

    attributeNameIndex_ = bytes(currentPosition) << 8 | bytes(currentPosition + 1)
    currentPosition += 2

    attributeLength_ = Util.convertToIntegerFromByteArray(bytes, currentPosition)
    currentPosition += 4

    var count = 1
    if (attributeLength_ > 0) {
      while(count <= attributeLength_) {

        info_ += bytes(currentPosition)
        currentPosition += 1
        count += 1
      }
    }

    return currentPosition
  }

  def attributeNameIndex = attributeNameIndex_
  def attributeLength = attributeLength_
  def info = info_

  private var attributeNameIndex_ : Int = _
  private var attributeLength_ : Int = _
  private var info_ = ListBuffer[Byte]()
}

/*
attribute_info {
u2 attribute_name_index;
u4 attribute_length;
u1 info[attribute_length];
}
*/