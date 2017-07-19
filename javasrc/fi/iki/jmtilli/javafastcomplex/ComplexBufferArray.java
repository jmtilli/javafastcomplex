package fi.iki.jmtilli.javafastcomplex;
import java.io.Serializable;

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
   A mutable complex number array.

   An instance of this class refers to a complex number the value of
   which can be changed. This allows higher performance, as if the
   value of the number can be changed, not that many small objects
   need to be created.
 */
public class ComplexBufferArray implements ComplexNumberArray, Serializable {
  private static final long serialVersionUID = -8014038048264709473L;
  /**
     The real part.
     @serial
   */
  private double[] re;
  /**
     The imaginary part.
     @serial
   */
  private double[] im;

  /**
     Returns the size of the array.

     @return The size
   */
  public int size()
  {
    return re.length;
  }

  /**
     Create a complex buffer array

     @param size The size of the array
   */
  public ComplexBufferArray(int size)
  {
    this.re = new double[size];
    this.im = new double[size];
  }
  /**
     Modify the real part of this complex buffer and set imaginary part to zero.

     @param i The array index
     @param re The new real part
     @return this
   */
  public ComplexBufferArray set(int i, double re)
  {
    this.re[i] = re;
    this.im[i] = +0.0;
    return this;
  }
  /**
     Modify the real and imaginary parts of this complex buffer

     @param i The array index
     @param re The new real part
     @param im The new imaginary part
     @return this
   */
  public ComplexBufferArray set(int i, double re, double im)
  {
    this.re[i] = re;
    this.im[i] = im;
    return this;
  }

  /**
     Returns the real part of the complex number.

     @param i The array index
     @return The real part
   */
  public double getReal(int i)
  {
    return this.re[i];
  }
  /**
     Returns the imaginary part of the complex number.

     @param i The array index
     @return The imaginary part
   */
  public double getImag(int i)
  {
    return this.im[i];
  }



