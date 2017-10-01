package my.algorithm.prime;

// Elliptic Curve Method (ECM) Prime Factorization
//
// Written by Dario Alejandro Alpern (Buenos Aires - Argentina)
// Last updated September 10th, 2011. See http://www.alpertron.com.ar/ECM.HTM
//
// Based in Yuji Kida's implementation for UBASIC interpreter
//
// No part of this code can be used for commercial purposes without
// the written consent from the author. Otherwise it can be used freely
// except that you have to write somewhere in the code this header.
//


import java.math.BigInteger;

public class Ecm {

    static final BigInteger BigInt0 = BigInteger.valueOf(0L);
    static final BigInteger BigInt1 = BigInteger.valueOf(1L);
    static final BigInteger BigInt2 = BigInteger.valueOf(2L);
    static final BigInteger BigInt3 = BigInteger.valueOf(3L);
    private BigInteger probableFactor;

    static final int PWmax = 32, Qmax = 30241, LEVELmax = 11;
    static final int NLen = 1200;
    final int aiIndx[] = new int[Qmax];
    final int aiF[] = new int[Qmax];
    static final int aiP[] = {2, 3, 5, 7, 11, 13};
    static final int aiQ[] =
            {
                    2,
                    3,
                    5,
                    7,
                    13,
                    11,
                    31,
                    61,
                    19,
                    37,
                    181,
                    29,
                    43,
                    71,
                    127,
                    211,
                    421,
                    631,
                    41,
                    73,
                    281,
                    2521,
                    17,
                    113,
                    241,
                    337,
                    1009,
                    109,
                    271,
                    379,
                    433,
                    541,
                    757,
                    2161,
                    7561,
                    15121,
                    23,
                    67,
                    89,
                    199,
                    331,
                    397,
                    463,
                    617,
                    661,
                    881,
                    991,
                    1321,
                    2311,
                    2377,
                    2971,
                    3697,
                    4159,
                    4621,
                    8317,
                    9241,
                    16633,
                    18481,
                    23761,
                    101,
                    151,
                    401,
                    601,
                    701,
                    1051,
                    1201,
                    1801,
                    2801,
                    3301,
                    3851,
                    4201,
                    4951,
                    6301,
                    9901,
                    11551,
                    12601,
                    14851,
                    15401,
                    19801,
                    97,
                    353,
                    673,
                    2017,
                    3169,
                    3361,
                    5281,
                    7393,
                    21601,
                    30241,
                    53,
                    79,
                    131,
                    157,
                    313,
                    521,
                    547,
                    859,
                    911,
                    937,
                    1093,
                    1171,
                    1249,
                    1301,
                    1873,
                    1951,
                    2003,
                    2081,
                    41,
                    2731,
                    2861,
                    3121,
                    3433,
                    3511,
                    5851,
                    6007,
                    6553,
                    7151,
                    7723,
                    8009,
                    8191,
                    8581,
                    8737,
                    9829,
                    11701,
                    13729,
                    14561,
                    15601,
                    16381,
                    17551,
                    20021,
                    20593,
                    21841,
                    24571,
                    25741,
                    26209,
                    28081};
    static final int aiG[] =
            {
                    1,
                    2,
                    2,
                    3,
                    2,
                    2,
                    3,
                    2,
                    2,
                    2,
                    2,
                    2,
                    3,
                    7,
                    3,
                    2,
                    2,
                    3,
                    6,
                    5,
                    3,
                    17,
                    3,
                    3,
                    7,
                    10,
                    11,
                    6,
                    6,
                    2,
                    5,
                    2,
                    2,
                    23,
                    13,
                    11,
                    5,
                    2,
                    3,
                    3,
                    3,
                    5,
                    3,
                    3,
                    2,
                    3,
                    6,
                    13,
                    3,
                    5,
                    10,
                    5,
                    3,
                    2,
                    6,
                    13,
                    15,
                    13,
                    7,
                    2,
                    6,
                    3,
                    7,
                    2,
                    7,
                    11,
                    11,
                    3,
                    6,
                    2,
                    11,
                    6,
                    10,
                    2,
                    7,
                    11,
                    2,
                    6,
                    13,
                    5,
                    3,
                    5,
                    5,
                    7,
                    22,
                    7,
                    5,
                    7,
                    11,
                    2,
                    3,
                    2,
                    5,
                    10,
                    3,
                    2,
                    2,
                    17,
                    5,
                    5,
                    2,
                    7,
                    2,
                    10,
                    3,
                    5,
                    3,
                    7,
                    3,
                    2,
                    7,
                    5,
                    7,
                    2,
                    3,
                    10,
                    7,
                    3,
                    3,
                    17,
                    6,
                    5,
                    10,
                    6,
                    23,
                    6,
                    23,
                    2,
                    3,
                    3,
                    5,
                    11,
                    7,
                    6,
                    11,
                    19};
    final int aiInv[] = new int[PWmax];
    static final int aiNP[] = {2, 3, 3, 4, 4, 4, 4, 5, 5, 5, 6};
    static final int aiNQ[] = {5, 8, 11, 18, 22, 27, 36, 59, 79, 89, 136};
    static final int aiT[] =
            {
                    2 * 2 * 3,
                    2 * 2 * 3 * 5,
                    2 * 2 * 3 * 3 * 5,
                    2 * 2 * 3 * 3 * 5 * 7,
                    2 * 2 * 2 * 3 * 3 * 5 * 7,
                    2 * 2 * 2 * 2 * 3 * 3 * 5 * 7,
                    2 * 2 * 2 * 2 * 3 * 3 * 3 * 5 * 7,
                    2 * 2 * 2 * 2 * 3 * 3 * 3 * 5 * 7 * 11,
                    2 * 2 * 2 * 2 * 3 * 3 * 3 * 5 * 5 * 7 * 11,
                    2 * 2 * 2 * 2 * 2 * 3 * 3 * 3 * 5 * 5 * 7 * 11,
                    2 * 2 * 2 * 2 * 2 * 3 * 3 * 3 * 5 * 5 * 7 * 11 * 13};
    final int biTmp[] = new int[NLen];
    final int biExp[] = new int[NLen];
    final int biN[] = new int[NLen];
    final int biR[] = new int[NLen];
    final int biS[] = new int[NLen];
    final int biT[] = new int[NLen];
    final int biU[] = new int[NLen]; /* Temp */
    final int biV[] = new int[NLen]; /* Temp */
    final int biW[] = new int[NLen]; /* Temp */
    final int aiJS[][] = new int[PWmax][NLen];
    final int aiJW[][] = new int[PWmax][NLen];
    final int aiJX[][] = new int[PWmax][NLen];
    final int aiJ0[][] = new int[PWmax][NLen];
    final int aiJ1[][] = new int[PWmax][NLen];
    final int aiJ2[][] = new int[PWmax][NLen];
    final int aiJ00[][] = new int[PWmax][NLen];
    final int aiJ01[][] = new int[PWmax][NLen];
    int NumberLength;
    final int CalcAuxGcdU[] = new int[NLen];
    final int CalcAuxGcdV[] = new int[NLen];
    final int CalcAuxGcdT[] = new int[NLen];

    int TestNbr[] = new int[NLen];

    static final long DosALa32 = (long) 1 << 32;
    static final long DosALa31 = (long) 1 << 31;

    static final double dDosALa31 = (double) DosALa31;
    static final double dDosALa62 = dDosALa31 * dDosALa31;
    static final long Mi = 1000000000;

    double dN;
    final int BigNbr1[] = new int[NLen];

    final int MontgomeryMultR1[] = new int[NLen];
    final int MontgomeryMultR2[] = new int[NLen];
    final int MontgomeryMultAfterInv[] = new int[NLen];
    long MontgomeryMultN;
    long OldTimeElapsed;
    long Old;
    long yieldFreq;
    private long lModularMult;


