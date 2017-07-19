package fi.iki.jmtilli.javafastcomplex;

/*
  Copyright (C) 2013-2017 Juha-Matti Tilli
  
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
   A utility class for complex numbers.

   This class contains static methods which can be used for objects
   implementing the ComplexNumber interface.
 */
public class ComplexUtils {
  /**
     Returns whether the complex number is NaN.

     A comples number is considered NaN if either the real or imaginary
     part is NaN.

     @param n The complex number
     @return Whether the complex number is NaN
   */
  public static boolean isNaN(ComplexNumber n)
  {
    return Double.isNaN(n.getReal()) || Double.isNaN(n.getImag());
  }
  /**
     Returns whether the complex number is NaN.

     A comples number is considered NaN if either the real or imaginary
     part is NaN.

     @param n The complex number array
     @param i The array index
     @return Whether the complex number is NaN
   */
  public static boolean isNaN(ComplexNumberArray n, int i)
  {
    return Double.isNaN(n.getReal(i)) || Double.isNaN(n.getImag(i));
  }
  /**
     Returns whether the complex number is infinite.

     A comples number is considered infinite if either the real or imaginary
     part is infinite and the complex number is not NaN.

     @param n The complex number
     @return Whether the complex number is infinite
   */
  public static boolean isInfinite(ComplexNumber n)
  {
    if (n.isNaN())
    {
      return false;
    }
    return Double.isInfinite(n.getReal()) || Double.isInfinite(n.getImag());
  }
  /**
     Returns whether the complex number is infinite.

     A comples number is considered infinite if either the real or imaginary
     part is infinite and the complex number is not NaN.

     @param n The complex number array
     @param i The array index
     @return Whether the complex number is infinite
   */
  public static boolean isInfinite(ComplexNumberArray n, int i)
  {
    if (n.isNaN(i))
    {
      return false;
    }
    return Double.isInfinite(n.getReal(i)) || Double.isInfinite(n.getImag(i));
  }

  /**
     Returns the absolute value of the complex number.

     If the complex number is NaN, returns NaN. If the complex
     number is infinite, returns the infinity.

     @param num The complex number
     @return The absolute value of the complex number.
   */
  public static double abs(ComplexNumber num)
  {
    if (num.isNaN())
    {
      // This is required: otherwise infinity overrides NaN
      return Double.NaN;
    }
    return Math.hypot(num.getReal(), num.getImag());
  }
  /**
     Returns the absolute value of the complex number.

     If the complex number is NaN, returns NaN. If the complex
     number is infinite, returns the infinity.

     @param num The complex number array
     @param i The array index
     @return The absolute value of the complex number.
   */
  public static double abs(ComplexNumberArray num, int i)
  {
    if (num.isNaN(i))
    {
      // This is required: otherwise infinity overrides NaN
      return Double.NaN;
    }
    return Math.hypot(num.getReal(i), num.getImag(i));
  }
  /**
     Calculates the argument of this complex number.
      
     The argument is the angle between the positive real axis and the point
     that represents this number in the complex plane.

     @param num The complex number
       
     @return -pi&le;x&le;pi the argument
   */
  public static double arg(ComplexNumber num)
  {
    return Math.atan2(num.getImag(), num.getReal());
  }
  /**
     Calculates the argument of this complex number.
      
     The argument is the angle between the positive real axis and the point
     that represents this number in the complex plane.

     @param num The complex number array
     @param i The array index
       
     @return -pi&le;x&le;pi the argument
   */
  public static double arg(ComplexNumberArray num, int i)
  {
    return Math.atan2(num.getImag(i), num.getReal(i));
  }
  /**
     Create a complex number from polar coordinates.
    
     @param abs The absolute value
     @param argument The argument
     @return The new complex number
   */
  public static Complex newPolar(double abs, double argument)
  {
    return new Complex(abs*Math.cos(argument), abs*Math.sin(argument));
  }
  /**
     Raise a complex number to a real power

     @param a The complex base
     @param b The real power
     @return The result
   */
  public static Complex pow(ComplexNumber a, double b)
  {
    // Generates a bit garbage
    return new ComplexBuffer(a).logInPlace()
               .multiplyInPlace(b).expInPlace().get();
  }
  /**
     Raise a complex number to a real power

     @param a The complex base array
     @param i The array index
     @param b The real power
     @return The result
   */
  public static Complex pow(ComplexNumberArray a, int i, double b)
  {
    // Generates a bit garbage
    return new ComplexBuffer(a, i).logInPlace()
               .multiplyInPlace(b).expInPlace().get();
  }
  /**
     Raise a complex number to a complex power

     @param a The complex base
     @param b The complex power
     @return The result
   */
  public static Complex pow(ComplexNumber a, ComplexNumber b)
  {
    // Generates a bit garbage
    return new ComplexBuffer(a).logInPlace()
               .multiplyInPlace(b).expInPlace().get();
  }
  /**
     Raise a complex number to a complex power

     @param a The complex base array
     @param i The array index
     @param b The complex power
     @return The result
   */
  public static Complex pow(ComplexNumberArray a, int i, ComplexNumber b)
  {
    // Generates a bit garbage
    return new ComplexBuffer(a, i).logInPlace()
               .multiplyInPlace(b).expInPlace().get();
  }
  /**
     Raise a complex number to a complex power

     @param a The complex base array
     @param i The array index of first array
     @param b The complex power arary
     @param j The array index of second array
     @return The result
   */
  public static Complex pow(ComplexNumberArray a, int i, ComplexNumberArray b, int j)
  {
    // Generates a bit garbage
    return new ComplexBuffer(a, i).logInPlace()
               .multiplyInPlace(b, j).expInPlace().get();
  }
  /**
     Raise a complex number to a complex power

     @param a The complex base
     @param b The complex power array
     @param j The array index
     @return The result
   */
  public static Complex pow(ComplexNumber a, ComplexNumberArray b, int j)
  {
    // Generates a bit garbage
    return new ComplexBuffer(a).logInPlace()
               .multiplyInPlace(b, j).expInPlace().get();
  }