  /**
     Modify the value of this complex buffer

     @param i The array index
     @param num The new value
     @return this
   */
  public ComplexBufferArray set(int i, ComplexNumber num)
  {
    return this.set(i, num.getReal(), num.getImag());
  }
  /**
     Modify the value of this complex buffer

     @param i The array index
     @param num The new value array
     @param j The array index to the other array
     @return this
   */
  public ComplexBufferArray set(int i, ComplexNumberArray num, int j)
  {
    return this.set(i, num.getReal(j), num.getImag(j));
  }
  /**
     Get the value of this complex buffer as an immutable object

     @param i The array index
     @return A new immutable complex number that has the same value as this
             buffer
   */
  public Complex get(int i)
  {
    return Complex.valueOf(this.getReal(i), this.getImag(i));
  }
  /**
     Add another complex number to this buffer and store the result in this
     buffer

     @param i The array index
     @param c The other complex number

     @return this
   */
  public ComplexBufferArray addInPlace(int i, ComplexNumber c)
  {
    return this.set(i, this.getReal(i) + c.getReal(), this.getImag(i) + c.getImag());
  }
  /**
     Add another complex number to this buffer and store the result in this
     buffer

     @param i The array index
     @param c The other complex number array
     @param j The array index to the other array

     @return this
   */
  public ComplexBufferArray addInPlace(int i, ComplexNumberArray c, int j)
  {
    return this.set(i, this.getReal(i) + c.getReal(j), this.getImag(i) + c.getImag(j));
  }
  /**
     Add a real number to this buffer and store the result in this buffer

     @param i The array index
     @param d The real number

     @return this
   */
  public ComplexBufferArray addInPlace(int i, double d)
  {
    return this.set(i, this.getReal(i) + d, this.getImag(i));
  }
  /**
     Subtract another complex number from this buffer and store the result in
     this buffer

     @param i The array index
     @param c The other complex number

     @return this
   */
  public ComplexBufferArray subtractInPlace(int i, ComplexNumber c)
  {
    return this.set(i, this.getReal(i) - c.getReal(), this.getImag(i) - c.getImag());
  }
  /**
     Subtract another complex number from this buffer and store the result in
     this buffer

     @param i The array index
     @param c The other complex number array
     @param j The array index to the other array

     @return this
   */
  public ComplexBufferArray subtractInPlace(int i, ComplexNumberArray c, int j)
  {
    return this.set(i, this.getReal(i) - c.getReal(j), this.getImag(i) - c.getImag(j));
  }
  /**
     Subtract a real number from this buffer and store the result in
     this buffer

     @param i The array index
     @param d The real number

     @return this
   */
  public ComplexBufferArray subtractInPlace(int i, double d)
  {
    return this.set(i, this.getReal(i) - d, this.getImag(i));
  }
  /**
     Subtract the value of this buffer from another complex number and store
     the result in this buffer

     @param i The array index
     @param c The other complex number

     @return this
   */
  public ComplexBufferArray subtractReversedInPlace(int i, ComplexNumber c)
  {
    return this.set(i, c.getReal() - this.getReal(i), c.getImag() - this.getImag(i));
  }
  /**
     Subtract the value of this buffer from another complex number and store
     the result in this buffer

     @param i The array index
     @param c The other complex number array
     @param j The array index to the other array

     @return this
   */
  public ComplexBufferArray subtractReversedInPlace(int i, ComplexNumberArray c, int j)
  {
    return this.set(i, c.getReal(j) - this.getReal(i), c.getImag(j) - this.getImag(i));
  }
  /**
     Subtract the value of this buffer from a real number and store the result
     in this buffer

     @param i The array index
     @param d The real number

     @return this
   */
  public ComplexBufferArray subtractReversedInPlace(int i, double d)
  {
    return this.set(i, d - this.getReal(i), -this.getImag(i));
  }
  /**
     Negate the value of this buffer and store the result in this buffer

     @param i The array index
     @return this
   */
  public ComplexBufferArray negateInPlace(int i)
  {
    return this.set(i, -this.getReal(i), -this.getImag(i));
  }
  /**
     Calculate the conjugate of the value of this buffer and store the
     result in this buffer

     @param i The array index
     @return this
   */
  public ComplexBufferArray conjugateInPlace(int i)
  {
    return this.set(i, this.getReal(i), -this.getImag(i));
  }
  /**
     Calculate the inverse of the value of this buffer and store the
     result in this buffer

     @param i The array index
     @return this
   */
  public ComplexBufferArray invertInPlace(int i)
  {
    return divideReversedInPlace(i, 1.0);
  }
  /**
     Calculate the square root of the value of this buffer and store the result
     in this buffer

     @param i The array index
     @return this
   */
  public ComplexBufferArray sqrtInPlace(int i)
  {
    final double w = ComplexUtils.calcSqrtAuxiliaryNumber(this, i);
    final double re = this.getReal(i);
    final double im = this.getImag(i);
    if (w == 0.0)
    {
      return this.set(i, +0.0, +0.0);
    }
    else if (re >= 0.0)
    {
      return this.set(i, w, im/(2*w));
    }
    else if (im >= 0.0)
    {
      return this.set(i, Math.abs(im)/(2*w), w);
    }
    else
    {
      return this.set(i, Math.abs(im)/(2*w), -w);
    }
  }
  /**
     Calculate the exponential of the value of this buffer and store the result
     in this buffer

     @param i The array index
     @return this
   */
  public ComplexBufferArray expInPlace(int i)
  {
    final double m = Math.exp(this.getReal(i));
    return this.set(i, m*Math.cos(this.getImag(i)), m*Math.sin(this.getImag(i)));
  }
  /**
     Calculate the logarithm of the value of this buffer and store the result
     in this buffer

     @param i The array index
     @return this
   */
  public ComplexBufferArray logInPlace(int i)
  {
    return this.set(i, Math.log(this.abs(i)), this.arg(i));
  }
  /**
     Calculate the logarithm of 1 added to the value of this buffer and store
     the result in this buffer

     @param i The array index
     @return this
   */
  public ComplexBufferArray log1pInPlace(int i)
  {
    final double rho = this.abs(i);
    final double re = this.getReal(i);
    this.set(i, this.getReal(i) + 1, this.getImag(i));
    if (rho > 0.375)
    {
      return this.logInPlace(i);
    }
    return this.set(i, 0.5*Math.log1p(2*re + rho*rho), this.arg(i));
  }
  /**
     Calculate exp(this)-1 and store the result in this buffer

     @param i The array index
     @return this
   */
  public ComplexBufferArray expm1InPlace(int i)
  {
    /*
       expm1(z) = exp(x)*exp(i*y) - 1
                =   expm1(x) * (1 - 2*sin(y/2)**2)
                  - 2*sin(y/2)**2
                  + i*sin(y)*(1 + expm1(x))
     */
    final double re = this.getReal(i);
    final double im = this.getImag(i);
    final double rho = this.abs(i);
    double expm1_re;
    double two_mul_sin_im_div_2_sq;
    if (rho > 0.5)
    {
      this.expInPlace(i);
      return this.set(i, this.getReal(i) - 1, this.getImag(i));
    }
    expm1_re = Math.expm1(re);
    two_mul_sin_im_div_2_sq = Math.sin(im/2);
    two_mul_sin_im_div_2_sq = two_mul_sin_im_div_2_sq * two_mul_sin_im_div_2_sq;
    return this.set(i,
                      expm1_re * (1 - two_mul_sin_im_div_2_sq)
                    - two_mul_sin_im_div_2_sq,
                    Math.sin(im)*(1 + expm1_re));
  }
  /**
     Calculate the inverse hyperbolic cosine of the value of the buffer
     and store the result in this buffer

     @param i The array index
     @return this
   */
  public ComplexBufferArray acoshInPlace(int i)
  {
    // Could be optimized a lot, eg. multiplication with Complex.I
    this.acosInPlace(i);
    return this.multiplyInPlace(i, Complex.I);
  }
  /**
     Calculate the inverse hyperbolic sine of the value of the buffer
     and store the result in this buffer

     @param i The array index
     @return this
   */
  public ComplexBufferArray asinhInPlace(int i)
  {
    // Could be optimized a lot, eg. multiplication with Complex.I
    this.multiplyInPlace(i, Complex.I);
    this.asinInPlace(i);
    this.multiplyInPlace(i, Complex.I);
    return this.negateInPlace(i);
  }
  /**
     Calculate the inverse hyperbolic tangent of the value of the buffer
     and store the result in this buffer

     @param i The array index
     @return this
   */
  public ComplexBufferArray atanhInPlace(int i)
  {
    // Could be optimized a lot, eg. multiplication with Complex.I
    this.multiplyInPlace(i, Complex.I);
    this.atanInPlace(i);
    this.multiplyInPlace(i, Complex.I);
    return this.negateInPlace(i);
  }
  /**
     Calculate the inverse cosine of the value of the buffer
     and store the result in this buffer

     @param i The array index
     @return this
   */
  public ComplexBufferArray acosInPlace(int i)
  {
    // Generates one object of garbage
    // Could be optimized a lot, eg. multiplication with Complex.I
    final ComplexBuffer copy = new ComplexBuffer(getReal(i), getImag(i));
    this.multiplyInPlace(i, copy).subtractReversedInPlace(i, 1.0).sqrtInPlace(i);
    this.multiplyInPlace(i, Complex.I).addInPlace(i, copy);
    this.logInPlace(i);
    return this.multiplyInPlace(i, Complex.I).negateInPlace(i);
  }
  /**
     Calculate the inverse sine of the value of the buffer
     and store the result in this buffer

     @param i The array index
     @return this
   */
  public ComplexBufferArray asinInPlace(int i)
  {
    // Generates one object of garbage
    // Could be optimized a lot, eg. multiplication with Complex.I
    final ComplexBuffer copy = new ComplexBuffer(getReal(i), getImag(i));
    this.multiplyInPlace(i, copy).subtractReversedInPlace(i, 1.0).sqrtInPlace(i);
    this.addInPlace(i, copy.multiplyInPlace(Complex.I));
    this.logInPlace(i);
    return this.multiplyInPlace(i, Complex.I).negateInPlace(i);
  }
  /**
     Calculate the inverse tangent of the value of the buffer
     and store the result in this buffer

     @param i The array index
     @return this
   */
  public ComplexBufferArray atanInPlace(int i)
  {
    // Generates one object of garbage
    // Could be optimized a lot, eg. mul/add/sub with Complex.I
    final ComplexBuffer copy = new ComplexBuffer(getReal(i), getImag(i));
    this.addInPlace(i, Complex.I);
    this.divideInPlace(i, copy.subtractReversedInPlace(Complex.I));
    this.logInPlace(i);
    return this.multiplyInPlace(i, 0.5).multiplyInPlace(i, Complex.I);
  }
  /**
     Calculate the cosine of the value of the buffer
     and store the result in this buffer

     @param i The array index
     @return this
   */
  public ComplexBufferArray cosInPlace(int i)
  {
    return this.set(i, Math.cos(getReal(i)) * Math.cosh(getImag(i)),
                      -Math.sin(getReal(i)) * Math.sinh(getImag(i)));
  }
  /**
     Calculate the sine of the value of the buffer
     and store the result in this buffer

     @param i The array index
     @return this
   */
  public ComplexBufferArray sinInPlace(int i)
  {
    return this.set(i, Math.sin(getReal(i)) * Math.cosh(getImag(i)),
                       Math.cos(getReal(i)) * Math.sinh(getImag(i)));
  }
  /**
     Calculate the tangent of the value of the buffer
     and store the result in this buffer

     @param i The array index
     @return this
   */
  public ComplexBufferArray tanInPlace(int i)
  {
    final double real_x2 = this.getReal(i) * 2;
    final double imag_x2 = this.getImag(i) * 2;
    final double d = Math.cos(real_x2) + Math.cosh(imag_x2);
    return this.set(i, Math.sin(real_x2)/d, Math.sinh(imag_x2)/d);
  }
  /**
     Calculate the hyperbolic cosine of the value of the buffer
     and store the result in this buffer

     @param i The array index
     @return this
   */
  public ComplexBufferArray coshInPlace(int i)
  {
    return this.set(i, Math.cosh(getReal(i)) * Math.cos(getImag(i)),
                       Math.sinh(getReal(i)) * Math.sin(getImag(i)));

  }
  /**
     Calculate the hyperbolic sine of the value of the buffer
     and store the result in this buffer

     @param i The array index
     @return this
   */
  public ComplexBufferArray sinhInPlace(int i)
  {
    return this.set(i, Math.sinh(getReal(i)) * Math.cos(getImag(i)),
                       Math.cosh(getReal(i)) * Math.sin(getImag(i)));
  }
  /**
     Calculate the hyperbolic tangent of the value of the buffer
     and store the result in this buffer

     @param i The array index
     @return this
   */
  public ComplexBufferArray tanhInPlace(int i)
  {
    final double real_x2 = this.getReal(i) * 2;
    final double imag_x2 = this.getImag(i) * 2;
    final double d = Math.cosh(real_x2) + Math.cos(imag_x2);
    return this.set(i, Math.sinh(real_x2)/d, Math.sin(imag_x2)/d);
  }
  /**
     Raise this complex number to a real power
     and store the result in this buffer

     @param i The array index
     @param b The real power

     @return this
   */
  public ComplexBufferArray powInPlace(int i, double b)
  {
    return this.logInPlace(i).multiplyInPlace(i, b).expInPlace(i);
  }
  /**
     Raise this complex number to a complex power
     and store the result in this buffer

     @param i The array index
     @param b The complex power

     @return this
   */
  public ComplexBufferArray powInPlace(int i, ComplexNumber b)
  {
    if (b == this)
    {
      b = new Complex(b); // freeze b to make it work if b == this
    }
    return this.logInPlace(i).multiplyInPlace(i, b).expInPlace(i);
  }
  /**
     Raise this complex number to a complex power
     and store the result in this buffer

     @param i The array index
     @param b The complex power array
     @param j The array index to the other array

     @return this
   */
  public ComplexBufferArray powInPlace(int i, ComplexNumberArray b, int j)
  {
    if (b == this && i == j)
    {
      Complex frozen = new Complex(b.getReal(j), b.getImag(j));
      return this.logInPlace(i).multiplyInPlace(i, frozen).expInPlace(i);
    }
    return this.logInPlace(i).multiplyInPlace(i, b, j).expInPlace(i);
  }
  /**
     Multiply the value of this complex buffer by another complex number
     and store the result in this buffer

     @param i The array index
     @param c The other complex number

     @return this
   */
  public ComplexBufferArray multiplyInPlace(int i, ComplexNumber c)
  {
    double this_re = this.getReal(i), this_im = this.getImag(i);
    double that_re = c.getReal(), that_im = c.getImag();
    return this.set(i, this_re*that_re - this_im*that_im,
                       this_im*that_re + this_re*that_im);
  }
  /**
     Multiply the value of this complex buffer by another complex number
     and store the result in this buffer

     @param i The array index
     @param c The other complex number array
     @param j The array index to the other array

     @return this
   */
  public ComplexBufferArray multiplyInPlace(int i, ComplexNumberArray c, int j)
  {
    double this_re = this.getReal(i), this_im = this.getImag(i);
    double that_re = c.getReal(j), that_im = c.getImag(j);
    return this.set(i, this_re*that_re - this_im*that_im,
                       this_im*that_re + this_re*that_im);
  }
  /**
     Multiply the value of this complex buffer by a real number
     and store the result in this buffer

     @param i The array index
     @param d The real number

     @return this
   */
  public ComplexBufferArray multiplyInPlace(int i, double d)
  {
    return this.set(i, this.getReal(i) * d, this.getImag(i) * d);
  }
  /**
     Multiply the value of this complex buffer by an integer
     and store the result in this buffer

     @param i The array index
     @param x The integer

     @return this
   */
  public ComplexBufferArray multiplyInPlace(int i, int x)
  {
    return this.set(i, this.getReal(i) * x, this.getImag(i) * x);
  }
  /**
     Divide the value of this complex buffer by another complex number
     and store the result in this buffer

     @param i The array index
     @param c The other complex number

     @return this
   */
  public ComplexBufferArray divideInPlace(int i, ComplexNumber c)
  {
    final double this_re = this.getReal(i), this_im = this.getImag(i);
    final double c_re = c.getReal(), c_im = c.getImag();
    if (Math.abs(c_re) > Math.abs(c_im))
    {
      final double c_im_div_re = c_im/c_re;
      final double w = 1.0 / (c_re + c_im*c_im_div_re);
      return this.set(i, (this_re + this_im*c_im_div_re) * w,
                         (this_im - this_re*c_im_div_re) * w);
    }
    else
    {
      final double c_re_div_im = c_re/c_im;
      final double w = 1.0 / (c_im + c_re*c_re_div_im);
      return this.set(i, (this_re*c_re_div_im + this_im) * w,
                         (this_im*c_re_div_im - this_re) * w);
    }
  }
  /**
     Divide the value of this complex buffer by another complex number
     and store the result in this buffer

     @param i The array index
     @param c The other complex number array
     @param j The array index to the other array

     @return this
   */
  public ComplexBufferArray divideInPlace(int i, ComplexNumberArray c, int j)
  {
    final double this_re = this.getReal(i), this_im = this.getImag(i);
    final double c_re = c.getReal(j), c_im = c.getImag(j);
    if (Math.abs(c_re) > Math.abs(c_im))
    {
      final double c_im_div_re = c_im/c_re;
      final double w = 1.0 / (c_re + c_im*c_im_div_re);
      return this.set(i, (this_re + this_im*c_im_div_re) * w,
                         (this_im - this_re*c_im_div_re) * w);
    }
    else
    {
      final double c_re_div_im = c_re/c_im;
      final double w = 1.0 / (c_im + c_re*c_re_div_im);
      return this.set(i, (this_re*c_re_div_im + this_im) * w,
                         (this_im*c_re_div_im - this_re) * w);
    }
  }
  /**
     Divide the value of this complex buffer by a real number
     and store the result in this buffer

     @param i The array index
     @param d The real number

     @return this
   */
  public ComplexBufferArray divideInPlace(int i, double d)
  {
    return this.set(i, this.getReal(i) / d, this.getImag(i) / d);
  }
  /**
     Divide another complex number by the value of this complex buffer
     and store the result in this buffer

     @param i The array index
     @param c The other complex number

     @return this
   */
  public ComplexBufferArray divideReversedInPlace(int i, ComplexNumber c)
  {
    final double c_re = c.getReal(), c_im = c.getImag();
    final double this_re = this.getReal(i), this_im = this.getImag(i);
    if (Math.abs(this_re) > Math.abs(this_im))
    {
      final double this_im_div_re = this_im/this_re;
      final double w = 1.0 / (this_re + this_im*this_im_div_re);
      return this.set(i, (c_re + c_im*this_im_div_re) * w,
                         (c_im - c_re*this_im_div_re) * w);
    }
    else
    {
      final double this_re_div_im = this_re/this_im;
      final double w = 1.0 / (this_im + this_re*this_re_div_im);
      return this.set(i, (c_re*this_re_div_im + c_im) * w,
                         (c_im*this_re_div_im - c_re) * w);
    }
  }
  /**
     Divide another complex number by the value of this complex buffer
     and store the result in this buffer

     @param i The array index
     @param c The other complex number array
     @param j The array index to the other array

     @return this
   */
  public ComplexBufferArray divideReversedInPlace(int i, ComplexNumberArray c, int j)
  {
    final double c_re = c.getReal(j), c_im = c.getImag(j);
    final double this_re = this.getReal(i), this_im = this.getImag(i);
    if (Math.abs(this_re) > Math.abs(this_im))
    {
      final double this_im_div_re = this_im/this_re;
      final double w = 1.0 / (this_re + this_im*this_im_div_re);
      return this.set(i, (c_re + c_im*this_im_div_re) * w,
                         (c_im - c_re*this_im_div_re) * w);
    }
    else
    {
      final double this_re_div_im = this_re/this_im;
      final double w = 1.0 / (this_im + this_re*this_re_div_im);
      return this.set(i, (c_re*this_re_div_im + c_im) * w,
                         (c_im*this_re_div_im - c_re) * w);
    }
  }
  /**
     Divide a real number by the value of this complex buffer
     and store the result in this buffer

     @param i The array index
     @param d The real number

     @return this
   */
  public ComplexBufferArray divideReversedInPlace(int i, double d)
  {
    final double old_re = this.getReal(i);
    final double old_im = this.getImag(i);
    if (Math.abs(old_re) > Math.abs(old_im))
    {
      final double im_div_re = old_im/old_re;
      final double w = d / (old_re + old_im*im_div_re);
      return this.set(i, w, -im_div_re * w);
    }
    else
    {
      final double re_div_im = old_re/old_im;
      final double w = d / (old_im + old_re*re_div_im);
      return this.set(i, re_div_im * w, -w);
    }
  }