    // Prime checking routine
    // Return codes: 0 = Number is prime.
    //               1 = Number is composite.
    public int aprtCle(BigInteger N) {
        int i, j, G, H, I, J, K, P, Q, T, U, W, X;
        int IV, InvX, LEVELnow, NP, PK, PL, PM, SW, VK, TestedQs, TestingQs;
        int QQ, T1, T3, U1, U3, V1, V3;
        int LengthN, LengthS;
        long Mask;
        double dS;

        NumberLength = BigNbrToBigInt(N, TestNbr);
        GetYieldFrequency();
        GetMontgomeryParms();

        j = 0;
        for (I = 0; I < NumberLength; I++) {
            biS[I] = 0;
            for (J = 0; J < PWmax; J++) {
                aiJX[J][I] = 0;
            }
        }
        GetPrimes2Test:
        for (i = 0; i < LEVELmax; i++) {
            biS[0] = 2;
            for (I = 1; I < NumberLength; I++) {
                biS[I] = 0;
            }
            for (j = 0; j < aiNQ[i]; j++) {
                Q = aiQ[j];
                U = aiT[i] * Q;
                do {
                    U /= Q;
                    MultBigNbrByLong(biS, Q, biS, NumberLength);
                }
                while (U % Q == 0);

                // Exit loop if S^2 > N.

                if (CompareSquare(biS, TestNbr) > 0) {
                    break GetPrimes2Test;
                }
            } /* End for j */
        } /* End for i */
        if (i == LEVELmax) { /* too big */
            return ProbabilisticPrimeTest(N);
        }
        LEVELnow = i;
        TestingQs = j;
        T = aiT[LEVELnow];
        NP = aiNP[LEVELnow];

        MainStart:
        for (; ; ) {
            for (i = 0; i < NP; i++) {
                P = aiP[i];
                SW = TestedQs = 0;
                Q = W = (int) BigNbrModLong(TestNbr, P * P);
                for (J = P - 2; J > 0; J--) {
                    W = (W * Q) % (P * P);
                }
                if (P > 2 && W != 1) {
                    SW = 1;
                }
                for (; ; ) {
                    for (j = TestedQs; j <= TestingQs; j++) {
                        Q = aiQ[j] - 1;
                        G = aiG[j];
                        K = 0;
                        while (Q % P == 0) {
                            K++;
                            Q /= P;
                        }
                        Q = aiQ[j];
                        if (K == 0) {
                            continue;
                        }

                        PM = 1;
                        for (I = 1; I < K; I++) {
                            PM = PM * P;
                        }
                        PL = (P - 1) * PM;
                        PK = P * PM;
                        J = 1;
                        for (I = 1; I < Q; I++) {
                            J = J * G % Q;
                            aiIndx[J] = I;
                        }
                        J = 1;
                        for (I = 1; I <= Q - 2; I++) {
                            J = J * G % Q;
                            aiF[I] = aiIndx[(Q + 1 - J) % Q];
                        }
                        for (I = 0; I < PK; I++) {
                            for (J = 0; J < NumberLength; J++) {
                                aiJ0[I][J] = aiJ1[I][J] = 0;
                            }
                        }
                        if (P > 2) {
                            JacobiSum(1, 1, P, PK, PL, PM, Q);
                        } else {
                            if (K != 1) {
                                JacobiSum(1, 1, P, PK, PL, PM, Q);
                                for (I = 0; I < PK; I++) {
                                    for (J = 0; J < NumberLength; J++) {
                                        aiJW[I][J] = 0;
                                    }
                                }
                                if (K != 2) {
                                    for (I = 0; I < PM; I++) {
                                        for (J = 0; J < NumberLength; J++) {
                                            aiJW[I][J] = aiJ0[I][J];
                                        }
                                    }
                                    JacobiSum(2, 1, P, PK, PL, PM, Q);
                                    for (I = 0; I < PM; I++) {
                                        for (J = 0; J < NumberLength; J++) {
                                            aiJS[I][J] = aiJ0[I][J];
                                        }
                                    }
                                    JS_JW(PK, PL, PM, P);
                                    NormalizeJS(PK, PL, PM, P);
                                    for (I = 0; I < PM; I++) {
                                        for (J = 0; J < NumberLength; J++) {
                                            aiJ1[I][J] = aiJS[I][J];
                                        }
                                    }
                                    JacobiSum(3 << (K - 3), 1 << (K - 3), P, PK, PL, PM, Q);
                                    for (J = 0; J < NumberLength; J++) {
                                        for (I = 0; I < PK; I++) {
                                            aiJW[I][J] = 0;
                                        }
                                        for (I = 0; I < PM; I++) {
                                            aiJS[I][J] = aiJ0[I][J];
                                        }
                                    }
                                    JS_2(PK, PL, PM, P);
                                    NormalizeJS(PK, PL, PM, P);
                                    for (I = 0; I < PM; I++) {
                                        for (J = 0; J < NumberLength; J++) {
                                            aiJ2[I][J] = aiJS[I][J];
                                        }
                                    }
                                }
                            }
                        }
                        for (J = 0; J < NumberLength; J++) {
                            aiJ00[0][J] = aiJ01[0][J] = MontgomeryMultR1[J];
                            for (I = 1; I < PK; I++) {
                                aiJ00[I][J] = aiJ01[I][J] = 0;
                            }
                        }
                        VK = (int) BigNbrModLong(TestNbr, PK);
                        for (I = 1; I < PK; I++) {
                            if (I % P != 0) {
                                U1 = 1;
                                U3 = I;
                                V1 = 0;
                                V3 = PK;
                                while (V3 != 0) {
                                    QQ = U3 / V3;
                                    T1 = U1 - V1 * QQ;
                                    T3 = U3 - V3 * QQ;
                                    U1 = V1;
                                    U3 = V3;
                                    V1 = T1;
                                    V3 = T3;
                                }
                                aiInv[I] = (U1 + PK) % PK;
                            } else {
                                aiInv[I] = 0;
                            }
                        }
                        if (P != 2) {
                            for (IV = 0; IV <= 1; IV++) {
                                for (X = 1; X < PK; X++) {
                                    for (I = 0; I < PK; I++) {
                                        for (J = 0; J < NumberLength; J++) {
                                            aiJS[I][J] = aiJ0[I][J];
                                        }
                                    }
                                    if (X % P == 0) {
                                        continue;
                                    }
                                    if (IV == 0) {
                                        LongToBigNbr(X, biExp, NumberLength);
                                    } else {
                                        LongToBigNbr(VK * X / PK, biExp, NumberLength);
                                        if (VK * X / PK == 0) {
                                            continue;
                                        }
                                    }
                                    JS_E(PK, PL, PM, P);
                                    for (I = 0; I < PK; I++) {
                                        for (J = 0; J < NumberLength; J++) {
                                            aiJW[I][J] = 0;
                                        }
                                    }
                                    InvX = aiInv[X];
                                    for (I = 0; I < PK; I++) {
                                        J = I * InvX % PK;
                                        AddBigNbrModN(aiJW[J], aiJS[I], aiJW[J], TestNbr,
                                                NumberLength);
                                    }
                                    NormalizeJW(PK, PL, PM, P);
                                    if (IV == 0) {
                                        for (I = 0; I < PK; I++) {
                                            for (J = 0; J < NumberLength; J++) {
                                                aiJS[I][J] = aiJ00[I][J];
                                            }
                                        }
                                    } else {
                                        for (I = 0; I < PK; I++) {
                                            for (J = 0; J < NumberLength; J++) {
                                                aiJS[I][J] = aiJ01[I][J];
                                            }
                                        }
                                    }
                                    JS_JW(PK, PL, PM, P);
                                    if (IV == 0) {
                                        for (I = 0; I < PK; I++) {
                                            for (J = 0; J < NumberLength; J++) {
                                                aiJ00[I][J] = aiJS[I][J];
                                            }
                                        }
                                    } else {
                                        for (I = 0; I < PK; I++) {
                                            for (J = 0; J < NumberLength; J++) {
                                                aiJ01[I][J] = aiJS[I][J];
                                            }
                                        }
                                    }
                                } /* end for X */
                            } /* end for IV */
                        } else {
                            if (K == 1) {
                                MultBigNbrByLongModN(MontgomeryMultR1, Q, aiJ00[0],
                                        TestNbr, NumberLength);
                                for (J = 0; J < NumberLength; J++) {
                                    aiJ01[0][J] = MontgomeryMultR1[J];
                                }
                            } else {
                                if (K == 2) {
                                    if (VK == 1) {
                                        for (J = 0; J < NumberLength; J++) {
                                            aiJ01[0][J] = MontgomeryMultR1[J];
                                        }
                                    }
                                    for (J = 0; J < NumberLength; J++) {
                                        aiJS[0][J] = aiJ0[0][J];
                                        aiJS[1][J] = aiJ0[1][J];
                                    }
                                    JS_2(PK, PL, PM, P);
                                    if (VK == 3) {
                                        for (J = 0; J < NumberLength; J++) {
                                            aiJ01[0][J] = aiJS[0][J];
                                            aiJ01[1][J] = aiJS[1][J];
                                        }
                                    }
                                    MultBigNbrByLongModN(aiJS[0], Q, aiJ00[0], TestNbr,
                                            NumberLength);
                                    MultBigNbrByLongModN(aiJS[1], Q, aiJ00[1], TestNbr,
                                            NumberLength);
                                } else {
                                    for (IV = 0; IV <= 1; IV++) {
                                        for (X = 1; X < PK; X += 2) {
                                            for (I = 0; I <= PM; I++) {
                                                for (J = 0; J < NumberLength; J++) {
                                                    aiJS[I][J] = aiJ1[I][J];
                                                }
                                            }
                                            if (X % 8 == 5 || X % 8 == 7) {
                                                continue;
                                            }
                                            if (IV == 0) {
                                                LongToBigNbr(X, biExp, NumberLength);
                                            } else {
                                                LongToBigNbr(VK * X / PK, biExp, NumberLength);
                                                if (VK * X / PK == 0) {
                                                    continue;
                                                }
                                            }
                                            JS_E(PK, PL, PM, P);
                                            for (I = 0; I < PK; I++) {
                                                for (J = 0; J < NumberLength; J++) {
                                                    aiJW[I][J] = 0;
                                                }
                                            }
                                            InvX = aiInv[X];
                                            for (I = 0; I < PK; I++) {
                                                J = I * InvX % PK;
                                                AddBigNbrModN(aiJW[J], aiJS[I], aiJW[J], TestNbr,
                                                        NumberLength);
                                            }
                                            NormalizeJW(PK, PL, PM, P);
                                            if (IV == 0) {
                                                for (I = 0; I < PK; I++) {
                                                    for (J = 0; J < NumberLength; J++) {
                                                        aiJS[I][J] = aiJ00[I][J];
                                                    }
                                                }
                                            } else {
                                                for (I = 0; I < PK; I++) {
                                                    for (J = 0; J < NumberLength; J++) {
                                                        aiJS[I][J] = aiJ01[I][J];
                                                    }
                                                }
                                            }
                                            NormalizeJS(PK, PL, PM, P);
                                            JS_JW(PK, PL, PM, P);
                                            if (IV == 0) {
                                                for (I = 0; I < PK; I++) {
                                                    for (J = 0; J < NumberLength; J++) {
                                                        aiJ00[I][J] = aiJS[I][J];
                                                    }
                                                }
                                            } else {
                                                for (I = 0; I < PK; I++) {
                                                    for (J = 0; J < NumberLength; J++) {
                                                        aiJ01[I][J] = aiJS[I][J];
                                                    }
                                                }
                                            }
                                        } /* end for X */
                                        if (IV == 0 || VK % 8 == 1 || VK % 8 == 3) {
                                            continue;
                                        }
                                        for (I = 0; I < PM; I++) {
                                            for (J = 0; J < NumberLength; J++) {
                                                aiJW[I][J] = aiJ2[I][J];
                                                aiJS[I][J] = aiJ01[I][J];
                                            }
                                        }
                                        for (; I < PK; I++) {
                                            for (J = 0; J < NumberLength; J++) {
                                                aiJW[I][J] = aiJS[I][J] = 0;
                                            }
                                        }
                                        JS_JW(PK, PL, PM, P);
                                        for (I = 0; I < PM; I++) {
                                            for (J = 0; J < NumberLength; J++) {
                                                aiJ01[I][J] = aiJS[I][J];
                                            }
                                        }
                                    } /* end for IV */
                                }
                            }
                        }
                        for (I = 0; I < PL; I++) {
                            for (J = 0; J < NumberLength; J++) {
                                aiJS[I][J] = aiJ00[I][J];
                            }
                        }
                        for (; I < PK; I++) {
                            for (J = 0; J < NumberLength; J++) {
                                aiJS[I][J] = 0;
                            }
                        }
                        DivBigNbrByLong(TestNbr, PK, biExp, NumberLength);
                        JS_E(PK, PL, PM, P);
                        for (I = 0; I < PK; I++) {
                            for (J = 0; J < NumberLength; J++) {
                                aiJW[I][J] = 0;
                            }
                        }
                        for (I = 0; I < PL; I++) {
                            for (J = 0; J < PL; J++) {
                                MontgomeryMult(aiJS[I], aiJ01[J], biTmp);
                                AddBigNbrModN(biTmp, aiJW[(I + J) % PK], aiJW[(I + J) % PK], TestNbr,
                                        NumberLength);
                            }
                        }
                        NormalizeJW(PK, PL, PM, P);
                        MatchingRoot:
                        do {
                            H = -1;
                            W = 0;
                            for (I = 0; I < PL; I++) {
                                if (!BigNbrIsZero(aiJW[I])) {
                                    if (H == -1
                                            && BigNbrAreEqual(aiJW[I], MontgomeryMultR1)) {
                                        H = I;
                                    } else {
                                        H = -2;
                                        AddBigNbrModN(aiJW[I], MontgomeryMultR1, biTmp, TestNbr,
                                                NumberLength);
                                        if (BigNbrIsZero(biTmp)) {
                                            W++;
                                        }
                                    }
                                }
                            }
                            if (H >= 0) {
                                break MatchingRoot;
                            }
                            if (W != P - 1) {
                                return 1; /* Not prime */
                            }
                            for (I = 0; I < PM; I++) {
                                AddBigNbrModN(aiJW[I], MontgomeryMultR1, biTmp, TestNbr, NumberLength);
                                if (BigNbrIsZero(biTmp)) {
                                    break;
                                }
                            }
                            if (I == PM) {
                                return 1; /* Not prime */
                            }
                            for (J = 1; J <= P - 2; J++) {
                                AddBigNbrModN(aiJW[I + J * PM], MontgomeryMultR1, biTmp, TestNbr,
                                        NumberLength);
                                if (!BigNbrIsZero(biTmp)) {
                                    return 1; /* Not prime */
                                }
                            }
                            H = I + PL;
                        }
                        while (false);
                        if (SW == 1 || H % P == 0) {
                            continue;
                        }
                        if (P != 2) {
                            SW = 1;
                            continue;
                        }
                        if (K == 1) {
                            if ((TestNbr[0] & 3) == 1) {
                                SW = 1;
                            }
                            continue;
                        }

                        // if (Q^((N-1)/2) mod N != N-1), N is not prime.

                        MultBigNbrByLongModN(MontgomeryMultR1, Q, biTmp, TestNbr,
                                NumberLength);
                        for (I = 0; I < NumberLength; I++) {
                            biR[I] = biTmp[I];
                        }
                        I = NumberLength - 1;
                        Mask = 0x40000000;
                        while ((TestNbr[I] & Mask) == 0) {
                            Mask /= 2;
                            if (Mask == 0) {
                                I--;
                                Mask = 0x40000000L;
                            }
                        }
                        do {
                            Mask /= 2;
                            if (Mask == 0) {
                                I--;
                                Mask = 0x40000000L;
                            }
                            MontgomeryMult(biR, biR, biT);
                            for (J = 0; J < NumberLength; J++) {
                                biR[J] = biT[J];
                            }
                            if ((TestNbr[I] & Mask) != 0) {
                                MontgomeryMult(biR, biTmp, biT);
                                for (J = 0; J < NumberLength; J++) {
                                    biR[J] = biT[J];
                                }
                            }
                        }
                        while (I > 0 || Mask > 2);
                        AddBigNbrModN(biR, MontgomeryMultR1, biTmp, TestNbr, NumberLength);
                        if (!BigNbrIsZero(biTmp)) {
                            return 1; /* Not prime */
                        }
                        SW = 1;
                    } /* end for j */
                    if (SW == 0) {
                        TestedQs = TestingQs + 1;
                        if (TestingQs < aiNQ[LEVELnow] - 1) {
                            TestingQs++;
                            Q = aiQ[TestingQs];
                            U = T * Q;
                            do {
                                MultBigNbrByLong(biS, Q, biS, NumberLength);
                                U /= Q;
                            }
                            while (U % Q == 0);
                            continue; /* Retry */
                        }
                        LEVELnow++;
                        if (LEVELnow == LEVELmax) {
                            return ProbabilisticPrimeTest(N); /* Cannot tell */
                        }
                        T = aiT[LEVELnow];
                        NP = aiNP[LEVELnow];
                        biS[0] = 2;
                        for (J = 1; J < NumberLength; J++) {
                            biS[J] = 0;
                        }
                        for (J = 0; J <= aiNQ[LEVELnow]; J++) {
                            Q = aiQ[J];
                            U = T * Q;
                            do {
                                MultBigNbrByLong(biS, Q, biS, NumberLength);
                                U /= Q;
                            }
                            while (U % Q == 0);
                            if (CompareSquare(biS, TestNbr) > 0) {
                                TestingQs = J;
                                continue MainStart; /* Retry from the beginning */
                            }
                        } /* end for J */
                        return ProbabilisticPrimeTest(N); /* Program error */
                    } /* end if */
                    break;
                }             /* end for (;;) */
            } /* end for i */

            // Final Test

            LengthN = NumberLength;
            for (I = 0; I < NumberLength; I++) {
                biN[I] = TestNbr[I];
                TestNbr[I] = biS[I];
                biR[I] = 0;
            }
            for (; ; ) {
                if (TestNbr[NumberLength - 1] != 0) {
                    break;
                }
                NumberLength--;
            }
            dN = (double) TestNbr[NumberLength - 1];
            if (NumberLength > 1) {
                dN += (double) TestNbr[NumberLength - 2] / dDosALa31;
            }
            if (NumberLength > 2) {
                dN += (double) TestNbr[NumberLength - 3] / dDosALa62;
            }
            LengthS = NumberLength;
            dS = dN;
            MontgomeryMultR1[0] = 1;
            for (I = 1; I < NumberLength; I++) {
                MontgomeryMultR1[I] = 0;
            }

            biR[0] = 1;
            BigNbrModN(biN, LengthN, biT); /* Compute N mod S */
            for (J = 1; J <= T; J++) {
                MultBigNbrModN(biR, biT, biTmp, TestNbr, NumberLength);
                for (i = NumberLength - 1; i > 0; i--) {
                    if (biTmp[i] != 0) {
                        break;
                    }
                }
                if (i == 0 && biTmp[0] != 1) {
                    return 0; /* Number is prime */
                }
                for (; ; ) {
                    if (biTmp[NumberLength - 1] != 0) {
                        break;
                    }
                    NumberLength--;
                }
                for (I = 0; I < NumberLength; I++) {
                    TestNbr[I] = biTmp[I];
                }
                dN = (double) TestNbr[NumberLength - 1];
                if (NumberLength > 1) {
                    dN += (double) TestNbr[NumberLength - 2] / dDosALa31;
                }
                if (NumberLength > 2) {
                    dN += (double) TestNbr[NumberLength - 3] / dDosALa62;
                }
                for (i = NumberLength - 1; i > 0; i--) {
                    if (TestNbr[i] != biTmp[i]) {
                        break;
                    }
                }
                if (TestNbr[i] > biTmp[i]) {
                    BigNbrModN(biN, LengthN, biTmp); /* Compute N mod R */
                    if (BigNbrIsZero(biTmp)) { /* If N is multiple of R.. */
                        return 1; /* Number is composite */
                    }
                }
                dN = dS;
                NumberLength = LengthS;
                for (I = 0; I < NumberLength; I++) {
                    biR[I] = TestNbr[I];
                    TestNbr[I] = biS[I];
                }
            } /* End for J */
            return 0; /* Number is prime */
        }
    }

