package com.gmail.kouh2501gis.BytecodeViewer

import com.gmail.kouh2501gis.BytecodeViewer.constant._
import com.gmail.kouh2501gis.BytecodeViewer.util._

import scala.collection.mutable.ListBuffer

/**
 * Created with IntelliJ IDEA.
 * User: kouhama
 * Date: 13/05/19
 * Time: 0:00
 * To change this template use File | Settings | File Templates.
 */
class ClassFile {

  /**
   * class file データを各メンバーに設定する
   * @param bytes
   */
  def setData(bytes : Array[Byte]) : Unit = {

    //magic
    magic_(0) = bytes(0)
    magic_(1) = bytes(1)
    magic_(2) = bytes(2)
    magic_(3) = bytes(3)

    minorVersion_ = bytes(4) << 8 | bytes(5)
    majorVersion_ = bytes(6) << 8 | bytes(7)

    constantPoolCount_ = bytes(8) << 8 | bytes(9)
    //0オリジンだが#0はない為、−１する
    constantPoolCount_ = constantPoolCount_ - 1

    var count = 1
    var currentPosition = 10
    while(count <= constantPoolCount) {
      bytes(currentPosition) match {
        case CpInfo.kCONSTANT_Class => {
          constantPool_ += ClassInfo(count, bytes(currentPosition + 1) << 8 | bytes(currentPosition + 2))
          currentPosition += ClassInfo.kDateLength
          count += 1
        }
        case CpInfo.kCONSTANT_Double => {
          currentPosition += DoubleInfo.kDataLength
          count += 1
        }
        case CpInfo.kCONSTANT_Long => {
          currentPosition += LongInfo.kDataLength
          count += 1
        }
        case CpInfo.kCONSTANT_Fieldref => {
          constantPool_ += FieldrefInfo(
            count,
            bytes(currentPosition + 1) << 8 | bytes(currentPosition + 2),
            bytes(currentPosition + 3) << 8 | bytes(currentPosition + 4)
          )
          currentPosition += FieldrefInfo.kDataLength
          count += 1
        }
        case CpInfo.kCONSTANT_Float => {
          constantPool_ += FloatInfo(count, Util.convertToFloatFromByteArray(bytes, currentPosition + 1))
          currentPosition += FloatInfo.kDataLength
          count += 1
        }
        case CpInfo.kCONSTANT_Integer => {
          constantPool_ += IntegerInfo(count, Util.convertToIntegerFromByteArray(bytes, currentPosition + 1))
          currentPosition += IntegerInfo.kDataLength
          count += 1
        }
        case CpInfo.kCONSTANT_InterfaceMethodref => {
          constantPool_ += InterfaceMethodrefInfo(
            count,
            bytes(currentPosition + 1) << 8 | bytes(currentPosition + 2),
            bytes(currentPosition + 3) << 8 | bytes(currentPosition + 4)
          )
          currentPosition += InterfaceMethodrefInfo.kDataLength
          count += 1
        }
        case CpInfo.kCONSTANT_Methodref => {
          constantPool_ += MethodrefInfo(
            count,
            bytes(currentPosition + 1) << 8 | bytes(currentPosition + 2),
            bytes(currentPosition + 3) << 8 | bytes(currentPosition + 4)
          )
          currentPosition += MethodrefInfo.kDataLength
          count += 1
        }
        case CpInfo.kCONSTANT_NameAndType => {
          constantPool_ += NameAndTypeInfo(
            count,
            bytes(currentPosition + 1) << 8 | bytes(currentPosition + 2),
            bytes(currentPosition + 3) << 8 | bytes(currentPosition + 4)
          )
          currentPosition += NameAndTypeInfo.kDataLength
          count += 1
        }
        case CpInfo.kCONSTANT_String => {
          constantPool_ += StringInfo(count, bytes(currentPosition + 1) << 8 | bytes(currentPosition + 2))
          currentPosition += StringInfo.kDataLength
          count += 1
        }

        case CpInfo.kCONSTANT_Utf8 => {
          val length = bytes(currentPosition + 1) << 8 | bytes(currentPosition + 2)
          var tmp = new Array[Byte](length)
          System.arraycopy(bytes, currentPosition + CpInfo.kTagLength + Utf8Info.kLengthLength, tmp, 0, length)

          val utf8 = Utf8Info(count, length, new String(tmp, "UTF-8"))
          constantPool_ += utf8
          //print(" value=" + utf8.value)
          currentPosition += utf8.kDataLength
          count += 1

        }

        case _ => {
          constantPool_ += new Undefined(count)
          count += 1
        }
      }
    }

    accessFlag_.setFlag(bytes, currentPosition)
    currentPosition += 2

    thisClass_ = bytes(currentPosition) << 8 | bytes(currentPosition + 1)
    currentPosition += 2

    superClass_ = bytes(currentPosition) << 8 | bytes(currentPosition + 1)
    currentPosition += 2

    interfaceCount_ = bytes(currentPosition) << 8 | bytes(currentPosition + 1)
    currentPosition += 2

    count = 1
    if (interfaceCount > 0) {
      while(count <= interfaceCount) {
        interfaces_ += bytes(currentPosition) << 8 | bytes(currentPosition + 1)
        currentPosition += 2
        count += 1
      }
    }

    fieldsCount_ = bytes(currentPosition) << 8 | bytes(currentPosition + 1)
    currentPosition += 2

    count = 1
    if (fieldsCount_ > 0) {
      while(count <= fieldsCount_) {
        val field = new FieldInfo()
        currentPosition = field.setData(bytes, currentPosition)
        fields_ += field
        count += 1
      }
    }

    methodsCount_ = bytes(currentPosition) << 8 | bytes(currentPosition + 1)
    currentPosition += 2

    println("method count = " + methodsCount)
    count = 1
    if (methodsCount_ > 0) {
      while(count <= methodsCount_) {
        val method = new MethodInfo()
        currentPosition = method.setData(bytes, currentPosition)
        methods_ += method
        count += 1
      }
    }

    attributesCount_ = bytes(currentPosition) << 8 | bytes(currentPosition + 1)
    currentPosition += 2

    count = 1
    if (attributesCount > 0) {
      while(count <= attributesCount) {

        var attribute = new AttributeInfo()
        currentPosition = attribute.setData(bytes, currentPosition)
        attributes_ += attribute
        count += 1
      }
    }
  }

