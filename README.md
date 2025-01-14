Benchmarking several languages/tools with Mandelbrot set generation. 1-to-1 translation of code from one language to another. No SIMD, no multithreading (except prange() trick with Numba), no tricks (e.g. skipping sqrt), a bare minimum of language specific adjustments to make the code nicer while keeping all loops and operations in place.

By default using int32 and float64 (where possible, i.e. in Dart all integers are 64 bit, Go comes with 128 bit Complex struct). If there's common/standar library to work with complex numbers it is used. Otherwise a custom `Complex` class/structure/type is recreated with 3 operstions required by the algorithm.

Sum check column shows the result of calculating the total sum of numbers in the array produced by `mandelbrot()` method. It demonstrates how there can be slight variations (+/-0,1%) in different languages and compiler settings due to differences in floating point math utilized.

Ubuntu 22.04.3 LTS, 64 bit, Intel Core i5-8257U @ 1.4GHz x 2, VMWare Workstation Player 17.0.1

| Language/variant          | Time (seconds) | Version | Sum check | Comment       |
|---------------------------|----------------|---------|-----------|---------------|
| Python + custom Complex   | 1672,0         | 3.11.15 | 78513425  |               |
| Python + NumPy            | 10,8           | 3.11.15 | 78513425  |               |
| Python + NP + Numba       | 0,68           | 3.11.15 | 78513425  |               |
| Python + Numba (fastmath) | 0,64           | 3.11.15 | 78513473  | Different sum |
| Python + Numba (prange)   | 0,38           | 3.11.15 | 78513425  | Parallel/MT   |
| Python + without NumPy    | 9,8            | 3.11.15 | 78513425  |               |
| Python + w/o NP + Numba   | 0,29           | 3.11.15 | 78513473  | Diff, fastmth |
| Python+w/o NP+Numba(prng) | 0,19           | 3.11.15 | 78513473  | Diff, fst, prl|
| Ruby                      | 28,5           | 3.0.2   | 79273394  | Different sum |
| Pascal                    | 0,93           | 3.2.2   | 78801988  | Free pascal   |
| JavaScript +cstcomp (Bun) | 0,90           | 1.0.3   | 78513425  |               |
| JavaScript +cstcomp (Node)| 0,82           | 12.22.9 | 78513425  |               |
| Go + complex128           | 0,54           | 1.21.1  | 78513415  | Different sum |
| Go + cust Complex64       | 0,35           | 1.21.1  | 78513415  | Different sum |
| Dart + cust Complex (JIT) | 0,64           | 3.1.0   | 78513425  |               |
| Dart + cust Complex (AoT) | 0,42           | 3.1.0   | 78513425  |               |
| Dart + cstcomp + JS + node| 0,75           | 3.1.0   | 78513425  |               |
| Dart + cstcomp + JS + bun | 0,84           | 3.1.0   | 78513425  |               |
| Rust + num-complex        | 0,73           | 1.72.1  | 78513425  |               |
| Rust + custom Complex     | 0,32           | 1.72.1  | 78513425  |               |
| Java + custom Complex     | 0,5            | openjdk | 78513425  | 11.0.20.1     |
| Kotlin + custom Complex   | 0,6            | 1.9.0   | 78513425  | JRE 11.0.20.1 |
| C# + custom Complex (JIT) | 0,37           | 7.0.111 | 78513425  |               |
| C# + custom Complex (AoT) | 0,33           | 7.0.111 | 78513425  |               |
| Mojo                      | 0,32           | 0.2.1   | 78513383  | Different sum |
| gcc                       | 0,69           | 11.4.0  | 78513478  | Different sum |
| gcc (-O3)                 | 0,32           | 11.4.0  | 78513478  | Different sum |
| gcc (-Ofast)              | 0,29           | 11.4.0  | 78513478  | Different sum |

More on Python and Mojo: https://dev.to/maximsaplin/mojo-head-to-head-with-python-and-numba-5bn9
