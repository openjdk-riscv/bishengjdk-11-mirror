/*
 * Copyright (c) 2000, 2018, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package nsk.jdi.FloatValue.value;

import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;


/**
 * This class is used as debuggee application for the value001a JDI test.
 */

public class value001a {

    //----------------------------------------------------- templete section

    static final int PASSED = 0;
    static final int FAILED = 2;
    static final int PASS_BASE = 95;

    //--------------------------------------------------   log procedures

    static boolean verbMode = false;  // debugger may switch to true

    private static void log1(String message) {
        if (verbMode)
            System.err.println("**> value001a: " + message);
    }

    private static void logErr(String message) {
        if (verbMode)
            System.err.println("!!**> value001a: " + message);
    }

    //====================================================== test program
    //................................................... globals for a debugger

        public static float pos_inf     =  Float.POSITIVE_INFINITY;
        public static float pos_zero    =  0.0f;
        public static float neg_zero    = -0.0f;
        public static float neg_inf     =  Float.NEGATIVE_INFINITY;

        public static float pos_largest  =  Float.MAX_VALUE;
        public static float pos_smallest =  Float.MIN_VALUE;
        public static float neg_largest  = -Float.MAX_VALUE;
        public static float neg_smallest = -Float.MIN_VALUE;

        public static float float_nan   =  Float.NaN;

//      public static float plus1_1     =  +1.0f;
//      public static float plus1_2     =  +1.0f;
//      public static float minus1      =  -1.0f;

//      public static double double_plus1 = +1.0d;

    //....................................................
    //----------------------------------------------------   main method

    public static void main (String argv[]) {

        for (int i=0; i<argv.length; i++) {
            if ( argv[i].equals("-vbs") || argv[i].equals("-verbose") ) {
                verbMode = true;
                break;
            }
        }
        log1("debuggee started!");

        // informing a debugger of readyness
        ArgumentHandler argHandler = new ArgumentHandler(argv);
        IOPipe pipe = argHandler.createDebugeeIOPipe();
        pipe.println("ready");


        int exitCode = PASSED;
        for (int i = 0; ; i++) {

            String instruction;

            log1("waiting for an instruction from the debugger ...");
            instruction = pipe.readln();
            if (instruction.equals("quit")) {
                log1("'quit' recieved");
                break ;

            } else if (instruction.equals("newcheck")) {
                switch (i) {

    //------------------------------------------------------  section tested

                case 0:
                                pipe.println("checkready");
                                break ;

    //-------------------------------------------------    standard end section

                default:
                                pipe.println("checkend");
                                break ;
                }

            } else {
                logErr("ERRROR: unexpected instruction: " + instruction);
                exitCode = FAILED;
                break ;
            }
        }

        System.exit(exitCode + PASS_BASE);
    }
}
