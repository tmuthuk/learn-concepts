import java.io.FileInputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Learn_ByteBuffer {

    ByteBuffer buff;
    public static void main(String args[]) throws IOException {
        Learn_ByteBuffer learnByteBuffer = new Learn_ByteBuffer();

        learnByteBuffer.createByteBuffer();
        learnByteBuffer.readFile();
    }

    public void createByteBuffer() {
        int BUFFER_SIZE = 100; // bytes
        buff = ByteBuffer.allocate(BUFFER_SIZE);

        // Properties
        /*
         * Capacity - total allocated size of the buffer
         */
        System.out.println( "ByteBuffer Capacity - "+ buff.capacity()); // prints 1024


        System.out.println( "ByteBuffer Limit - " + buff.limit());
    }

    public void readFile() throws IOException {
        // Use ByteBuffer to read a file

        final FileInputStream fis = new FileInputStream("/Users/tamil/Downloads/Logikcull_ProcessingBackendEngineer.pdf");
        FileChannel fc = fis.getChannel();
        showStats("newly allocated", fc, buff);
        // ---------

        fc.read(buff);
        showStats("after first read", fc, buff);
        // ---------

        // Toggles from write mode to read mode. Sets the read position to the start of the buffer
        buff.flip();
        showStats("after flip", fc, buff);
        // ---------

        // Remaining reports the total bytes remaining between the current position and the limit
        byte[] byteArr = new byte[buff.remaining()];
        buff.get(byteArr);

        showStats("after reading", fc, buff);
        // ---------

        // Clears the contents of the buffer and sets the write position to the start of the buffer
        buff.clear();
        showStats("after clear", fc, buff);
        // ---------
    }

    private static void showStats(String where, FileChannel fc, Buffer b ) throws IOException {

        // Properties
        /*
         * Capacity - total allocated size of the buffer
         * Limit - points to the index in the buffer until which the data has been written so far
         * Position - index at which the pointer is currently present.
         *            While writing to the buffer, position points to the next location where the data will be written
         *            While reading/emptying the buffer, position points to the index which will be read next
         * Remaining - reports the total bytes remaining between the current position and the limit
         */
        System.out.println( where +
                " channelPosition: " + fc.position() +
                " bufferPosition: " + b.position() +
                " limit: " + b.limit() +
                " remaining: " + b.remaining() +
                " capacity: " + b.capacity() );
    }
}
