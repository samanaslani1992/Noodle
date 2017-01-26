package com.noodle.storage;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 *
 */

public class Record {

  byte[] key;
  byte[] data;

  /**
   * Empty convenience constructor.
   */
  public Record() {
    this(new byte[]{}, new byte[]{});
  }

  public Record(final byte[] key, final byte[] data) {
    this.key = key;
    this.data = data;
  }

  public int size() {
    return 8 + key.length + data.length;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Record record = (Record) o;

    return Arrays.equals(key, record.key) && Arrays.equals(data, record.data);

  }

  @Override
  public int hashCode() {
    int result = Arrays.hashCode(key);
    result = 31 * result + Arrays.hashCode(data);
    return result;
  }

  public ByteBuffer asByteBuffer() {
    final ByteBuffer buffer = ByteBuffer.allocate(size());
    buffer.putInt(key.length)
        .putInt(data.length)
        .put(key)
        .put(data);

    buffer.position(0);

    return buffer;
  }
}
