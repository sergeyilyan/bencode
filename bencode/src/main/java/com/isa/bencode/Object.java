package com.isa.bencode;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

abstract public class Object {
	abstract void writeExternal(OutputStream outputStream) throws IOException ;

	public static Object create(InputStream inputStream,char c) throws IOException{
		switch (c) {
		case 'i':
			return new Number(inputStream,c);
		case 'd':
			return new Map(inputStream,c);
		case 'l':
			return new Array(inputStream,c);
		default:
			return new Bytes(inputStream,c);
		}
	}
}