    // Prime checking routine
    // Return codes: 0 = Number is prime.
    //               1 = Number is composite.
    int ProbabilisticPrimeTest(BigInteger N) {
        long Base, Q;
        int baseNbr, nbrBases, exp, index, j, k;
        long mask;

        NumberLength = BigNbrToBigInt(N, TestNbr);
        GetYieldFrequency();
        exp = N.subtract(BigInt1).getLowestSetBit();
        GetMontgomeryParms();
        Base = 1;
        nbrBases = N.bitLength() / 2;
        for (baseNbr = 0; baseNbr < nbrBases; baseNbr++) {
            if (Base < 3) {
                Base++;
            } else {
                calculate_new_prime4:
                for (; ; ) {
                    Base += 2;
                    for (Q = 3; Q * Q <= Base; Q += 2) { /* Check if Base is prime */
                        if (Base % Q == 0) {
                            continue calculate_new_prime4; /* Composite */
                        }
                    }
                    break; /* Prime found */
                }
            } /* end if */

            System.arraycopy(MontgomeryMultR1, 0, biN, 0, NumberLength);
            index = NumberLength - 1;
            mask = 0x40000000L;
            for (k = NumberLength * 31; k > exp; k--) {
                MontgomeryMult(biN, biN, biT);
                if ((TestNbr[index] & mask) != 0) {
                    MultBigNbrByLongModN(biT, Base, biT, TestNbr, NumberLength);
                }
                System.arraycopy(biT, 0, biN, 0, NumberLength);
                mask >>= 1;
                if (mask == 0) {
                    index--;
                    mask = 0x40000000L;
                }
            }
            for (j = 0; j < NumberLength; j++) {
                if (biN[j] != MontgomeryMultR1[j]) {
                    break;
                }
            }
            if (j == NumberLength) {
                continue;
            } /* Probable prime, go to next base */
            for (k = 0; k < exp; k++) {
                AddBigNbr(biN, MontgomeryMultR1, biT, NumberLength);
          /* If temp is congruent to -1, the number is prob. prime */
                for (j = 0; j < NumberLength; j++) {
                    if (biT[j] != TestNbr[j]) {
                        break;
                    }
                }
                if (j == NumberLength) {
                    break;
                } /* Probable prime, go to next base */
                MontgomeryMult(biN, biN, biT);
          /* Check whether square equals 1 */
                for (j = 0; j < NumberLength; j++) {
                    if (biT[j] != MontgomeryMultR1[j]) {
                        break;
                    }
                }
                if (j == NumberLength) { // Check whether the number can be factored
                    // by computing gcd(temp-1, N).
                    SubtractBigNbrModN(biN, MontgomeryMultR1, biU, TestNbr, NumberLength);
                    gcdBigNbr(biU, TestNbr, biV, NumberLength);
                    for (j = 0; j < NumberLength; j++) {
                        if (biV[j] != BigNbr1[j]) {
                            break;
                        }
                    }
                    if (j < NumberLength) {  // biV is a proper factor.
                        probableFactor = BigIntToBigNbr(biV, NumberLength);
                        return 1; /* Composite number */
                    }
                }
                System.arraycopy(biT, 0, biN, 0, NumberLength);
            }
            if (k == exp) {
                return 1; /* Composite number */
            }
        }
        return 0; /* Indicate probable prime */
    }

