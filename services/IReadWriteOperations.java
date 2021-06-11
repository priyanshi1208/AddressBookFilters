package com.addresbook.services;

import java.io.IOException;

public interface IReadWriteOperations {
    void printAddressbookIntoFile(String filename,OutputType outputType) throws IOException;
    void ReadFile(String filename) throws IOException;
    long CountEntries(String filename);
}
