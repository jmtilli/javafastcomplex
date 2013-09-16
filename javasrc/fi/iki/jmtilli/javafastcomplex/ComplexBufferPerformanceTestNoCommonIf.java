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
   Performance test for mutable complex numbers with no common interface
   with immutable complex numbers.

   Shows that having a common interface to mutable and immutable
   complex numbers has practically no performance penalty.
 */
public class ComplexBufferPerformanceTestNoCommonIf {
  /**
     A simple complex buffer with omitted common interface with other
     complex number classes.

     Supports only addition.
   */
  private static class ComplexBufferNoCommonIf {
    /**
       The real part.
     */
    private double re;
    /**
       The imaginary part.
     */
    private double im;
    /**
       Create a complex buffer with zero as the real and imaginary parts.
     */
    public ComplexBufferNoCommonIf()
    {
      this.re = 0;
      this.im = 0;
    }
    /**
       Set the real and imaginary parts.
     */
    public void set(double re, double im)
    {
      this.re = re;
      this.im = im;
    }
    /**
       Add the number in another complex buffer of the same type to this
       complex number.

       Supports adding only complex numbers that have the same type.

       @param that The other complex buffer
     */
    public void addInPlace(ComplexBufferNoCommonIf that)
    {
      this.re += that.re;
      this.im += that.im;
    }
  };
  public static void main(String[] args)
  {
    ComplexBufferNoCommonIf[] ar = new ComplexBufferNoCommonIf[1000];
    ComplexBufferNoCommonIf sum;
    int i;
    int j;
    for(i = 0; i < 1000; i++)
    {
      ar[i] = new ComplexBufferNoCommonIf();
    }
    sum = new ComplexBufferNoCommonIf();
    for(j = 0; j < 1000*1000; j++)
    {
      for(i = 0; i < 1000; i++)
      {
        ar[i].set(i, i);
      }
      sum.set(0, 0);
      for(i = 0; i < 1000; i++)
      {
        sum.addInPlace(ar[i]);
      }
      if (sum.re != 1000*(1000-1)/2 || sum.im != 1000*(1000-1)/2)
      {
        System.out.println("err");
      }
    }
  }
};