  /**
     Calculate an auxiliary number for square root calculation

     @param c The complex number
     @return The auxiliary number
   */
  static double calcSqrtAuxiliaryNumber(ComplexNumber c)
  {
    final double re = c.getReal();
    final double im = c.getImag();
    if (re == 0 && im == 0)
    {
      return 0.0;
    }
    else if (Math.abs(re) >= Math.abs(im))
    {
      final double im_div_re = im/re;
      return   Math.sqrt(Math.abs(re))
             * Math.sqrt(0.5 + 0.5*Math.sqrt(1 + im_div_re*im_div_re));
    }
    else
    {
      final double re_div_im = re/im;
      return   Math.sqrt(Math.abs(im))
             * Math.sqrt(0.5 * (  Math.abs(re_div_im)
                                + Math.sqrt(1 + re_div_im*re_div_im)));
    }
  }
  /**
     Calculate an auxiliary number for square root calculation

     @param c The complex number array
     @param i The array index
     @return The auxiliary number
   */
  static double calcSqrtAuxiliaryNumber(ComplexNumberArray c, int i)
  {
    final double re = c.getReal(i);
    final double im = c.getImag(i);
    if (re == 0 && im == 0)
    {
      return 0.0;
    }
    else if (Math.abs(re) >= Math.abs(im))
    {
      final double im_div_re = im/re;
      return   Math.sqrt(Math.abs(re))
             * Math.sqrt(0.5 + 0.5*Math.sqrt(1 + im_div_re*im_div_re));
    }
    else
    {
      final double re_div_im = re/im;
      return   Math.sqrt(Math.abs(im))
             * Math.sqrt(0.5 * (  Math.abs(re_div_im)
                                + Math.sqrt(1 + re_div_im*re_div_im)));
    }
  }

  /**
     Returns a String representation of a complex number.

     @param num The complex number array
    
     @return "NaN" if NaN<br/>
              re if purely real<br/>
              im+"i" if purely imaginary<br/>
              (re + " + " + im + "i") if im&gt;0<br/>
              (re + " - " + (-im) + "i") if im&lt;0
   */
  public static String toString(ComplexNumber num)
  {
    double re = num.getReal();
    double im = num.getImag();
    if (isNaN(num))
    {
      return "NaN";
    }
    if (im == 0)
    {
      return re + "";
    }
    else if (re == 0)
    {
      return im + "i";
    }
    else if (im < 0)
    {
      return re + " - " + (-im) + "i";
    }
    else
    {
      return re + " + " + im + "i";
    }
  }

  /**
     Returns a String representation of a complex number.

     @param num The complex number array
     @param i The array index
    
     @return "NaN" if NaN<br/>
              re if purely real<br/>
              im+"i" if purely imaginary<br/>
              (re + " + " + im + "i") if im&gt;0<br/>
              (re + " - " + (-im) + "i") if im&lt;0
   */
  public static String toString(ComplexNumberArray num, int i)
  {
    double re = num.getReal(i);
    double im = num.getImag(i);
    if (isNaN(num, i))
    {
      return "NaN";
    }
    if (im == 0)
    {
      return re + "";
    }
    else if (re == 0)
    {
      return im + "i";
    }
    else if (im < 0)
    {
      return re + " - " + (-im) + "i";
    }
    else
    {
      return re + " + " + im + "i";
    }
  }

