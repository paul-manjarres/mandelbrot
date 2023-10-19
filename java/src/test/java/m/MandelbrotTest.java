package m;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MandelbrotTest {
  public static final int RESULT = 78513425;

  @Test
  public void test(){
    int[][] result = Mandelbrot.mandelbrot();
    int sum_result = Arrays.stream(result).flatMapToInt(Arrays::stream).sum();
    assertEquals( RESULT, sum_result);
    System.out.println("Sum Result: "+sum_result);
  }
}