  /**
   * class fileのHexSpeakを返す
   * 通常は0xCAFEBABE
   * @return
   */
  def magic : String = {
    val result = new StringBuilder()
    for (x <- magic_) {
      result.append("%X".format(x))
    }
    return result.toString()
  }

  def minorVersion = minorVersion_
  def majorVersion = majorVersion_
  def constantPoolCount = constantPoolCount_
  def constantPool = constantPool_
  def accessFlag = accessFlag_
  def thisClass = thisClass_
  def superClass = superClass_
  def interfaceCount = interfaceCount_
  def fieldsCount = fieldsCount_
  def fields = fields_
  def methodsCount = methodsCount_
  def methods = methods_
  def attributesCount = attributesCount_
  def attributes = attributes_

  private val magic_ = new Array[Byte](4)
  private var minorVersion_ : Int = _
  private var majorVersion_ : Int = _
  private var constantPoolCount_ : Int = _
  private var constantPool_ = ListBuffer[CpInfo]()
  private var accessFlag_ = new AccessFlag()
  private var thisClass_ : Int = _
  private var superClass_ : Int = _
  private var interfaceCount_ : Int = _
  private var interfaces_ = ListBuffer[Int]()
  private var fieldsCount_ : Int = _
  private var fields_ = ListBuffer[FieldInfo]()
  private var methodsCount_ : Int = _
  private var methods_  = ListBuffer[MethodInfo]()
  private var attributesCount_ : Int = _
  private var attributes_ = ListBuffer[AttributeInfo]()
}