  /**
     Add a complex number to a complex number
    
     @param a A complex number
     @param b A complex number
     @return The sum
   */
  public static Complex add(ComplexNumber a, ComplexNumber b)
  {
    return new Complex(a.getReal() + b.getReal(), a.getImag() + b.getImag());
  }
  /**
     Add a complex number to a complex number
    
     @param a A complex number array
     @param i The array index
     @param b A complex number
     @return The sum
   */
  public static Complex add(ComplexNumberArray a, int i, ComplexNumber b)
  {
    return new Complex(a.getReal(i) + b.getReal(), a.getImag(i) + b.getImag());
  }
  /**
     Add a complex number to a complex number
    
     @param a A complex number array
     @param i The array index to first array
     @param b A complex number array
     @param j The array index to second array
     @return The sum
   */
  public static Complex add(ComplexNumberArray a, int i, ComplexNumberArray b, int j)
  {
    return new Complex(a.getReal(i) + b.getReal(j), a.getImag(i) + b.getImag(j));
  }
  /**
     Add a complex number to a complex number
    
     @param a A complex number
     @param b A complex number array
     @param j The array index
     @return The sum
   */
  public static Complex add(ComplexNumber a, ComplexNumberArray b, int j)
  {
    return new Complex(a.getReal() + b.getReal(j), a.getImag() + b.getImag(j));
  }
  /**
     Add a real number to a complex number
    
     @param a The complex number
     @param b The real number
     @return The sum
   */
  public static Complex add(ComplexNumber a, double b)
  {
    return new Complex(a.getReal() + b, a.getImag());
  }
  /**
     Add a real number to a complex number
    
     @param a The complex number array
     @param i The array index
     @param b The real number
     @return The sum
   */
  public static Complex add(ComplexNumberArray a, int i, double b)
  {
    return new Complex(a.getReal(i) + b, a.getImag(i));
  }
  /**
     Add a complex number to a real number
    
     @param a The real number
     @param b The complex number
     @return The sum
   */
  public static Complex add(double a, ComplexNumber b)
  {
    return add(b, a);
  }
  /**
     Add a complex number to a real number
    
     @param a The real number
     @param b The complex number array
     @param j The array index
     @return The sum
   */
  public static Complex add(double a, ComplexNumberArray b, int j)
  {
    return add(b, j, a);
  }
  /**
     Subtract a complex number from a complex number
    
     @param a The minuend
     @param b The subtrahend
     @return The difference
   */
  public static Complex subtract(ComplexNumber a, ComplexNumber b)
  {
    return new Complex(a.getReal() - b.getReal(), a.getImag() - b.getImag());
  }
  /**
     Subtract a complex number from a complex number
    
     @param a The minuend array
     @param i The array index
     @param b The subtrahend
     @return The difference
   */
  public static Complex subtract(ComplexNumberArray a, int i, ComplexNumber b)
  {
    return new Complex(a.getReal(i) - b.getReal(), a.getImag(i) - b.getImag());
  }
  /**
     Subtract a complex number from a complex number
    
     @param a The minuend array
     @param i The first array index
     @param b The subtrahend array
     @param j The second array index
     @return The difference
   */
  public static Complex subtract(ComplexNumberArray a, int i, ComplexNumberArray b, int j)
  {
    return new Complex(a.getReal(i) - b.getReal(j), a.getImag(i) - b.getImag(j));
  }
  /**
     Subtract a complex number from a complex number
    
     @param a The minuend
     @param b The subtrahend arrary
     @param j The array index
     @return The difference
   */
  public static Complex subtract(ComplexNumber a, ComplexNumberArray b, int j)
  {
    return new Complex(a.getReal() - b.getReal(j), a.getImag() - b.getImag(j));
  }
  /**
     Subtract a real number from a complex number
    
     @param a The complex number
     @param b The real number
     @return The difference
   */
  public static Complex subtract(ComplexNumber a, double b)
  {
    return new Complex(a.getReal() - b, a.getImag());
  }
  /**
     Subtract a real number from a complex number
    
     @param a The complex number array
     @param i The array index
     @param b The real number
     @return The difference
   */
  public static Complex subtract(ComplexNumberArray a, int i, double b)
  {
    return new Complex(a.getReal(i) - b, a.getImag(i));
  }
  /**
     Subtract a complex number from a real number
    
     @param a The real number
     @param b The complex number
     @return The difference
   */
  public static Complex subtract(double a, ComplexNumber b)
  {
    return new Complex(a - b.getReal(), -b.getImag());
  }
  /**
     Subtract a complex number from a real number
    
     @param a The real number
     @param b The complex number array
     @param j The array index
     @return The difference
   */
  public static Complex subtract(double a, ComplexNumberArray b, int j)
  {
    return new Complex(a - b.getReal(j), -b.getImag(j));
  }
  /**
     Multiply a complex number by a complex number
    
     @param a A complex number
     @param b A complex number
     @return The result of the multiplication
   */
  public static Complex multiply(ComplexNumber a, ComplexNumber b)
  {
    final double a_re = a.getReal(), a_im = a.getImag();
    final double b_re = b.getReal(), b_im = b.getImag();
    return new Complex(a_re*b_re - a_im*b_im, a_im*b_re + a_re*b_im);
  }
  /**
     Multiply a complex number by a complex number
    
     @param a A complex number array
     @param i The array index
     @param b A complex number
     @return The result of the multiplication
   */
  public static Complex multiply(ComplexNumberArray a, int i, ComplexNumber b)
  {
    final double a_re = a.getReal(i), a_im = a.getImag(i);
    final double b_re = b.getReal(), b_im = b.getImag();
    return new Complex(a_re*b_re - a_im*b_im, a_im*b_re + a_re*b_im);
  }
  /**
     Multiply a complex number by a complex number
    
     @param a A complex number array
     @param i The array index to first array
     @param b A complex number array
     @param j The array index to second array
     @return The result of the multiplication
   */
  public static Complex multiply(ComplexNumberArray a, int i, ComplexNumberArray b, int j)
  {
    final double a_re = a.getReal(i), a_im = a.getImag(i);
    final double b_re = b.getReal(j), b_im = b.getImag(j);
    return new Complex(a_re*b_re - a_im*b_im, a_im*b_re + a_re*b_im);
  }
  /**
     Multiply a complex number by a complex number
    
     @param a A complex number
     @param b A complex number arary
     @param j The array index
     @return The result of the multiplication
   */
  public static Complex multiply(ComplexNumber a, ComplexNumberArray b, int j)
  {
    final double a_re = a.getReal(), a_im = a.getImag();
    final double b_re = b.getReal(j), b_im = b.getImag(j);
    return new Complex(a_re*b_re - a_im*b_im, a_im*b_re + a_re*b_im);
  }
  /**
     Divide a complex number by a complex number
    
     @param a The dividend
     @param b The divisor
     @return The result of the division
   */
  public static Complex divide(ComplexNumber a, ComplexNumber b)
  {
    final double a_re = a.getReal(), a_im = a.getImag();
    final double b_re = b.getReal(), b_im = b.getImag();
    if (Math.abs(b_re) > Math.abs(b_im))
    {
      final double b_im_div_re = b_im/b_re;
      final double w = 1.0 / (b_re + b_im*b_im_div_re);
      return new Complex((a_re + a_im*b_im_div_re) * w,
                         (a_im - a_re*b_im_div_re) * w);
    }
    else
    {
      final double b_re_div_im = b_re/b_im;
      final double w = 1.0 / (b_im + b_re*b_re_div_im);
      return new Complex((a_re*b_re_div_im + a_im) * w,
                         (a_im*b_re_div_im - a_re) * w);
    }
  }
  /**
     Divide a complex number by a complex number
    
     @param a The dividend array
     @param i The array index
     @param b The divisor
     @return The result of the division
   */
  public static Complex divide(ComplexNumberArray a, int i, ComplexNumber b)
  {
    final double a_re = a.getReal(i), a_im = a.getImag(i);
    final double b_re = b.getReal(), b_im = b.getImag();
    if (Math.abs(b_re) > Math.abs(b_im))
    {
      final double b_im_div_re = b_im/b_re;
      final double w = 1.0 / (b_re + b_im*b_im_div_re);
      return new Complex((a_re + a_im*b_im_div_re) * w,
                         (a_im - a_re*b_im_div_re) * w);
    }
    else
    {
      final double b_re_div_im = b_re/b_im;
      final double w = 1.0 / (b_im + b_re*b_re_div_im);
      return new Complex((a_re*b_re_div_im + a_im) * w,
                         (a_im*b_re_div_im - a_re) * w);
    }
  }
  /**
     Divide a complex number by a complex number
    
     @param a The dividend array
     @param i The array index to first array
     @param b The divisor array
     @param j The array index to second array
     @return The result of the division
   */
  public static Complex divide(ComplexNumberArray a, int i, ComplexNumberArray b, int j)
  {
    final double a_re = a.getReal(i), a_im = a.getImag(i);
    final double b_re = b.getReal(j), b_im = b.getImag(j);
    if (Math.abs(b_re) > Math.abs(b_im))
    {
      final double b_im_div_re = b_im/b_re;
      final double w = 1.0 / (b_re + b_im*b_im_div_re);
      return new Complex((a_re + a_im*b_im_div_re) * w,
                         (a_im - a_re*b_im_div_re) * w);
    }
    else
    {
      final double b_re_div_im = b_re/b_im;
      final double w = 1.0 / (b_im + b_re*b_re_div_im);
      return new Complex((a_re*b_re_div_im + a_im) * w,
                         (a_im*b_re_div_im - a_re) * w);
    }
  }
  /**
     Divide a complex number by a complex number
    
     @param a The dividend
     @param b The divisor array
     @param j The array index
     @return The result of the division
   */
  public static Complex divide(ComplexNumber a, ComplexNumberArray b, int j)
  {
    final double a_re = a.getReal(), a_im = a.getImag();
    final double b_re = b.getReal(j), b_im = b.getImag(j);
    if (Math.abs(b_re) > Math.abs(b_im))
    {
      final double b_im_div_re = b_im/b_re;
      final double w = 1.0 / (b_re + b_im*b_im_div_re);
      return new Complex((a_re + a_im*b_im_div_re) * w,
                         (a_im - a_re*b_im_div_re) * w);
    }
    else
    {
      final double b_re_div_im = b_re/b_im;
      final double w = 1.0 / (b_im + b_re*b_re_div_im);
      return new Complex((a_re*b_re_div_im + a_im) * w,
                         (a_im*b_re_div_im - a_re) * w);
    }
  }
  /**
     Divide a complex number by a real number
    
     @param a The complex number
     @param d The real number
     @return The result of the division
   */
  public static Complex divide(ComplexNumber a, double d)
  {
    return new Complex(a.getReal() / d, a.getImag() / d);
  }
  /**
     Divide a complex number by a real number
    
     @param a The complex number array
     @param i The array index
     @param d The real number
     @return The result of the division
   */
  public static Complex divide(ComplexNumberArray a, int i, double d)
  {
    return new Complex(a.getReal(i) / d, a.getImag(i) / d);
  }
  /**
     Multiply a complex number by a real number
    
     @param a The complex number
     @param d The real number
     @return The result of the multiplication
   */
  public static Complex multiply(ComplexNumber a, double d)
  {
    return new Complex(a.getReal() * d, a.getImag() * d);
  }
  /**
     Multiply a complex number by a real number
    
     @param a The complex number array
     @param i The array index
     @param d The real number
     @return The result of the multiplication
   */
  public static Complex multiply(ComplexNumberArray a, int i, double d)
  {
    return new Complex(a.getReal(i) * d, a.getImag(i) * d);
  }
  /**
     Multiply a real number by a complex number
    
     @param d The real number
     @param a The complex number
     @return The result of the multiplication
   */
  public static Complex multiply(double d, ComplexNumber a)
  {
    return multiply(a, d);
  }
  /**
     Multiply a real number by a complex number
    
     @param d The real number
     @param a The complex number array
     @param i The array index
     @return The result of the multiplication
   */
  public static Complex multiply(double d, ComplexNumberArray a, int i)
  {
    return multiply(a, i, d);
  }
  /**
     Multiply a complex number by an integer
    
     @param a The complex number
     @param i The integer
     @return The result of the multiplication
   */
  public static Complex multiply(ComplexNumber a, int i)
  {
    return new Complex(a.getReal() * i, a.getImag() * i);
  }
  /**
     Multiply a complex number by an integer
    
     @param a The complex number array
     @param i The array index
     @param x The integer
     @return The result of the multiplication
   */
  public static Complex multiply(ComplexNumberArray a, int i, int x)
  {
    return new Complex(a.getReal(i) * x, a.getImag(i) * x);
  }
  /**
     Multiply an integer by a complex number
    
     @param i The integer
     @param a The complex number
     @return The result of the multiplication
   */
  public static Complex multiply(int i, ComplexNumber a)
  {
    return multiply(a, i);
  }
  /**
     Multiply an integer by a complex number
    
     @param x The integer
     @param a The complex number array
     @param i The array index
     @return The result of the multiplication
   */
  public static Complex multiply(int x, ComplexNumberArray a, int i)
  {
    return multiply(a, i, x);
  }
  /**
     Calculates the negation of a complex number
    
     @param a The complex number
     @return -a
   */
  public static Complex negate(ComplexNumber a)
  {
    return new Complex(-a.getReal(), -a.getImag());
  }
  /**
     Calculates the negation of a complex number
    
     @param a The complex number array
     @param i The array index
     @return -a
   */
  public static Complex negate(ComplexNumberArray a, int i)
  {
    return new Complex(-a.getReal(i), -a.getImag(i));
  }
  /**
     Calculates the conjugate of a complex number
    
     @param a The complex number
     @return The complex conjugate of a
   */
  public static Complex conjugate(ComplexNumber a)
  {
    return new Complex(a.getReal(), -a.getImag());
  }
  /**
     Calculates the conjugate of a complex number
    
     @param a The complex number array
     @param i The array index
     @return The complex conjugate of a
   */
  public static Complex conjugate(ComplexNumberArray a, int i)
  {
    return new Complex(a.getReal(i), -a.getImag(i));
  }
  /**
     Divide a real number by a complex number
    
     @param d The real number
     @param c The complex number
     @return The result of the division
   */
  public static Complex divide(double d, ComplexNumber c)
  {
    final double re = c.getReal(), im = c.getImag();
    if (Math.abs(re) > Math.abs(im))
    {
      final double im_div_re = im/re;
      final double w = d / (re + im*im_div_re);
      return new Complex(w, -im_div_re * w);
    }
    else
    {
      final double re_div_im = re/im;
      final double w = d / (im + re*re_div_im);
      return new Complex(re_div_im * w, -w);
    }
  }
  /**
     Divide a real number by a complex number
    
     @param d The real number
     @param c The complex number array
     @param i The array index
     @return The result of the division
   */
  public static Complex divide(double d, ComplexNumberArray c, int i)
  {
    final double re = c.getReal(i), im = c.getImag(i);
    if (Math.abs(re) > Math.abs(im))
    {
      final double im_div_re = im/re;
      final double w = d / (re + im*im_div_re);
      return new Complex(w, -im_div_re * w);
    }
    else
    {
      final double re_div_im = re/im;
      final double w = d / (im + re*re_div_im);
      return new Complex(re_div_im * w, -w);
    }
  }
  /**
     Calculates the inverse of a complex number
    
     @param c The complex number
     @return 1 divided by c
   */
  public static Complex invert(ComplexNumber c)
  {
    return divide(1.0, c);
  }
  /**
     Calculates the inverse of a complex number
    
     @param c The complex number array
     @param i The array index
     @return 1 divided by c
   */
  public static Complex invert(ComplexNumberArray c, int i)
  {
    return divide(1.0, c, i);
  }
  /**
     Calculates the square root of a complex number
    
     @param c The complex number
     @return The square root of c
   */
  public static Complex sqrt(ComplexNumber c)
  {
    final double w = calcSqrtAuxiliaryNumber(c);
    final double re = c.getReal();
    final double im = c.getImag();
    if (w == 0.0)
    {
      return Complex.ZERO;
    }
    else if (re >= 0)
    {
      return new Complex(w, im/(2*w));
    }
    else if (im >= 0)
    {
      return new Complex(Math.abs(im)/(2*w), w);
    }
    else
    {
      return new Complex(Math.abs(im)/(2*w), -w);
    }
  }
  /**
     Calculates the square root of a complex number
    
     @param c The complex number array
     @param i The array index
     @return The square root of c
   */
  public static Complex sqrt(ComplexNumberArray c, int i)
  {
    final double w = calcSqrtAuxiliaryNumber(c, i);
    final double re = c.getReal(i);
    final double im = c.getImag(i);
    if (w == 0.0)
    {
      return Complex.ZERO;
    }
    else if (re >= 0)
    {
      return new Complex(w, im/(2*w));
    }
    else if (im >= 0)
    {
      return new Complex(Math.abs(im)/(2*w), w);
    }
    else
    {
      return new Complex(Math.abs(im)/(2*w), -w);
    }
  }
  /**
     Calculates the exponential of the complex number.
    
     @param c The complex number
     @return e raised to the power c
   */
  public static Complex exp(ComplexNumber c)
  {
    final double m = Math.exp(c.getReal());
    return new Complex(m*Math.cos(c.getImag()), m*Math.sin(c.getImag()));
  }
  /**
     Calculates the exponential of the complex number.
    
     @param c The complex number array
     @param i The array index
     @return e raised to the power c
   */
  public static Complex exp(ComplexNumberArray c, int i)
  {
    final double m = Math.exp(c.getReal(i));
    return new Complex(m*Math.cos(c.getImag(i)), m*Math.sin(c.getImag(i)));
  }
  /**
     Calculates the natural logarithm of the complex number.
    
     @param c The complex number
     @return The natural logarithm
   */
  public static Complex log(ComplexNumber c)
  {
    return new Complex(Math.log(c.abs()), c.arg());
  }
  /**
     Calculates the natural logarithm of the complex number.
    
     @param c The complex number array
     @param i The array index
     @return The natural logarithm
   */
  public static Complex log(ComplexNumberArray c, int i)
  {
    return new Complex(Math.log(c.abs(i)), c.arg(i));
  }
  /**
     Returns log(c+1). For values of c near 0, calculating log1p(c) is much
     more accurate than calculating log(1+c).
    
     @param c The complex number
     @return The value log(1+x)
   */
  public static Complex log1p(ComplexNumber c)
  {
    final double rho = c.abs();
    if (rho > 0.375)
    {
      return log(Complex.ONE.add(c));
    }
    return new Complex(0.5*Math.log1p(2*c.getReal() + rho*rho),
                       Complex.ONE.add(c).arg());
  }
  /**
     Returns log(c+1). For values of c near 0, calculating log1p(c) is much
     more accurate than calculating log(1+c).
    
     @param c The complex number array
     @param i The array index
     @return The value log(1+x)
   */
  public static Complex log1p(ComplexNumberArray c, int i)
  {
    final double rho = c.abs(i);
    if (rho > 0.375)
    {
      return log(ComplexUtils.add(Complex.ONE, c, i));
    }
    return new Complex(0.5*Math.log1p(2*c.getReal(i) + rho*rho),
                       ComplexUtils.add(Complex.ONE, c, i).arg());
  }
  /**
     Returns exp(c)-1. For values of c near 0, calculating expm1(c) is much
     more accurate than calculating exp(c)-1.
    
     @param c The complex number
     @return The value exp(c)-1
   */
  public static Complex expm1(ComplexNumber c)
  {
    /*
       expm1(z) = exp(x)*exp(i*y) - 1
                =   expm1(x) * (1 - 2*sin(y/2)**2)
                  - 2*sin(y/2)**2
                  + i*sin(y)*(1 + expm1(x))
     */
    final double re = c.getReal();
    final double im = c.getImag();
    final double rho = c.abs();
    double two_mul_sin_im_div_2_sq;
    double expm1_re;
    if (rho > 0.5)
    {
      // Generates a bit garbage
      ComplexBuffer buf = new ComplexBuffer(c);
      buf.expInPlace();
      return new Complex(buf.getReal() - 1, buf.getImag());
    }
    expm1_re = Math.expm1(re);
    two_mul_sin_im_div_2_sq = Math.sin(im/2);
    two_mul_sin_im_div_2_sq = two_mul_sin_im_div_2_sq * two_mul_sin_im_div_2_sq;
    return new Complex(  expm1_re * (1 - two_mul_sin_im_div_2_sq)
                       - two_mul_sin_im_div_2_sq,
                       Math.sin(im)*(1 + expm1_re));
  }
  /**
     Returns exp(c)-1. For values of c near 0, calculating expm1(c) is much
     more accurate than calculating exp(c)-1.
    
     @param c The complex number array
     @param i The array index
     @return The value exp(c)-1
   */
  public static Complex expm1(ComplexNumberArray c, int i)
  {
    /*
       expm1(z) = exp(x)*exp(i*y) - 1
                =   expm1(x) * (1 - 2*sin(y/2)**2)
                  - 2*sin(y/2)**2
                  + i*sin(y)*(1 + expm1(x))
     */
    final double re = c.getReal(i);
    final double im = c.getImag(i);
    final double rho = c.abs(i);
    double two_mul_sin_im_div_2_sq;
    double expm1_re;
    if (rho > 0.5)
    {
      // Generates a bit garbage
      ComplexBuffer buf = new ComplexBuffer(c, i);
      buf.expInPlace();
      return new Complex(buf.getReal() - 1, buf.getImag());
    }
    expm1_re = Math.expm1(re);
    two_mul_sin_im_div_2_sq = Math.sin(im/2);
    two_mul_sin_im_div_2_sq = two_mul_sin_im_div_2_sq * two_mul_sin_im_div_2_sq;
    return new Complex(  expm1_re * (1 - two_mul_sin_im_div_2_sq)
                       - two_mul_sin_im_div_2_sq,
                       Math.sin(im)*(1 + expm1_re));
  }
  /**
     Calculate the inverse hyperbolic cosine of a complex number.
    
     @param c The complex number
     @return The inverse hyperbolic cosine of c
   */
  public static Complex acosh(ComplexNumber c)
  {
    // Generates a bit garbage
    return new ComplexBuffer(c).acoshInPlace().get();
  }
  /**
     Calculate the inverse hyperbolic cosine of a complex number.
    
     @param c The complex number array
     @param i The array index
     @return The inverse hyperbolic cosine of c
   */
  public static Complex acosh(ComplexNumberArray c, int i)
  {
    // Generates a bit garbage
    return new ComplexBuffer(c, i).acoshInPlace().get();
  }
  /**
     Calculate the inverse hyperbolic sine of a complex number.
    
     @param c The complex number
     @return The inverse hyperbolic sine of c
   */
  public static Complex asinh(ComplexNumber c)
  {
    // Generates a bit garbage
    return new ComplexBuffer(c).asinhInPlace().get();
  }
  /**
     Calculate the inverse hyperbolic sine of a complex number.
    
     @param c The complex number array
     @param i The array index
     @return The inverse hyperbolic sine of c
   */
  public static Complex asinh(ComplexNumberArray c, int i)
  {
    // Generates a bit garbage
    return new ComplexBuffer(c, i).asinhInPlace().get();
  }
  /**
     Calculate the inverse hyperbolic tangent of a complex number.
    
     @param c The complex number
     @return The inverse hyperbolic tangent of c
   */
  public static Complex atanh(ComplexNumber c)
  {
    // Generates a bit garbage
    return new ComplexBuffer(c).atanhInPlace().get();
  }
  /**
     Calculate the inverse hyperbolic tangent of a complex number.
    
     @param c The complex number array
     @param i The array index
     @return The inverse hyperbolic tangent of c
   */
  public static Complex atanh(ComplexNumberArray c, int i)
  {
    // Generates a bit garbage
    return new ComplexBuffer(c, i).atanhInPlace().get();
  }
  /**
     Calculate the inverse cosine of a complex number.
    
     @param c The complex number
     @return The inverse cosine of c
   */
  public static Complex acos(ComplexNumber c)
  {
    // Generates a bit garbage
    return new ComplexBuffer(c).acosInPlace().get();
  }
  /**
     Calculate the inverse cosine of a complex number.
    
     @param c The complex number array
     @param i The array index
     @return The inverse cosine of c
   */
  public static Complex acos(ComplexNumberArray c, int i)
  {
    // Generates a bit garbage
    return new ComplexBuffer(c, i).acosInPlace().get();
  }
  /**
     Calculate the inverse sine of a complex number.
    
     @param c The complex number
     @return The inverse sine of c
   */
  public static Complex asin(ComplexNumber c)
  {
    // Generates a bit garbage
    return new ComplexBuffer(c).asinInPlace().get();
  }
  /**
     Calculate the inverse sine of a complex number.
    
     @param c The complex number array
     @param i The array index
     @return The inverse sine of c
   */
  public static Complex asin(ComplexNumberArray c, int i)
  {
    // Generates a bit garbage
    return new ComplexBuffer(c, i).asinInPlace().get();
  }
  /**
     Calculate the inverse tangent of a complex number.
    
     @param c The complex number
     @return The inverse tangent of c
   */
  public static Complex atan(ComplexNumber c)
  {
    // Generates a bit garbage
    return new ComplexBuffer(c).atanInPlace().get();
  }
  /**
     Calculate the inverse tangent of a complex number.
    
     @param c The complex number array
     @param i The array index
     @return The inverse tangent of c
   */
  public static Complex atan(ComplexNumberArray c, int i)
  {
    // Generates a bit garbage
    return new ComplexBuffer(c, i).atanInPlace().get();
  }
  /**
     Calculate the cosine of a complex number.
    
     @param c The complex number
     @return The cosine of c
   */
  public static Complex cos(ComplexNumber c)
  {
    return new Complex(Math.cos(c.getReal()) * Math.cosh(c.getImag()),
                       -Math.sin(c.getReal()) * Math.sinh(c.getImag()));
  }
  /**
     Calculate the cosine of a complex number.
    
     @param c The complex number array
     @param i The array index
     @return The cosine of c
   */
  public static Complex cos(ComplexNumberArray c, int i)
  {
    return new Complex(Math.cos(c.getReal(i)) * Math.cosh(c.getImag(i)),
                       -Math.sin(c.getReal(i)) * Math.sinh(c.getImag(i)));
  }
  /**
     Calculate the sine of a complex number.
    
     @param c The complex number
     @return The sine of c
   */
  public static Complex sin(ComplexNumber c)
  {
    return new Complex(Math.sin(c.getReal()) * Math.cosh(c.getImag()),
                       Math.cos(c.getReal()) * Math.sinh(c.getImag()));
  }
  /**
     Calculate the sine of a complex number.
    
     @param c The complex number array
     @param i The array index
     @return The sine of c
   */
  public static Complex sin(ComplexNumberArray c, int i)
  {
    return new Complex(Math.sin(c.getReal(i)) * Math.cosh(c.getImag(i)),
                       Math.cos(c.getReal(i)) * Math.sinh(c.getImag(i)));
  }
  /**
     Calculate the tangent of a complex number.
    
     @param c The complex number
     @return The tangent of c
   */
  public static Complex tan(ComplexNumber c)
  {
    final double real_x2 = c.getReal() * 2;
    final double imag_x2 = c.getImag() * 2;
    final double d = Math.cos(real_x2) + Math.cosh(imag_x2);
    return new Complex(Math.sin(real_x2)/d, Math.sinh(imag_x2)/d);
  }
  /**
     Calculate the tangent of a complex number.
    
     @param c The complex number array
     @param i The array index
     @return The tangent of c
   */
  public static Complex tan(ComplexNumberArray c, int i)
  {
    final double real_x2 = c.getReal(i) * 2;
    final double imag_x2 = c.getImag(i) * 2;
    final double d = Math.cos(real_x2) + Math.cosh(imag_x2);
    return new Complex(Math.sin(real_x2)/d, Math.sinh(imag_x2)/d);
  }
  /**
     Calculate the hyperbolic cosine of a complex number.
    
     @param c The complex number
     @return The hyperbolic cosine of c
   */
  public static Complex cosh(ComplexNumber c)
  {
    return new Complex(Math.cosh(c.getReal()) * Math.cos(c.getImag()),
                       Math.sinh(c.getReal()) * Math.sin(c.getImag()));
  }
  /**
     Calculate the hyperbolic cosine of a complex number.
    
     @param c The complex number array
     @param i The array index
     @return The hyperbolic cosine of c
   */
  public static Complex cosh(ComplexNumberArray c, int i)
  {
    return new Complex(Math.cosh(c.getReal(i)) * Math.cos(c.getImag(i)),
                       Math.sinh(c.getReal(i)) * Math.sin(c.getImag(i)));
  }
  /**
     Calculate the hyperbolic sine of a complex number.
    
     @param c The complex number
     @return The hyperbolic sine of c
   */
  public static Complex sinh(ComplexNumber c)
  {
    return new Complex(Math.sinh(c.getReal()) * Math.cos(c.getImag()),
                       Math.cosh(c.getReal()) * Math.sin(c.getImag()));
  }
  /**
     Calculate the hyperbolic sine of a complex number.
    
     @param c The complex number array
     @param i The array index
     @return The hyperbolic sine of c
   */
  public static Complex sinh(ComplexNumberArray c, int i)
  {
    return new Complex(Math.sinh(c.getReal(i)) * Math.cos(c.getImag(i)),
                       Math.cosh(c.getReal(i)) * Math.sin(c.getImag(i)));
  }
  /**
     Calculate the hyperbolic tangent of a complex number.
    
     @param c The complex number
     @return The hyperbolic tangent of c
   */
  public static Complex tanh(ComplexNumber c)
  {
    final double real_x2 = c.getReal() * 2;
    final double imag_x2 = c.getImag() * 2;
    final double d = Math.cosh(real_x2) + Math.cos(imag_x2);
    return new Complex(Math.sinh(real_x2)/d, Math.sin(imag_x2)/d);
  }
  /**
     Calculate the hyperbolic tangent of a complex number.
    
     @param c The complex number array
     @param i The array index
     @return The hyperbolic tangent of c
   */
  public static Complex tanh(ComplexNumberArray c, int i)
  {
    final double real_x2 = c.getReal(i) * 2;
    final double imag_x2 = c.getImag(i) * 2;
    final double d = Math.cosh(real_x2) + Math.cos(imag_x2);
    return new Complex(Math.sinh(real_x2)/d, Math.sin(imag_x2)/d);
  }

