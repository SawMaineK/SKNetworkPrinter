package com.smk.networkprinter;

public interface Printer {
   void open();

   void write(byte[] command);

   void close();
}