    static BigInteger BigIntToBigNbr(int[] GD, int NumberLength) {
        byte[] Result;
        long[] Temp;
        int i, NL;
        long digit;

        Temp = new long[NumberLength];
        Convert31To32Bits(GD, Temp, NumberLength);
        NL = NumberLength * 4;
        Result = new byte[NL];
        for (i = 0; i < NumberLength; i++) {
            digit = Temp[i];
            Result[NL - 1 - 4 * i] = (byte) (digit & 0xFF);
            Result[NL - 2 - 4 * i] = (byte) (digit / 0x100 & 0xFF);
            Result[NL - 3 - 4 * i] = (byte) (digit / 0x10000 & 0xFF);
            Result[NL - 4 - 4 * i] = (byte) (digit / 0x1000000 & 0xFF);
        }
        return (new BigInteger(Result));
    }

    // Compare Nbr1^2 vs. Nbr2
    int CompareSquare(int Nbr1[], int Nbr2[]) {
        int I, k;

        for (I = NumberLength - 1; I > 0; I--) {
            if (Nbr1[I] != 0) {
                break;
            }
        }
        k = NumberLength / 2;
        if (NumberLength % 2 == 0) {
            if (I >= k) {
                return 1;
            } // Nbr1^2 > Nbr2
            if (I < k - 1 || biS[k - 1] < 65536) {
                return -1;
            } // Nbr1^2 < Nbr2
        } else {
            if (I < k) {
                return -1;
            } // Nbr1^2 < Nbr2
            if (I > k || biS[k] >= 65536) {
                return 1;
            } // Nbr1^2 > Nbr2
        }
        MultBigNbr(biS, biS, biTmp, NumberLength);
        SubtractBigNbr(biTmp, TestNbr, biTmp, NumberLength);
        if (BigNbrIsZero(biTmp)) {
            return 0;
        } // Nbr1^2 == Nbr2
        if (biTmp[NumberLength - 1] >= 0) {
            return 1;
        } // Nbr1^2 > Nbr2
        return -1; // Nbr1^2 < Nbr2
    }