  /**
     Returns a hash code of the complex number.
    
     The hash code is based on the bit representations of the real and
     imaginary parts. If two complex numbers are considered equal by
     the equal method, they have the same hash code.
    
     @param c The complex number
     @return The hash code
   */
  public static int hashCode(ComplexNumber c)
  {
    long x1, x2;
    int hash = 7;
    if (c.isNaN())
    {
      /*
         All instances that have isNaN() are equal to each other, and
         therefore we must have a consistent hash code.
       */
      x1 = Double.doubleToLongBits(Double.NaN);
      x2 = x1;
    }
    else
    {
      x1 = Double.doubleToLongBits(c.getReal());
      x2 = Double.doubleToLongBits(c.getImag());
    }
    hash = 17 * hash + (int)(x1);
    hash = 17 * hash + (int)(x1 >>> 32);
    hash = 17 * hash + (int)(x1 >>> 48);
    hash = 17 * hash + (int)(x2);
    hash = 17 * hash + (int)(x2 >>> 32);
    hash = 17 * hash + (int)(x2 >>> 48);
    return hash;
  }
  /**
     Returns a hash code of the complex number.
    
     The hash code is based on the bit representations of the real and
     imaginary parts. If two complex numbers are considered equal by
     the equal method, they have the same hash code.
    
     @param c The complex number array
     @param i The array index
     @return The hash code
   */
  public static int hashCode(ComplexNumberArray c, int i)
  {
    long x1, x2;
    int hash = 7;
    if (c.isNaN(i))
    {
      /*
         All instances that have isNaN() are equal to each other, and
         therefore we must have a consistent hash code.
       */
      x1 = Double.doubleToLongBits(Double.NaN);
      x2 = x1;
    }
    else
    {
      x1 = Double.doubleToLongBits(c.getReal(i));
      x2 = Double.doubleToLongBits(c.getImag(i));
    }
    hash = 17 * hash + (int)(x1);
    hash = 17 * hash + (int)(x1 >>> 32);
    hash = 17 * hash + (int)(x1 >>> 48);
    hash = 17 * hash + (int)(x2);
    hash = 17 * hash + (int)(x2 >>> 32);
    hash = 17 * hash + (int)(x2 >>> 48);
    return hash;
  }
  /**
     Compare two complex numbers with all cases of NaN considered equal.

     Note that this compares the bit representations of the real and imaginary
     parts. All instances of NaN are considered equal to each other. Thus for
     example NaN is equal to itself and +0.0+0.0i is not equal to -0.0-0.0i.
     This definition allows hash tables to work properly.

     @return true if both complex numbers are NaN<br/>
             true if the bit representations of the real and imaginary
             parts are equal<br/>
             false otherwise
   */
  public static boolean equal(ComplexNumber a, ComplexNumber b)
  {
    if (a == b)
    {
      return true;
    }
    if (a.isNaN() && b.isNaN())
    {
      // To make hash tables work correctly. We want only one NaN.
      return true;
    }
    return longBitsEqual(a, b);
  }
  /**
     Compare the bit representations of real and imaginary parts of two
     complex numbers.

     Note that this compares the bit representations of the real and imaginary
     parts separately. Thus for example +0.0i+0.0i is not equal to
     -0.0-0.0i. Note that eg. NaN+0i is not equal to 0+NaNi, ie. all
     instances of NaN are not considered equal to each other.

     @return true if the bit representations of the real and imaginary
             parts are equal<br/>
             false otherwise
   */
  public static boolean longBitsEqual(ComplexNumber a, ComplexNumber b)
  {
    if (a == b)
    {
      return true;
    }
    return       Double.doubleToLongBits(a.getReal())
              == Double.doubleToLongBits(b.getReal())
           &&    Double.doubleToLongBits(a.getImag())
              == Double.doubleToLongBits(b.getImag());
  }
  /**
     Compare the real and imaginary parts of two complex numbers.

     Note that this compares the real and imaginary parts with the "=="
     operator. Thus for example NaN is not equal to itself, and +0.0+0.0i
     is considered equal to -0.0-0.0i.

     @return true if the the real and imaginary parts are equal with
                  the "==" comparison operator<br/>
             false otherwise
   */
  public static boolean equalRealImag(ComplexNumber a, ComplexNumber b)
  {
    // NB: NaN is not equal to itself here!
    return a.getReal() == b.getReal() && a.getImag() == b.getImag();
  }
};
