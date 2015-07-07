package com.isa.bencode;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Bytes extends Object {
	private byte[] value;

	
	public byte[] getValue() {
		return value;
	}

	public void setValue(byte[] value) {
		this.value = value;
	}

	public Bytes(byte[] value) {
		this.value = value;
	}
	
	public Bytes(InputStream inputStream,char before) throws IOException {
		char c;
		StringBuffer length = new StringBuffer(""+before);
		while((c = (char)inputStream.read())!=':'){
			length.append(c);
		}
		value = new byte[Integer.valueOf(length.toString())];
		inputStream.read(value);
	}
	

	@Override
	public void writeExternal(OutputStream outputStream) throws IOException {
		outputStream.write( (value.length+":").getBytes("UTF-8"));
		outputStream.write(value);
	}

	
	@Override
	public String toString() {
		return (value!=null)?new String(value):null;
	}

	@Override
	public boolean equals(java.lang.Object arg0) {
		if(arg0 instanceof Bytes){
			return value.equals(((Bytes)arg0).value);
		}else{
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return value.hashCode();
	}
	
}
