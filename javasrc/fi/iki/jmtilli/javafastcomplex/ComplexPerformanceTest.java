package fi.iki.jmtilli.javafastcomplex;
/*
  Copyright (C) 2013 Juha-Matti Tilli
  
  Permission is hereby granted, free of charge, to any person obtaining a copy
  of this software and associated documentation files (the "Software"), to deal
  in the Software without restriction, including without limitation the rights
  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
  copies of the Software, and to permit persons to whom the Software is
  furnished to do so, subject to the following conditions:
  
  The above copyright notice and this permission notice shall be included in
  all copies or substantial portions of the Software.
  
  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
  SOFTWARE.
 */
/**
   Performance test for immutable complex numbers.

   Shows that having a common interface to mutable and immutable
   complex numbers has no performance penalty, but the garbage-collection
   of immutable complex numbers has a large performance penalty.
 */
public class ComplexPerformanceTest {
  public static void main(String[] args)
  {
    Complex[] ar = new Complex[1000];
    Complex sum;
    ComplexBuffer BUF_ZERO = new ComplexBuffer();
    int i;
    int j;
    for(i = 0; i < 1000; i++)
    {
      ar[i] = Complex.ZERO;
    }
    sum = Complex.ZERO;
    for(j = 0; j < 1000*1000; j++)
    {
      for(i = 0; i < 1000; i++)
      {
        ar[i] = new Complex(i, i);
      }
      sum = Complex.ZERO;
      for(i = 0; i < 1000; i++)
      {
        sum = sum.add(ar[i]);
      }
      // We do this so that the argument can be either Complex or ComplexBuffer.
      // Makes it bit harder for the JIT to optimize if the argument can be
      // either one of these.
      sum = sum.add(BUF_ZERO);
      if (sum.getReal() != 1000*(1000-1)/2 || sum.getImag() != 1000*(1000-1)/2)
      {
        System.out.println("err");
      }
    }
  }
};
