package com.gmail.kouh2501gis.BytecodeViewer

/**
 * Created with IntelliJ IDEA.
 * User: kouhama
 * Date: 13/05/19
 * Time: 12:55
 * To change this template use File | Settings | File Templates.
 */
class AccessFlag {

  def isPublic = (flag & AccessFlag.kACC_PUBLIC) != 0
  def isFinal = (flag & AccessFlag.kACC_FINAL) != 0
  def isSuper = (flag & AccessFlag.kACC_SUPER) != 0
  def isInterface = (flag & AccessFlag.kACC_INTERFACE) != 0
  def isAcstract = (flag & AccessFlag.kACC_ABSTRACT) != 0

  override def toString : String =
  {
    val ret = new StringBuilder()

    ret.append(if (isPublic){"Public,"} else {""})
    ret.append(if (isFinal){"Final,"} else {""})
    ret.append(if (isSuper){"Super,"} else {""})
    ret.append(if (isInterface){"Interface,"} else {""})
    ret.append(if (isAcstract){"Abstract"} else {""})

    return ret.toString()
  }
  def flag = flag_
  def setFlag(data : Array[Byte], offset : Int) {
    flag_ = data(offset) << 8 | data(offset + 1)
  }
  var flag_ : Int = _
}

object AccessFlag {
  val kACC_PUBLIC = 0x001
  val kACC_FINAL = 0x0010
  val kACC_SUPER = 0x0020
  val kACC_INTERFACE = 0x0200
  val kACC_ABSTRACT = 0x0400
}
