pallet-loading-problem
=======================

In this work, we deal with the problem of packing (orthogonally and without overlapping) identical rectangles in a rectangle.

We use a recursive partitioning approach for the packing of different rectangles in a rectangle[1][2].

[1] R.Morabito and S.Morales, A simple and effective recursive proceduce for the manufacturer's pallet loading problem, *Journal of the Operational Research Society* 49,pp.819-828, 1998

[2] R.Morabito and S.Morales, A simple and effective recursive proceduce for the manufacturer's pallet loading problem (49, pp.819-828, 1998), *Journal of the Operational Research Society 49,pp* 50, pp.819-828, 1998

## Rules

 - To minimize the space of a rectangle area, choose some rectangles from a set of rectangles prepared at random (described below) and locate them on the frame.
 - A rectangle is usable only once.
 - A rectangle is able to rotate 90 degrees.
 - Do not overlap the other rectangles.
 - Do not protude from the outer frame.
 - Use these rectangles as a filling. (id, length, width)

        a 42 18
        b 35 1
        c 20 25
        d 29 9
        e 13 15
        f 6 46
        g 32 28
        h 12 42
        i 46 43
        j 28 37
        k 42 5
        l 3 4
        m 43 33
        n 22 17
        o 19 46
        p 48 27
        q 22 39
        r 20 13
        s 18 50
        t 36 45
        u 4 12
        v 23 34
        w 24 15
        x 42 12
        y 4 19
        z 48 45
        A 13 8
        B 38 10
        C 24 42
        D 30 29
        E 17 36
        F 41 43
        G 39 7
        H 41 43
        I 15 49
        J 47 6
        K 41 30
        L 21 1
        M 7 2
        N 44 49
        O 30 24
        P 35 5
        Q 7 41
        R 17 27
        S 32 9
        T 45 40
        U 27 24
        V 38 39
        W 19 33
        X 30 42
        Y 34 16
        Z 40 9

### Question.1
How much is the area of the space if the size of the outer frame is 50 x 50 ? 

### Question.2
How much is the area of the space if the size of the outer frame is 200 x 100 ?

## Results

### Environment

Intel(R) Core(TM)2 Quad CPU Q6700 @ 2.66GHz

Linux 3.2.0-27-generic-pae #43-Ubuntu SMP Fri Jul 6 15:06:05 UTC 2012 i686 i686 i386 GNU/Linux

Scala version 2.9.2 (Java HotSpot(TM) 64-Bit Server VM, Java 1.6.0_26)

Akka-actor 2.0.1

### Answer.1
from sbt prompt:

    > run 50 50 10000

The area of space is 7 . (ratio=0.28%)

![blank=7 W,y,r,e,A,Q,d,M,U,L](https://raw.github.com/tono-nakae/pallet-loading-problem/master/Answer1.png)

### Answer.2

from sbt prompt:

    > run 200 100 60000

The area of space is 700 . (ratio=3.50%)

![blank=700 N,i,a,H,L,z,x,l,T,V,t,P,j,b,Z,D,R,M,d,Y,I,r,A,e](https://raw.github.com/tono-nakae/pallet-loading-problem/master/Answer2.png)

### Answer.2 (over 1 min.)

The area of space is 484 . (ratio=2.42%)

![blank=484 N,i,a,H,L,z,q,M,y,X,K,D,r,A,O,d,T,B,f,J,c,b,e,u,o,W,l](https://raw.github.com/tono-nakae/pallet-loading-problem/master/Answer2-best.png)

## Conclusion

The implementation was able to demand a result faster by making processing parallel.
Scala & Akka-actor contributes to this result.

As a problem point left unfinished, the algorithm based on local
search optimization approach is frequently stuck at a locally optimal
point during long time. Then tasks should be shuffled with more
complex schemes based on iterations or like iterated local search,
like reactive search optimization, like simulated annealing.