    // Perform JS <- JS ^ E

    void JS_E(int PK, int PL, int PM, int P) {
        int I, J, K;
        long Mask;

        for (I = NumberLength - 1; I > 0; I--) {
            if (biExp[I] != 0) {
                break;
            }
        }
        if (I == 0 && biExp[0] == 1) {
            return;
        } // Return if E == 1
        for (K = 0; K < PL; K++) {
            for (J = 0; J < NumberLength; J++) {
                aiJW[K][J] = aiJS[K][J];
            }
        }
        Mask = 0x40000000L;
        for (; ; ) {
            if ((biExp[I] & Mask) != 0) {
                break;
            }
            Mask /= 2;
        }
        do {
            JS_2(PK, PL, PM, P);
            Mask /= 2;
            if (Mask == 0) {
                Mask = 0x40000000L;
                I--;
            }
            if ((biExp[I] & Mask) != 0) {
                JS_JW(PK, PL, PM, P);
            }
        }
        while (I > 0 || Mask != 1);
    }

    // Perform JS <- JS * JW

    void JS_JW(int PK, int PL, int PM, int P) {
        int I, J, K;
        for (I = 0; I < PL; I++) {
            for (J = 0; J < PL; J++) {
                K = (I + J) % PK;
                MontgomeryMult(aiJS[I], aiJW[J], biTmp);
                AddBigNbrModN(aiJX[K], biTmp, aiJX[K], TestNbr, NumberLength);
            }
        }
        for (I = 0; I < PK; I++) {
            for (J = 0; J < NumberLength; J++) {
                aiJS[I][J] = aiJX[I][J];
                aiJX[I][J] = 0;
            }
        }
        NormalizeJS(PK, PL, PM, P);
    }

    // Perform JS <- JS ^ 2

    void JS_2(int PK, int PL, int PM, int P) {
        int I, J, K;
        for (I = 0; I < PL; I++) {
            K = 2 * I % PK;
            MontgomeryMult(aiJS[I], aiJS[I], biTmp);
            AddBigNbrModN(aiJX[K], biTmp, aiJX[K], TestNbr, NumberLength);
            AddBigNbrModN(aiJS[I], aiJS[I], biT, TestNbr, NumberLength);
            for (J = I + 1; J < PL; J++) {
                K = (I + J) % PK;
                MontgomeryMult(biT, aiJS[J], biTmp);
                AddBigNbrModN(aiJX[K], biTmp, aiJX[K], TestNbr, NumberLength);
            }
        }
        for (I = 0; I < PK; I++) {
            for (J = 0; J < NumberLength; J++) {
                aiJS[I][J] = aiJX[I][J];
                aiJX[I][J] = 0;
            }
        }
        NormalizeJS(PK, PL, PM, P);
    }

    // Normalize coefficient of JS
    void NormalizeJS(int PK, int PL, int PM, int P) {
        int I, J;
        for (I = PL; I < PK; I++) {
            if (!BigNbrIsZero(aiJS[I])) {
                for (J = 0; J < NumberLength; J++) {
                    biT[J] = aiJS[I][J];
                }
                for (J = 1; J < P; J++) {
                    SubtractBigNbrModN(aiJS[I - J * PM], biT, aiJS[I - J * PM],
                            TestNbr, NumberLength);
                }
                for (J = 0; J < NumberLength; J++) {
                    aiJS[I][J] = 0;
                }
            }
        }
    }

    // Normalize coefficient of JW
    void NormalizeJW(int PK, int PL, int PM, int P) {
        int I, J;
        for (I = PL; I < PK; I++) {
            if (!BigNbrIsZero(aiJW[I])) {
                for (J = 0; J < NumberLength; J++) {
                    biT[J] = aiJW[I][J];
                }
                for (J = 1; J < P; J++) {
                    SubtractBigNbrModN(aiJW[I - J * PM], biT, aiJW[I - J * PM],
                            TestNbr, NumberLength);
                }
                for (J = 0; J < NumberLength; J++) {
                    aiJW[I][J] = 0;
                }
            }
        }
    }

    void JacobiSum(int A, int B, int P, int PK, int PL, int PM, int Q) {
        int I, J, K;

        for (I = 0; I < PL; I++) {
            for (J = 0; J < NumberLength; J++) {
                aiJ0[I][J] = 0;
            }
        }
        for (I = 1; I <= Q - 2; I++) {
            J = (A * I + B * aiF[I]) % PK;
            if (J < PL) {
                AddBigNbrModN(aiJ0[J], MontgomeryMultR1, aiJ0[J], TestNbr,
                        NumberLength);
            } else {
                for (K = 1; K < P; K++) {
                    SubtractBigNbrModN(
                            aiJ0[J - K * PM],
                            MontgomeryMultR1,
                            aiJ0[J - K * PM],
                            TestNbr, NumberLength);
                }
            }
        }
    }


    private void GetYieldFrequency() {
        yieldFreq = 1000000 / (NumberLength * NumberLength);
        if (yieldFreq > 100000)
            yieldFreq = yieldFreq / 100000 * 100000;
        else if (yieldFreq > 10000)
            yieldFreq = yieldFreq / 10000 * 10000;
        else if (yieldFreq > 1000)
            yieldFreq = yieldFreq / 1000 * 1000;
        else if (yieldFreq > 100)
            yieldFreq = yieldFreq / 100 * 100;
    }

    static int BigNbrToBigInt(BigInteger N, int TestNbr[]) {
        byte[] Result;
        long[] Temp;
        int i, j, mask;
        long p;
        int NumberLength;

        Result = N.toByteArray();
        NumberLength = (Result.length * 8 + 30) / 31;
        Temp = new long[NumberLength + 1];
        j = 0;
        mask = 1;
        p = 0;
        for (i = Result.length - 1; i >= 0; i--) {
            p += mask * (long) (Result[i] >= 0 ? Result[i] : Result[i] + 256);
            mask <<= 8;
            if (mask == 0) {                        // Overflow
                Temp[j++] = p;
                mask = 1;
                p = 0;
            }
        }
        Temp[j] = p;
        Convert32To31Bits(Temp, TestNbr, NumberLength);
        if (TestNbr[NumberLength - 1] > Mi) {
            TestNbr[NumberLength] = 0;
            NumberLength++;
        }
        TestNbr[NumberLength] = 0;
        return NumberLength;
    }


    static void LongToBigNbr(long Nbr, int Out[], int NumberLength) {
        int i;

        Out[0] = (int) (Nbr & 0x7FFFFFFF);
        Out[1] = (int) ((Nbr >> 31) & 0x7FFFFFFF);
        for (i = 2; i < NumberLength; i++) {
            Out[i] = (Nbr < 0 ? 0x7FFFFFFF : 0);
        }
    }

