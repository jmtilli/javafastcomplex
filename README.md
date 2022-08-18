# Javafastcomplex: a fast Java complex number package

Javafastcomplex implements two complex number classes, `Complex` for immutable
complex numbers and `ComplexBuffer` for mutable complex numbers. Both immutable
and mutable complex numbers share the same common `ComplexNumber` interface.
This is unlike other available complex number classes that typically only offer
an immutable complex number class. Using the mutable complex number class
reduces object creation and garbage collection overhead which allows trivially
to improve the performance of Java code that does complex number calculations.

## Documentation

Javafastcomplex documentations is available at
https://jmtilli.github.io/javafastcomplexjavadoc

## Examples

### Z component of wavevector

Let us consider the expression

```
kz0 = k0*sqrt(alpha0*alpha0 - 2*delta - 2*beta*i)
```

...which is used to calculate an approximation for the z component of
wavevector in X-ray reflectivity. Here `alpha0`, `delta`, `beta` and `k0` are
real numbers, but the imaginary unit is used inside the square root.

If using immutable complex numbers for that:

```
for (...) {
  double alpha0 = get_alpha0();
  double delta = get_delta();
  double beta = get_beta();
  double k0 = get_k0();
  kz0 = new Complex(alpha0*alpha0-2*delta, -2*beta).sqrt().multiply(k0);
}
```

If using mutable complex numbers for that, you do:

```
ComplexBuffer kz0 = new ComplexBuffer();
for (...) {
  double alpha0 = get_alpha0();
  double delta = get_delta();
  double beta = get_beta();
  double k0 = get_k0();
  kz0.set(alpha0*alpha0-2*delta, -2*beta).sqrtInPlace().multiplyInPlace(k0);
}
```

The code first constructs the complex number that occurs inside the square
root. This is assigned to the complex buffer `kz0` as a temporary value. The
assignment function `set` returns a reference to the buffer, so more operations
can be done. The operations to do are to calculate a square root in-place
(which again returns a reference to the buffer), and then to multiply in-place
with `k0`.

In this special example, the code for mutable and immutable operations is about
the same, but sometimes creating an efficient code for mutable operations may
require some amount of careful thinking.

### Parratt's formalism in X-ray reflectivity

Parratt's formalism is calculated as follows:
```
ri = (kz0-kz1)/(kz0+kz1)
roughri = ri*exp(kz0*kz1*roughness_factor)
b = R[angleindex]*exp(-2*i*kz1*d)
R[angleindex] = (b + roughri)/(b*roughri + 1)
```

...where `kz0` and `kz1` are calculated for each (layer, angle) combination.
`roughness_factor` is different for each layer and so is `d`. The last two are
iterated per layer: for each angle, there is `R[angleindex]` for the structure
below a given layer, and then `R[angleindex]` is updated by adding a new layer
over the structure.

Using immutable `Complex` class, we can do this as follows:

```
Complex num;
Complex den;
Complex ri;
Complex b;
Complex roughri;
Complex kz0;
Complex kz1;
Complex R = new Complex(0);
Complex d_times_minus_two_i;
Complex MINUS_TWO_I = new Complex(0, -2);
Complex R[];

for (int i: ... loop over layers ...) {
  double roughness_factor = get_roughness_factor_real(i);
  d_times_minus_two_i = MINUS_TWO_I.multiply(get_d_real(i));
  for (int j: ... loop over angles ...) {
    kz0 = new Complex(get_kz0_real(i,j), get_kz0_imag(i,j));
    kz1 = new Complex(get_kz1_real(i,j), get_kz1_imag(i,j));

    num = kz0.subtract(kz1);
    den = kz0.add(kz1);
    ri = num.divide(den);
    if (ri.isNaN())
    {
      ri = Complex.ZERO;
    }
    roughri = kz0.multiply(kz1).multiply(roughness_factor).exp().multiply(ri);
    b = kz1.multiply(d_times_minus_two_i).exp().multiply(R[j]);
    num = b.add(roughri);
    den = b.multiply(roughri).add(1);
    R[j] = num.divide(den);
  }
}
```

Using mutable `ComplexBuffer` class, we can do this as follows:

```
ComplexBuffer num = new ComplexBuffer();
ComplexBuffer den = new ComplexBuffer();
ComplexBuffer ri = new ComplexBuffer();
ComplexBuffer b = new ComplexBuffer();
ComplexBuffer roughri = new ComplexBuffer();
ComplexBuffer kz0 = new ComplexBuffer();
ComplexBuffer kz1 = new ComplexBuffer();
ComplexBuffer R = new ComplexBuffer();
ComplexBuffer d_times_minus_two_i = new ComplexBuffer();
ComplexBuffer R[];
Complex MINUS_TWO_I = new Complex(0, -2);

for (int i: ... loop over layers ...) {
  double roughness_factor = get_roughness_factor_real(i);
  d_times_minus_two_i.set(MINUS_TWO_I).multiplyInPlace(get_d_real(i));
  for (int j: ... loop over angles ...) {
    kz0.set(get_kz0_real(i,j), get_kz0_imag(i,j));
    kz1.set(get_kz1_real(i,j), get_kz1_imag(i,j));

    num.set(kz0).subtractInPlace(kz1);
    den.set(kz0).addInPlace(kz1);
    ri.set(num).divideInPlace(den);
    if (ri.isNaN())
    {
      ri.set(0,0); // for pathological cases ensure it's not NaN
    }
    roughri.set(kz0).multiplyInPlace(kz1).multiplyInPlace(roughness_factor)
           .expInPlace().multiplyInPlace(ri);
    b.set(kz1).multiplyInPlace(d_times_minus_two_i).expInPlace().multiplyInPlace(R[j]);
    num.set(b).addInPlace(roughri);
    den.set(b).multiplyInPlace(roughri).addInPlace(1);
    R[j].set(num).divideInPlace(den);
  }
}
```


