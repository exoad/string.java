
/**
  * Copyright (c) 2022, Jack Meng
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * This class is an extension of the original string class.
 * Mostly I was planning for you to instead of having to use .charAt()
 * you can just use [] and treat it as an array.
 * 
 * @author Jack Meng
 * @version 1.0
 * @since 1.0
 */
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public final class string {

  /// STATIC CONSTANT FIELDS ///
  protected static final char[] STD_PUNCTUATION = { ',', '.', '=', '+', '-', '*', '/', '%', '^', '&', '|', '!', '~',
      '(', ')', '[', ']', '{', '}', '<', '>', '?' };
  protected static final char[] STD_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
  protected static final char[] STD_ALPHA = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
      'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
  protected static final char[] STD_ALPHA_UPPER = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
      'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

  public static final char NULL_CHAR = '\0';
  public static final char NEWLINE = '\n';

  @Target({ ElementType.METHOD })
  @Retention(RetentionPolicy.SOURCE)
  private @interface LibTestingOnly {
  }

  /**
   * Currently, I do not expect the user to input a string bigger than
   * Integer.MAX_VALUE,
   * however in future updates, I can assure a Vector of char arrays to store the
   * string.
   */
  public char[] strc;

  public string(String str) {
    this.strc = str.toCharArray();
  }

  public string(char[] str) {
    this.strc = str;
  }

  public string(byte[] str) {
    this.strc = new char[str.length];
    for (int i = 0; i < str.length; i++) {
      this.strc[i] = (char) str[i];
    }
  }

  public string(StringBuilder sb) {
    this.strc = new string(sb.toString()).strc;
  }

  public string(string str) {
    this.strc = str.strc;
  }

  public char[] reversedContent() {
    char[] reversed = new char[strc.length];
    for (int i = 0; i < strc.length; i++) {
      reversed[i] = strc[strc.length - 1 - i];
    }
    return reversed;
  }

  public void reverse() {
    strc = reversedContent();
  }

  public char[] scrambledContent() {
    char[] scrambled = new char[strc.length];
    for (int i = 0; i < strc.length; i++) {
      scrambled[i] = strc[i];
    }
    for (int i = 0; i < strc.length; i++) {
      int rand = (int) (Math.random() * strc.length);
      char temp = scrambled[i];
      scrambled[i] = scrambled[rand];
      scrambled[rand] = temp;
    }
    return scrambled;
  }

  public void scramble() {
    strc = scrambledContent();
  }

  public void reset(string str) {
    this.strc = str.strc;
  }

  public char randChar() {
    return strc[(int) (Math.random() * strc.length)];
  }

  public char getChar(int i) {
    return strc[i];
  }

  public void setCharNonRetraceable(int i, char c) {
    strc[i] = c;
  }

  public char setChar(int i, char c) {
    char old = strc[i];
    strc[i] = c;
    return old;
  }

  public char pop_back() {
    char c = strc[strc.length - 1];
    strc = Arrays.copyOf(strc, strc.length - 1);
    return c;
  }

  public int find_first_of(char c) {
    for (int i = 0; i < strc.length; i++) {
      if (strc[i] == c) {
        return i;
      }
    }
    return -1;
  }

  public int find_last_of(char c) {
    for (int i = strc.length - 1; i >= 0; i--) {
      if (strc[i] == c) {
        return i;
      }
    }
    return -1;
  }

  public void resize(int newSize) {
    strc = Arrays.copyOf(strc, newSize);
  }

  public char[] shiftedRight(int n) {
    char[] shifted = new char[strc.length];
    for (int i = 0; i < strc.length; i++) {
      shifted[i] = strc[(i + n) % strc.length];
    }
    return shifted;
  }

  public void moveRight(int n) {
    strc = shiftedRight(n);
  }

  public char[] shiftedLeft(int n) {
    char[] shifted = new char[strc.length];
    for (int i = 0; i < strc.length; i++) {
      shifted[i] = strc[(i - n + strc.length) % strc.length];
    }
    return shifted;
  }

  public void moveLeft(int n) {
    strc = shiftedLeft(n);
  }

  public char[] bitShiftedRight(int n) {
    char[] shifted = new char[strc.length];
    for (int i = 0; i < strc.length; i++) {
      shifted[i] = (char) ((strc[i] >> n) & 0xFF);
    }
    return shifted;
  }

  public void bitMoveRight(int n) {
    strc = bitShiftedRight(n);
  }

  public char[] bitShiftedLeft(int n) {
    char[] shifted = new char[strc.length];
    for (int i = 0; i < strc.length; i++) {
      shifted[i] = (char) ((strc[i] << n) & 0xFF);
    }
    return shifted;
  }

  public char[] appendedContent(char... c) {
    char[] appended = new char[strc.length + c.length];
    for (int i = 0; i < strc.length; i++) {
      appended[i] = strc[i];
    }
    for (int i = 0; i < c.length; i++) {
      appended[strc.length + i] = c[i];
    }
    return appended;
  }

  public void append(char... c) {
    strc = appendedContent(c);
  }

  public void safeAppend(char... c) {
    if (strc.length + c.length > Integer.MAX_VALUE) {
      int newSize = strc.length + c.length;
      if (newSize > Integer.MAX_VALUE) {
        newSize = Integer.MAX_VALUE;
      }
      strc = Arrays.copyOf(strc, newSize);
    } else {
      strc = appendedContent(c);
    }
  }

  public void bitMoveLeft(int n) {
    strc = bitShiftedLeft(n);
  }

  public char[] bitShiftedUnsignedRight(int n) {
    char[] shifted = new char[strc.length];
    for (int i = 0; i < strc.length; i++) {
      shifted[i] = (char) (strc[i] >>> n);
    }
    return shifted;
  }

  public void bitMoveUnsignedRight(int n) {
    strc = bitShiftedUnsignedRight(n);
  }

  public byte[] toByteArray() {
    byte[] bytes = new byte[strc.length];
    for (int i = 0; i < strc.length; i++) {
      bytes[i] = (byte) strc[i];
    }
    return bytes;
  }

  public char[] substringedContent(int start, int end) {
    char[] substr = new char[end - start];
    for (int i = start; i < end; i++) {
      substr[i - start] = strc[i];
    }
    return substr;
  }

  public void substr(int start, int end) {
    strc = substringedContent(start, end);
  }

  public void remove(int start, int end) {
    char[] newstr = new char[strc.length - (end - start)];
    for (int i = 0; i < start; i++) {
      newstr[i] = strc[i];
    }
    for (int i = end; i < strc.length; i++) {
      newstr[i - (end - start)] = strc[i];
    }
    strc = newstr;
  }

  public void split(String regex) {
    String[] split = strc.toString().split(regex);
    strc = new char[split.length];
    for (int i = 0; i < split.length; i++) {
      strc[i] = split[i].charAt(0);
    }
  }

  public void split(String regex, int limit) {
    String[] split = strc.toString().split(regex, limit);
    strc = new char[split.length];
    for (int i = 0; i < split.length; i++) {
      strc[i] = split[i].charAt(0);
    }
  }

  public void replace(int start, int end, char... c) {
    char[] newstr = new char[strc.length - (end - start) + c.length];
    for (int i = 0; i < start; i++) {
      newstr[i] = strc[i];
    }
    for (int i = 0; i < c.length; i++) {
      newstr[start + i] = c[i];
    }
    for (int i = end; i < strc.length; i++) {
      newstr[start + c.length + i - (end - start)] = strc[i];
    }
    strc = newstr;
  }

  public int match(char c) {
    int count = 0;
    for (char x : strc) {
      if (x == c) {
        count++;
      }
    }
    return count;
  }

  public void swap(int i, int j) {
    assert i < strc.length;
    assert j < strc.length;
    char temp = strc[i];
    strc[i] = strc[j];
    strc[j] = temp;
  }

  public string caesarCiphered(int n) {
    StringBuilder sb = new StringBuilder();
    for (char c : strc) {
      sb.append((char) (c + n));
    }
    return new string(sb.toString());
  }

  public string vigenereCiphered(String key) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < strc.length; i++) {
      sb.append((char) (strc[i] ^ key.charAt(i % key.length())));
    }
    return new string(sb.toString());
  }

  public string hillCiphered(int[][] key) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < strc.length; i++) {
      int sum = 0;
      for (int j = 0; j < key[0].length; j++) {
        sum += key[i % key.length][j] * strc[i];
      }
      sb.append((char) (sum % 256));
    }
    return new string(sb.toString());
  }

  public string affineCiphered(int a, int b) {
    StringBuilder sb = new StringBuilder();
    for (char c : strc) {
      sb.append((char) (a * c + b));
    }
    return new string(sb.toString());
  }

  public string atbashCiphered() {
    StringBuilder sb = new StringBuilder();
    for (char c : strc) {
      if (c >= 'a' && c <= 'z') {
        sb.append((char) (c + 'A' - 'a'));
      } else if (c >= 'A' && c <= 'Z') {
        sb.append((char) (c + 'a' - 'A'));
      } else {
        sb.append(c);
      }
    }
    return new string(sb.toString());
  }

  public Map<Character, Boolean> compareDifferences(string str) {
    Map<Character, Boolean> differences = new HashMap<>();
    for (int i = 0; i < strc.length; i++) {
      if (!differences.containsKey(strc[i])) {
        differences.put(strc[i], false);
      }
      if (strc[i] != str.strc[i]) {
        differences.put(strc[i], true);
      }
    }
    return differences;
  }

  public string bogoCiphered() {
    StringBuilder sb = new StringBuilder();
    for (char c : strc) {
      sb.append(c);
    }
    return new string(sb.toString());
  }

  public string xorCiphered(char c) {
    StringBuilder sb = new StringBuilder();
    for (char x : strc) {
      sb.append((char) (x ^ c));
    }
    return new string(sb.toString());
  }

  public string rot13Ciphered() {
    StringBuilder sb = new StringBuilder();
    for (char c : strc) {
      if (c >= 'a' && c <= 'z') {
        sb.append((char) (c + 13 > 'z' ? c - 13 : c + 13));
      } else if (c >= 'A' && c <= 'Z') {
        sb.append((char) (c + 13 > 'Z' ? c - 13 : c + 13));
      } else {
        sb.append(c);
      }
    }
    return new string(sb.toString());
  }

  public string rot47Ciphered() {
    StringBuilder sb = new StringBuilder();
    for (char c : strc) {
      if (c >= 'a' && c <= 'z') {
        sb.append((char) (c + 47 > 'z' ? c - 47 : c + 47));
      } else if (c >= 'A' && c <= 'Z') {
        sb.append((char) (c + 47 > 'Z' ? c - 47 : c + 47));
      } else {
        sb.append(c);
      }
    }
    return new string(sb.toString());
  }

  public string toHex() {
    StringBuilder sb = new StringBuilder();
    for (char c : strc) {
      sb.append(Integer.toHexString(c));
    }
    return new string(sb.toString());
  }

  public string polynomialRollingHash(int n) {
    StringBuilder sb = new StringBuilder();
    int[] hash = new int[n];
    for (int i = 0; i < n; i++) {
      hash[i] = strc[i];
    }
    for (int i = n; i < strc.length; i++) {
      hash[i % n] = strc[i];
      sb.append(hash[i % n]);
    }
    return new string(sb.toString());
  }

  public string playfairCiphered(String key) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < strc.length; i++) {
      if (strc[i] == ' ') {
        sb.append(' ');
      } else {
        sb.append(key.charAt((strc[i] - 'a') % key.length()));
      }
    }
    return new string(sb.toString());
  }

  public string railFenceCiphered(int n) {
    StringBuilder sb = new StringBuilder();
    int[][] rail = new int[n][strc.length];
    for (int i = 0; i < strc.length; i++) {
      rail[i % n][i] = strc[i];
    }
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < strc.length; j++) {
        if (rail[i][j] != 0) {
          sb.append(rail[i][j]);
        }
      }
    }
    return new string(sb.toString());
  }

  public string substitutionCipher(String key) {
    StringBuilder sb = new StringBuilder();
    for (char c : strc) {
      if (c == ' ') {
        sb.append(' ');
      } else {
        sb.append(key.charAt((c - 'a') % key.length()));
      }
    }
    return new string(sb.toString());
  }

  public void dispose() {
    strc = null;
  }

  public int[] toIntArray() {
    int[] ints = new int[strc.length];
    for (int i = 0; i < strc.length; i++) {
      ints[i] = strc[i];
    }
    return ints;
  }

  /// STATIC METHODS FOR EXTERNAL ACCESS ///

  public static string fromBytes(byte[] bytes) {
    char[] chars = new char[bytes.length];
    for (int i = 0; i < bytes.length; i++) {
      chars[i] = (char) bytes[i];
    }
    return new string(chars);
  }

  public static char[] toupper(char[] c) {
    char[] uppercased = new char[c.length];
    for (int i = 0; i < c.length; i++) {
      uppercased[i] = Character.toUpperCase(c[i]);
    }
    return uppercased;
  }

  public static char[] tolower(char[] c) {
    char[] lowercased = new char[c.length];
    for (int i = 0; i < c.length; i++) {
      lowercased[i] = Character.toLowerCase(c[i]);
    }
    return lowercased;
  }

  public static <T> string nonRefereableNewString(T[] args) {
    StringBuilder sb = new StringBuilder();
    for (T t : args) {
      sb.append(t);
    }
    return new string(sb.toString());
  }

  /// OVERRIDDEN METHODS ///

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof string) {
      string str = (string) obj;
      if (str.strc.length != strc.length) {
        return false;
      }
      for (int i = 0; i < strc.length; i++) {
        if (strc[i] != str.strc[i]) {
          return false;
        }
      }
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 89 * hash + Arrays.hashCode(this.strc);
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (char c : strc) {
      sb.append(c);
    }
    return sb.toString();
  }

  /// ONLY FOR TESTING ///

  @LibTestingOnly
  public synchronized void print() {
    System.out.println(toString());
  }
}