  /**
     Calculate the absolute value of the complex number in this complex buffer.

     @param i The array index
     @return x&ge;0 The absolute value
   */
  public double abs(int i)
  {
    return ComplexUtils.abs(this, i);
  }
  /**
     Calculate the argument of the complex number in this complex buffer.

     The argument is the angle between the positive real axis and the point
     that represents this number in the complex plane.

     @param i The array index
     @return -pi&le;x&le;pi The argument
   */
  public double arg(int i)
  {
    return ComplexUtils.arg(this, i);
  }
  /**
     Check whether the complex number in this buffer is NaN (not-a-numer).

     A complex number is considered NaN if either the real or the imaginary
     part is NaN.

     @param i The array index
     @return Whether the complex number in this buffer is NaN
   */
  public boolean isNaN(int i)
  {
    return ComplexUtils.isNaN(this, i);
  }
  /**
     Check whether the complex number in this buffer is infinite.

     A complex number is considered infinite if either the real or the
     imaginary part is infinite. If either the real of imaginary part
     is NaN, the number is not considered infinite, so isNaN() and
     isInfinite() cannot be true at the same time.

     @param i The array index
     @return Whether the complex number in this buffer is infinite
   */
  public boolean isInfinite(int i)
  {
    return ComplexUtils.isInfinite(this, i);
  }
  /**
     Returns a String representation of the complex number in this complex
     buffer.
    
     @param i The array index
     @return "NaN" if NaN<br/>
             re if purely real<br/>
             im + "i" if purely imaginary<br/>
             re " + " + im + "i" if imaginary part positive<br/>
             re " - " + (-im) + "i" if imaginary part negative
   */
  public String toString(int i)
  {
    return ComplexUtils.toString(this, i);
  }

};
