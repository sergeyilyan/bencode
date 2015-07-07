package com.isa.bencode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;




public class Bencode {

	public static void encode(Object object,OutputStream outputStream) throws IOException{
		object.writeExternal(outputStream);
	}
	public static byte[] encode(Object object) throws IOException{
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); 
		object.writeExternal(outputStream);
		outputStream.close();
		return outputStream.toByteArray();
	}
	
	public static Object decode(InputStream inputStream) throws IOException{
		char c = (char)inputStream.read();
		return Object.create(inputStream, c);
	}
	public static Object decode(byte[] value) throws IOException{
		return Object.create(new ByteArrayInputStream(value,1,value.length-1), (char)value[0]);
	}

}

