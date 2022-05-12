# better_string
better string for java, iterable!

This java extension contains some pre-implemented methods, such as cipers, and also replace().
You must provide extra abstraction for any algorithms provided by this.

Further methods will be added and updates will be added later.

## Example Usage:

```java
public static void main(String... args) {
  string str = new string("hello");
  str.forEach((v) -> {
    System.out.println(v);
  });
  char x = str.strc[0]; // h
}
```

## Class Extension
It is no need for one to remove the "final" keyword from the class declaration. This is supposed to be 
a similar class to the original String class, and shall not be extended.