    boolean BigNbrIsZero(int Nbr[]) {
        for (int i = 0; i < NumberLength; i++) {
            if (Nbr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    boolean BigNbrAreEqual(int Nbr1[], int Nbr2[]) {
        for (int i = 0; i < NumberLength; i++) {
            if (Nbr1[i] != Nbr2[i]) {
                return false;
            }
        }
        return true;
    }

    static void ChSignBigNbr(int Nbr[], int NumberLength) {
        int carry = 0;
        for (int i = 0; i < NumberLength; i++) {
            carry = (carry >> 31) - Nbr[i];
            Nbr[i] = carry & 0x7FFFFFFF;
        }
    }

    static void AddBigNbr(int Nbr1[], int Nbr2[], int Sum[],
                          int NumberLength) {
        long carry = 0;
        for (int i = 0; i < NumberLength; i++) {
            carry = (carry >> 31) + (long) Nbr1[i] + (long) Nbr2[i];
            Sum[i] = (int) (carry & 0x7FFFFFFFL);
        }
    }

    static void SubtractBigNbr(int Nbr1[], int Nbr2[], int Diff[],
                               int NumberLength) {
        long carry = 0;
        for (int i = 0; i < NumberLength; i++) {
            carry = (carry >> 31) + (long) Nbr1[i] - (long) Nbr2[i];
            Diff[i] = (int) (carry & 0x7FFFFFFFL);
        }
    }

    static void MultBigNbr(int Nbr1[], int Nbr2[], int Prod[], int NumberLength) {
        long MaxUInt = 0x7FFFFFFFL;
        long carry, Pr;
        int i, j;
        carry = Pr = 0;
        for (i = 0; i < NumberLength; i++) {
            Pr = carry & MaxUInt;
            carry >>>= 31;
            for (j = 0; j <= i; j++) {
                Pr += (long) Nbr1[j] * (long) Nbr2[i - j];
                carry += (Pr >>> 31);
                Pr &= MaxUInt;
            }
            Prod[i] = (int) Pr;
        }
    }

    static void MultBigNbrByLong(int Nbr1[], long Nbr2, int Prod[],
                                 int NumberLength) {
        long MaxUInt = 0x7FFFFFFFL;
        long Pr;
        int i;
        Pr = 0;
        for (i = 0; i < NumberLength; i++) {
            Pr = (Pr >> 31) + Nbr2 * (long) Nbr1[i];
            Prod[i] = (int) (Pr & MaxUInt);
        }
    }

    long BigNbrModLong(int Nbr1[], long Nbr2) {
        int i;
        long Rem = 0;

        for (i = NumberLength - 1; i >= 0; i--) {
            Rem = ((Rem << 31) + (long) Nbr1[i]) % Nbr2;
        }
        return Rem;
    }

    static void AddBigNbrModN(int Nbr1[], int Nbr2[], int Sum[],
                              int TestNbr[], int NumberLength) {
        long MaxUInt = 0x7FFFFFFFL;
        long carry = 0;
        int i;

        for (i = 0; i < NumberLength; i++) {
            carry = (carry >> 31) + (long) Nbr1[i] + (long) Nbr2[i] - (long) TestNbr[i];
            Sum[i] = (int) (carry & MaxUInt);
        }
        if (carry < 0) {
            carry = 0;
            for (i = 0; i < NumberLength; i++) {
                carry = (carry >> 31) + (long) Sum[i] + (long) TestNbr[i];
                Sum[i] = (int) (carry & MaxUInt);
            }
        }
    }

    static void SubtractBigNbrModN(int Nbr1[], int Nbr2[], int Diff[],
                                   int TestNbr[], int NumberLength) {
        long MaxUInt = 0x7FFFFFFFL;
        long carry = 0;
        int i;

        for (i = 0; i < NumberLength; i++) {
            carry = (carry >> 31) + (long) Nbr1[i] - (long) Nbr2[i];
            Diff[i] = (int) (carry & MaxUInt);
        }
        if (carry < 0) {
            carry = 0;
            for (i = 0; i < NumberLength; i++) {
                carry = (carry >> 31) + (long) Diff[i] + (long) TestNbr[i];
                Diff[i] = (int) (carry & MaxUInt);
            }
        }
    }

    void MontgomeryMult(int Nbr1[], int Nbr2[], int Prod[]) {
        long New;
        int NumberLength = this.NumberLength;


        if (lModularMult >= 0) {
            lModularMult++;
            if ((lModularMult % yieldFreq) == 0) {
                Thread.yield();
                New = System.currentTimeMillis();
                if (OldTimeElapsed >= 0
                        && OldTimeElapsed / 1000 != (OldTimeElapsed + New - Old) / 1000) {
                    OldTimeElapsed += New - Old;
                    Old = New;


                }
            }
        }
        switch (NumberLength) {
            case 2:
                MontgomeryMult2(Nbr1, Nbr2, Prod);
                break;
/*            case 3 :
                MontgomeryMult3(Nbr1, Nbr2, Prod);
                break;
            case 4 :
                MontgomeryMult4(Nbr1, Nbr2, Prod);
                break;
            case 5 :
                MontgomeryMult5(Nbr1, Nbr2, Prod);
                break;
            case 6 :
                MontgomeryMult6(Nbr1, Nbr2, Prod);
                break;
            case 7 :
                MontgomeryMult7(Nbr1, Nbr2, Prod);
                break;
            case 8 :
                MontgomeryMult8(Nbr1, Nbr2, Prod);
                break;
            case 9 :
                MontgomeryMult9(Nbr1, Nbr2, Prod);
                break;
            case 10 :
                MontgomeryMult10(Nbr1, Nbr2, Prod);
                break;
            case 11 :
                MontgomeryMult11(Nbr1, Nbr2, Prod);
                break;*/
            default: {
                LargeMontgomeryMult(Nbr1, Nbr2, Prod);
            }

            break;
        }
    }

    void MontgomeryMult2(int Nbr1[], int Nbr2[], int Prod[]) {
        long Pr, Nbr, MontDig;
        long Prod0, Prod1;
        Prod0 = Prod1 = 0;
        long TestNbr0 = TestNbr[0];
        long TestNbr1 = TestNbr[1];
        long Nbr2_0 = Nbr2[0];
        long Nbr2_1 = Nbr2[1];
        for (int i = 0; i < 2; i++) {
            Pr = (Nbr = Nbr1[i]) * Nbr2_0 + Prod0;
            MontDig = ((int) Pr * MontgomeryMultN) & 0x7FFFFFFFL;
            Prod0 = (Pr = ((MontDig * TestNbr0 + Pr) >>> 31) +
                    MontDig * TestNbr1 + Nbr * Nbr2_1 + Prod1) & 0x7FFFFFFFL;
            Prod1 = Pr >>> 31;
        }
        if (Prod1 > TestNbr1 || Prod1 == TestNbr1 && (Prod0 >= TestNbr0)) {
            Prod0 = (Pr = Prod0 - TestNbr0) & 0x7FFFFFFFL;
            Prod1 = ((Pr >> 31) + Prod1 - TestNbr1) & 0x7FFFFFFFL;
        }
        Prod[0] = (int) Prod0;
        Prod[1] = (int) Prod1;
    }

    void LargeMontgomeryMult(int Nbr1[], int Nbr2[], int Prod[]) {
        long Pr, Nbr, MontDig;
        long Prod0, Prod1, Prod2, Prod3, Prod4, Prod5, Prod6, Prod7, Prod8,
                Prod9, Prod10;
        Prod0 = Prod1 = Prod2 = Prod3 =
                Prod4 = Prod5 = Prod6 = Prod7 = Prod8 = Prod9 = Prod10 = 0;
        long TestNbr0 = TestNbr[0];
        long TestNbr1 = TestNbr[1];
        long TestNbr2 = TestNbr[2];
        long TestNbr3 = TestNbr[3];
        long TestNbr4 = TestNbr[4];
        long TestNbr5 = TestNbr[5];
        long TestNbr6 = TestNbr[6];
        long TestNbr7 = TestNbr[7];
        long TestNbr8 = TestNbr[8];
        long TestNbr9 = TestNbr[9];
        long TestNbr10 = TestNbr[10];
        long Nbr2_0 = Nbr2[0];
        long Nbr2_1 = Nbr2[1];
        long Nbr2_2 = Nbr2[2];
        long Nbr2_3 = Nbr2[3];
        long Nbr2_4 = Nbr2[4];
        long Nbr2_5 = Nbr2[5];
        long Nbr2_6 = Nbr2[6];
        long Nbr2_7 = Nbr2[7];
        long Nbr2_8 = Nbr2[8];
        long Nbr2_9 = Nbr2[9];
        long Nbr2_10 = Nbr2[10];
        int j;
        for (j = 11; j < NumberLength; j++) {
            Prod[j] = 0;
        }
        for (int i = 0; i < NumberLength; i++) {
            Pr = (Nbr = Nbr1[i]) * Nbr2_0 + Prod0;
            MontDig = ((int) Pr * MontgomeryMultN) & 0x7FFFFFFFL;
            Prod0 = (Pr = ((MontDig * TestNbr0 + Pr) >>> 31) +
                    MontDig * TestNbr1 + Nbr * Nbr2_1 + Prod1) & 0x7FFFFFFFL;
            Prod1 = (Pr = (Pr >>> 31) +
                    MontDig * TestNbr2 + Nbr * Nbr2_2 + Prod2) & 0x7FFFFFFFL;
            Prod2 = (Pr = (Pr >>> 31) +
                    MontDig * TestNbr3 + Nbr * Nbr2_3 + Prod3) & 0x7FFFFFFFL;
            Prod3 = (Pr = (Pr >>> 31) +
                    MontDig * TestNbr4 + Nbr * Nbr2_4 + Prod4) & 0x7FFFFFFFL;
            Prod4 = (Pr = (Pr >>> 31) +
                    MontDig * TestNbr5 + Nbr * Nbr2_5 + Prod5) & 0x7FFFFFFFL;
            Prod5 = (Pr = (Pr >>> 31) +
                    MontDig * TestNbr6 + Nbr * Nbr2_6 + Prod6) & 0x7FFFFFFFL;
            Prod6 = (Pr = (Pr >>> 31) +
                    MontDig * TestNbr7 + Nbr * Nbr2_7 + Prod7) & 0x7FFFFFFFL;
            Prod7 = (Pr = (Pr >>> 31) +
                    MontDig * TestNbr8 + Nbr * Nbr2_8 + Prod8) & 0x7FFFFFFFL;
            Prod8 = (Pr = (Pr >>> 31) +
                    MontDig * TestNbr9 + Nbr * Nbr2_9 + Prod9) & 0x7FFFFFFFL;
            Prod9 = (Pr = (Pr >>> 31) +
                    MontDig * TestNbr10 + Nbr * Nbr2_10 + Prod10) & 0x7FFFFFFFL;
            Prod10 = (Pr = (Pr >>> 31) +
                    MontDig * TestNbr[11] + Nbr * Nbr2[11] + Prod[11]) & 0x7FFFFFFFL;
            for (j = 12; j < NumberLength; j++) {
                Prod[j - 1] = (int) ((Pr = (Pr >>> 31) +
                        MontDig * TestNbr[j] + Nbr * Nbr2[j] + Prod[j]) & 0x7FFFFFFFL);
            }
            Prod[j - 1] = (int) (Pr >>> 31);
        }
        Prod[0] = (int) Prod0;
        Prod[1] = (int) Prod1;
        Prod[2] = (int) Prod2;
        Prod[3] = (int) Prod3;
        Prod[4] = (int) Prod4;
        Prod[5] = (int) Prod5;
        Prod[6] = (int) Prod6;
        Prod[7] = (int) Prod7;
        Prod[8] = (int) Prod8;
        Prod[9] = (int) Prod9;
        Prod[10] = (int) Prod10;
        for (j = NumberLength - 1; j >= 0; j--) {
            if (Prod[j] != TestNbr[j]) {
                break;
            }
        }
        if (j < 0 || j >= 0 && Prod[j] >= TestNbr[j]) {
            Pr = 0;
            for (j = 0; j < NumberLength; j++) {
                Prod[j] = (int) ((Pr = (Pr >> 31) +
                        (long) Prod[j] - (long) TestNbr[j]) & 0x7FFFFFFFL);
            }
        }
    }


    void GetMontgomeryParms() {
        int NumberLength = this.NumberLength;
        int N, x, i, j, k, div;
        int length;

        dN = (double) TestNbr[NumberLength - 1];
        if (NumberLength > 1) {
            dN += (double) TestNbr[NumberLength - 2] / dDosALa31;
        }
        if (NumberLength > 2) {
            dN += (double) TestNbr[NumberLength - 3] / dDosALa62;
        }

        x = N = (int) TestNbr[0]; // 2 least significant bits of inverse correct.
        x = x * (2 - N * x); // 4 least significant bits of inverse correct.
        x = x * (2 - N * x); // 8 least significant bits of inverse correct.
        x = x * (2 - N * x); // 16 least significant bits of inverse correct.
        x = x * (2 - N * x); // 32 least significant bits of inverse correct.
        MontgomeryMultN = (-x) & 0x7FFFFFFF;

        j = NumberLength;
        MontgomeryMultR1[j] = 1;
        do {
            MontgomeryMultR1[--j] = 0;
        }
        while (j > 0);
        AdjustModN(MontgomeryMultR1, TestNbr, NumberLength);
        MultBigNbrModN(MontgomeryMultR1, MontgomeryMultR1, MontgomeryMultR2,
                TestNbr, NumberLength);
        MontgomeryMult(MontgomeryMultR2, MontgomeryMultR2, MontgomeryMultAfterInv);
        AddBigNbrModN(MontgomeryMultR1, MontgomeryMultR1, MontgomeryMultR2,
                TestNbr, NumberLength);
    }

    void BigNbrModN(int Nbr[], int Length, int Mod[]) {
        int i, j;
        for (i = 0; i < NumberLength; i++) {
            Mod[i] = Nbr[i + Length - NumberLength];
        }
        Mod[i] = 0;
        AdjustModN(Mod, TestNbr, NumberLength);
        for (i = Length - NumberLength - 1; i >= 0; i--) {
            for (j = NumberLength; j > 0; j--) {
                Mod[j] = Mod[j - 1];
            }
            Mod[0] = Nbr[i];
            AdjustModN(Mod, TestNbr, NumberLength);
        }
    }

    static void MultBigNbrModN(int Nbr1[], int Nbr2[], int Prod[],
                               int TestNbr[], int NumberLength) {
        long MaxUInt = 0x7FFFFFFFL;
        int i, j;
        long Pr, Nbr;

        if (NumberLength >= 2 &&
                TestNbr[NumberLength - 1] == 0 && TestNbr[NumberLength - 2] < 0x40000000) {
            NumberLength--;
        }
        i = NumberLength;
        do {
            Prod[--i] = 0;
        }
        while (i > 0);
        i = NumberLength;
        do {
            Nbr = Nbr1[--i];
            j = NumberLength;
            do {
                Prod[j] = Prod[j - 1];
                j--;
            }
            while (j > 0);
            Prod[0] = 0;
            Pr = 0;
            for (j = 0; j < NumberLength; j++) {
                Pr = (Pr >>> 31) + Nbr * Nbr2[j] + Prod[j];
                Prod[j] = (int) (Pr & MaxUInt);
            }
            Prod[j] += (Pr >>> 31);
            AdjustModN(Prod, TestNbr, NumberLength);
        }
        while (i > 0);
    }

    static void MultBigNbrByLongModN(int Nbr1[], long Nbr2, int Prod[],
                                     int TestNbr[], int NumberLength) {
        long MaxUInt = 0x7FFFFFFFL;
        long Pr;
        int j;

        if (NumberLength >= 2 &&
                TestNbr[NumberLength - 1] == 0 && TestNbr[NumberLength - 2] < 0x40000000) {
            NumberLength--;
        }
        Pr = 0;
        for (j = 0; j < NumberLength; j++) {
            Pr = (Pr >>> 31) + Nbr2 * Nbr1[j];
            Prod[j] = (int) (Pr & MaxUInt);
        }
        Prod[j] = (int) (Pr >>> 31);
        AdjustModN(Prod, TestNbr, NumberLength);
    }

    static void AdjustModN(int Nbr[], int TestNbr[], int NumberLength) {
        long MaxUInt = 0x7FFFFFFFL;
        long TrialQuotient;
        long carry;
        int i;
        double dAux, dN;

        dN = (double) TestNbr[NumberLength - 1];
        if (NumberLength > 1) {
            dN += (double) TestNbr[NumberLength - 2] / dDosALa31;
        }
        if (NumberLength > 2) {
            dN += (double) TestNbr[NumberLength - 3] / dDosALa62;
        }
        dAux =
                (double) Nbr[NumberLength] * dDosALa31 + (double) Nbr[NumberLength - 1];
        if (NumberLength > 1) {
            dAux += (double) Nbr[NumberLength - 2] / dDosALa31;
        }
        TrialQuotient = (long) (dAux / dN) + 3;
        if (TrialQuotient >= DosALa32) {
            carry = 0;
            for (i = 0; i < NumberLength; i++) {
                carry = Nbr[i + 1] - (TrialQuotient >>> 31) * TestNbr[i] - carry;
                Nbr[i + 1] = (int) (carry & MaxUInt);
                carry = (MaxUInt - carry) >>> 31;
            }
            TrialQuotient &= MaxUInt;
        }
        carry = 0;
        for (i = 0; i < NumberLength; i++) {
            carry = Nbr[i] - TrialQuotient * TestNbr[i] - carry;
            Nbr[i] = (int) (carry & MaxUInt);
            carry = (MaxUInt - carry) >>> 31;
        }
        Nbr[NumberLength] -= (int) carry;
        while ((Nbr[NumberLength] & MaxUInt) != 0) {
            carry = 0;
            for (i = 0; i < NumberLength; i++) {
                carry += (long) Nbr[i] + (long) TestNbr[i];
                Nbr[i] = (int) (carry & MaxUInt);
                carry >>= 31;
            }
            Nbr[NumberLength] += (int) carry;
        }
    }

    static void DivBigNbrByLong(int Dividend[], long Divisor, int Quotient[],
                                int NumberLength) {
        int i;
        boolean ChSignDivisor = false;
        long Divid, Rem = 0;

        if (Divisor < 0) {                            // If divisor is negative...
            ChSignDivisor = true;      // Indicate to change sign at the end and
            Divisor = -Divisor;        // convert divisor to positive.
        }
        if (Dividend[i = NumberLength - 1] >= 0x40000000) {                            // If dividend is negative...
            Rem = Divisor - 1;
        }
        for (; i >= 0; i--) {
            Divid = Dividend[i] + (Rem << 31);
            Rem = Divid - (Quotient[i] = (int) (Divid / Divisor)) * Divisor;
        }
        if (ChSignDivisor) {                            // Change sign if divisor is negative.
            // Convert divisor to positive.
            ChSignBigNbr(Quotient, NumberLength);
        }
    }

    static long RemDivBigNbrByLong(int Dividend[], long Divisor,
                                   int NumberLength) {
        int i;
        long Rem = 0;
        long Mod2_31;
        int divis = (int) (Divisor < 0 ? -Divisor : Divisor);
        if (Divisor < 0) {                            // If divisor is negative...
            Divisor = -Divisor;        // Convert divisor to positive.
        }
        Mod2_31 = ((-2147483648) - divis) % divis;  // 2^31 mod divis.
        if (Dividend[i = NumberLength - 1] >= 0x40000000) {                            // If dividend is negative...
            Rem = Divisor - 1;
        }
        for (; i >= 0; i--) {
            Rem = Rem * Mod2_31 + Dividend[i];
            do {
                Rem = (Rem >> 31) * Mod2_31 + (Rem & 0x7FFFFFFF);
            } while (Rem > 0x1FFFFFFFFL);
        }
        return Rem % divis;
    }

    // Gcd calculation:
    // Step 1: Set k<-0, and then repeatedly set k<-k+1, u<-u/2, v<-v/2
    //         zero or more times until u and v are not both even.
    // Step 2: If u is odd, set t<-(-v) and go to step 4. Otherwise set t<-u.
    // Step 3: Set t<-t/2
    // Step 4: If t is even, go back to step 3.
    // Step 5: If t>0, set u<-t, otherwise set v<-(-t).
    // Step 6: Set t<-u-v. If t!=0, go back to step 3.
    // Step 7: The GCD is u*2^k.
    public void gcdBigNbr(int Nbr1[], int Nbr2[], int Gcd[], int NumberLength) {
        int i, k;

        System.arraycopy(Nbr1, 0, CalcAuxGcdU, 0, NumberLength);
        System.arraycopy(Nbr2, 0, CalcAuxGcdV, 0, NumberLength);
        for (i = 0; i < NumberLength; i++) {
            if (CalcAuxGcdU[i] != 0) {
                break;
            }
        }
        if (i == NumberLength) {
            System.arraycopy(CalcAuxGcdV, 0, Gcd, 0, NumberLength);
            return;
        }
        for (i = 0; i < NumberLength; i++) {
            if (CalcAuxGcdV[i] != 0) {
                break;
            }
        }
        if (i == NumberLength) {
            System.arraycopy(CalcAuxGcdU, 0, Gcd, 0, NumberLength);
            return;
        }
        if (CalcAuxGcdU[NumberLength - 1] >= 0x40000000L) {
            ChSignBigNbr(CalcAuxGcdU, NumberLength);
        }
        if (CalcAuxGcdV[NumberLength - 1] >= 0x40000000L) {
            ChSignBigNbr(CalcAuxGcdV, NumberLength);
        }
        k = 0;
        while ((CalcAuxGcdU[0] & 1) == 0 && (CalcAuxGcdV[0] & 1) == 0) { // Step 1
            k++;
            DivBigNbrByLong(CalcAuxGcdU, 2, CalcAuxGcdU, NumberLength);
            DivBigNbrByLong(CalcAuxGcdV, 2, CalcAuxGcdV, NumberLength);
        }
        if ((CalcAuxGcdU[0] & 1) == 1) { // Step 2
            System.arraycopy(CalcAuxGcdV, 0, CalcAuxGcdT, 0, NumberLength);
            ChSignBigNbr(CalcAuxGcdT, NumberLength);
        } else {
            System.arraycopy(CalcAuxGcdU, 0, CalcAuxGcdT, 0, NumberLength);
        }
        do {
            while ((CalcAuxGcdT[0] & 1) == 0) { // Step 4
                DivBigNbrByLong(CalcAuxGcdT, 2, CalcAuxGcdT, NumberLength); // Step 3
            }
            if (CalcAuxGcdT[NumberLength - 1] < 0x40000000L) { // Step 5
                System.arraycopy(CalcAuxGcdT, 0, CalcAuxGcdU, 0, NumberLength);
            } else {
                System.arraycopy(CalcAuxGcdT, 0, CalcAuxGcdV, 0, NumberLength);
                ChSignBigNbr(CalcAuxGcdV, NumberLength);
            }                                                // Step 6
            SubtractBigNbr(CalcAuxGcdU, CalcAuxGcdV, CalcAuxGcdT, NumberLength);
            for (i = 0; i < NumberLength; i++) {
                if (CalcAuxGcdT[i] != 0) {
                    break;
                }
            }
        }
        while (i != NumberLength);
        System.arraycopy(CalcAuxGcdU, 0, Gcd, 0, NumberLength); // Step 7
        while (k > 0) {
            AddBigNbr(Gcd, Gcd, Gcd, NumberLength);
            k--;
        }
    }

    static void Convert31To32Bits(int[] nbr31bits, long[] nbr32bits,
                                  int NumberLength) {
        int i, j, k;
        i = 0;
        for (j = -1; j < NumberLength; j++) {
            k = i % 31;
            if (k == 0) {
                j++;
            }
            if (j == NumberLength) {
                break;
            }
            if (j == NumberLength - 1) {
                nbr32bits[i] = nbr31bits[j] >> k;
            } else {
                nbr32bits[i] = ((nbr31bits[j] >> k) |
                        (nbr31bits[j + 1] << (31 - k))) & 0xFFFFFFFFL;
            }
            i++;
        }
        for (; i < NumberLength; i++) {
            nbr32bits[i] = 0;
        }
    }

    static void Convert32To31Bits(long[] nbr32bits, int[] nbr31bits,
                                  int NumberLength) {
        int i, j, k;
        j = 0;
        nbr32bits[NumberLength] = 0;
        for (i = 0; i < NumberLength; i++) {
            k = i & 0x0000001F;
            if (k == 0) {
                nbr31bits[i] = (int) (nbr32bits[j] & 0x7FFFFFFF);
            } else {
                nbr31bits[i] = (int) (((nbr32bits[j] >> (32 - k)) |
                        (nbr32bits[j + 1] << k)) & 0x7FFFFFFF);
                j++;
            }
        }
    }


}


