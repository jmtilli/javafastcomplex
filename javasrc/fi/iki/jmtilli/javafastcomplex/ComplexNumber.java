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
   A common interface to complex numbers.
 */
public interface ComplexNumber {
  /**
     Returns the real part of the complex number.

     @return The real part
   */
  double getReal();
  /**
     Returns the real part of the complex number.

     @return The imaginary part
   */
  double getImag();
  /**
     Returns the absolute value of the complex number.
    
     @return Double.NaN if isNaN()<br/>
             Double.POSITIVE_INFINITY if isInfinite()<br/>
             x&ge;0 absolute value otherwise
   */
  double abs();
  /**
     Returns the argument of the complex number.
    
     @return Double.NaN if isNaN()<br/>
             -pi&le;x&le;pi argument otherwise
   */
  double arg();
  /**
     Checks whether the complex number is NaN.
    
     @return true if either the real or imaginary part is NaN<br/>
             false otherwise
   */
  boolean isNaN();
  /**
     Checks whether the complex number is infinite.

     @return true if the real or imaginary part is infinite and !isNaN()<br/>
             false otherwise
   */
  boolean isInfinite();
  /**
     Returns a String representation of the complex number.

     @return "NaN" if NaN<br/>
             re if purely real<br/>
             im + "i" if purely imaginary<br/>
             re " + " + im + "i" if imaginary part positive<br/>
             re " - " + (-im) + "i" if imaginary part negative<br/>
   */
  String toString();
};
