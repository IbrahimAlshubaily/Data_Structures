//*** I trimmed this class and customized it a bit to write the compressed.txt ***NOT MINE..
/******************************************************************************
 *  Compilation:  javac BinaryStdOut.java
 *  Execution:    java BinaryStdOut
 *  Dependencies: none
 *
 *  Write binary data to standard output, either one 1-bit boolean,
 *  one 8-bit char, one 32-bit int, one 64-bit double, one 32-bit float,
 *  or one 64-bit long at a time.
 *
 *  The bytes written are not aligned.
 *
 ******************************************************************************/

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *  <i>Binary standard output</i>. This class provides methods for converting
 *  primtive type variables ({@code boolean}, {@code byte}, {@code char},
 *  {@code int}, {@code long}, {@code float}, and {@code double})
 *  to sequences of bits and writing them to standard output.
 *  Uses big-endian (most-significant byte first).
 *  <p>
 *  The client must {@code flush()} the output stream when finished writing bits.
 *  <p>
 *  The client should not intermixing calls to {@code BinaryStdOut} with calls
 *  to {@code StdOut} or {@code System.out}; otherwise unexpected behavior 
 *  will result.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public final class BinaryStdOut {
    private  BufferedOutputStream out;

    private  int buffer;     // 8-bit buffer of bits to write out
    private  int n;          // number of bits remaining in buffer

    // don't instantiate
    BinaryStdOut() throws FileNotFoundException {
    	out = new BufferedOutputStream(new FileOutputStream( "./compressed.txt"));
    }

   /**
     * Write the specified bit to standard output.
     */
    private  void writeBit(boolean bit) {
        // add bit to buffer
        buffer <<= 1;
        if (bit) buffer |= 1;

        // if buffer is full (8 bits), write out as a single byte
        n++;
        if (n == 8) clearBuffer();
    } 
    
    // write out any remaining bits in buffer to standard output, padding with 0s
    private  void clearBuffer() {
        if (n == 0) return;
        if (n > 0) buffer <<= (8 - n);
        try {
            out.write(buffer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        n = 0;
        buffer = 0;
    }

   /**
     * Flush standard output, padding 0s if number of bits written so far
     * is not a multiple of 8.
     */
    public  void flush() {
        clearBuffer();
        try {
            out.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

   /**
     * Flush and close standard output. Once standard output is closed, you can no
     * longer write bits to it.
     */
    public  void close() {
        flush();
        try {
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


   /**
     * Write the specified bit to standard output.
     * @param x the {@code boolean} to write.
     */
    public  void write(boolean x) {
        writeBit(x);
    } 


}