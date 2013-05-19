package com.gmail.kouh2501gis.BytecodeViewer.util

/**
 * Created with IntelliJ IDEA.
 * User: kouhama
 * Date: 13/05/19
 * Time: 9:16
 * To change this template use File | Settings | File Templates.
 */
object Util {

  /**
   * int→byte配列
   * @param data
   * @param offset
   * @param value
   */
  def convertToByteArrayFromInteger(data : Array[Byte], offset : Int, value : Int) : Unit =
  {
    val size = Integer.SIZE / java.lang.Byte.SIZE

    //int size = Integer.SIZE / Byte.SIZE;
    if (data == null || data.length < size || offset < 0 || data.length - size < offset) {
      throw new IllegalArgumentException("Bat Param")
    } else {
      //for (int i = 0; i < size; i++) {
      for(i <- 0 until size) {
        //data[offset + i] = Integer.valueOf(value >> (Byte.SIZE * (size - 1 - i))).byteValue();
        data(offset + i) = Integer.valueOf(value >> (java.lang.Byte.SIZE * (size - 1 - i))).byteValue()
      }
    }
  }

  /**
   * byte配列　→　int
   * @param data
   * @param offset
   * @return
   */
  def convertToIntegerFromByteArray(data : Array[Byte], offset : Int) : Int =
  {
    //int result = 0;
    var result = 0

    //int size = Integer.SIZE / Byte.SIZE;
    val size = Integer.SIZE / java.lang.Byte.SIZE;

    if (data == null || data.length < size || offset < 0 || data.length - size < offset) {
      throw new IllegalArgumentException("Bat Param");
    } else {
      //for (int i = 0; i < size; i++) {
      for (i <- 0 until size) {
        //result |= Integer.valueOf(data[offset + i] & 0xff).intValue() << (Byte.SIZE * (size - 1 - i));
        result |= Integer.valueOf(data(offset + i) & 0xff).intValue() << (java.lang.Byte.SIZE * (size - 1 - i))
      }
    }
    return result
  }

  /**
   * float →　byte配列
   * @param data
   * @param offset
   * @param value
   */
  def convertToByteArrayFromFloat(data : Array[Byte], offset : Int, value : Int) : Unit =
  {
    convertToByteArrayFromInteger(data, offset, java.lang.Float.floatToIntBits(value));
  }

  /**
   * byte配列　→　float
   * @param data
   * @param offset
   * @return
   */
  def convertToFloatFromByteArray(data : Array[Byte], offset : Int) : Float =
  {
    return java.lang.Float.intBitsToFloat(convertToIntegerFromByteArray(data, offset))
  }
}
